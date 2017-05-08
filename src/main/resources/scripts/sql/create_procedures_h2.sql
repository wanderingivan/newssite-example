


CREATE ALIAS IF NOT EXISTS unread AS $$
ResultSet unread(Connection conn, String username) throws SQLException {
	conn.setAutoCommit(false);
	PreparedStatement userStm = conn.prepareStatement("SELECT u.user_id as userId FROM users u WHERE u.username = ?");
	userStm.setString(1,username);
	ResultSet rs = userStm.executeQuery();
	rs.next();
	Long userId = rs.getLong("userId");	
	
	PreparedStatement countStmt = conn.prepareStatement("SELECT * FROM messages cm INNER JOIN users u ON u.user_id = cm.poster_id WHERE cm.chat_id IN (SELECT cj.chat_id FROM chats_join_table cj WHERE cj.user_id=?) AND cm.poster_id != ? AND cm.read=0");
	countStmt.setLong(1,userId);
	countStmt.setLong(2,userId);
	rs = countStmt.executeQuery();
	
	PreparedStatement updateStmt =conn.prepareStatement("UPDATE messages cm SET cm.read = 1 WHERE cm.chat_id IN (SELECT chat_id from chats_join_table WHERE user_id=?) AND cm.poster_id !=? AND cm.read=0");
	updateStmt.setLong(1,userId);
	updateStmt.setLong(2,userId);
	updateStmt.executeUpdate();
	conn.commit();
	return rs;
}
$$;
	
CREATE ALIAS IF NOT EXISTS countunread AS $$
ResultSet countUnread(Connection conn, String username) throws SQLException {
	conn.setAutoCommit(false);
	PreparedStatement userStm = conn.prepareStatement("SELECT u.user_id as userId FROM users u WHERE u.username = ?");
	userStm.setString(1,username);
	ResultSet rs = userStm.executeQuery();
	rs.next();
	Long userId = rs.getLong("userId");
	PreparedStatement countStmt = conn.prepareStatement("SELECT count(*) as unread FROM messages cm INNER JOIN users u ON u.user_id = cm.poster_id WHERE cm.chat_id IN (SELECT cj.chat_id FROM chats_join_table cj WHERE cj.user_id=?) AND cm.poster_id != ? AND cm.read=0;");
	countStmt.setLong(1,userId);
	countStmt.setLong(2,userId);
	rs = countStmt.executeQuery();
	conn.commit();
	return rs;
}
$$;


CREATE ALIAS IF NOT EXISTS  addcomment AS $$
void addComment(Connection conn,String message,long articleId, String username) throws SQLException {
	PreparedStatement addCommentStmt = conn.prepareStatement("INSERT INTO comments(message,article_id,poster_id) VALUES (?,?,(SELECT u.user_id FROM users u WHERE u.username = ?))");
	addCommentStmt.setString(1,message);
	addCommentStmt.setLong(2,articleId);
	addCommentStmt.setString(3,username);
	addCommentStmt.executeUpdate();
}
$$;

CREATE ALIAS IF NOT EXISTS addvote AS $$
ResultSet addVote(Connection conn, long commentId,int vote) throws SQLException {
	conn.setAutoCommit(false);
	PreparedStatement updateVoteCountStmt = conn.prepareStatement("UPDATE comments SET votes = votes + ? WHERE comment_id = ?");
    updateVoteCountStmt.setLong(1,vote);
	updateVoteCountStmt.setLong(2,commentId);
	updateVoteCountStmt.executeUpdate();
	
	PreparedStatement returnVoteCountStmt = conn.prepareStatement("SELECT votes FROM comments WHERE comment_id = ?");
	returnVoteCountStmt.setLong(1,commentId);
	ResultSet rs = returnVoteCountStmt.executeQuery();
    conn.commit();
	return rs;
}
$$;

CREATE ALIAS IF NOT EXISTS addmessage AS $$ 
ResultSet addMessage(Connection conn,String username, long chatId,String message) throws SQLException {
	PreparedStatement addMessageStmt = conn.prepareStatement("INSERT INTO messages(poster_id,chat_id,message) VALUES((SELECT u.user_id FROM users u WHERE u.username= ?),?,?)");
	addMessageStmt.setString(1,username);
	addMessageStmt.setLong(2,chatId);
	addMessageStmt.setString(3,message);
	addMessageStmt.executeUpdate();
	
	PreparedStatement updateChatsMessage = conn.prepareStatement("UPDATE chats SET lastUpdate = NOW() WHERE chat_id = ?");
	updateChatsMessage.setLong(1,chatId);
	updateChatsMessage.executeUpdate();
	
	PreparedStatement selectLastMessage = conn.prepareStatement("SELECT * FROM messages WHERE message_id = LAST_INSERT_ID()");
	ResultSet rs = selectLastMessage.executeQuery();
	conn.commit();
	return rs;
}
$$;


CREATE ALIAS IF NOT EXISTS sendmessage AS $$
void sendMessage(Connection conn,String message, String sender, String receiver) throws SQLException {
	conn.setAutoCommit(false);
	Long chatId = null;
	PreparedStatement getChatStmt = conn.prepareStatement("SELECT chat_id as chatId FROM chats_join_table WHERE user_id IN (SELECT user_id FROM users WHERE username = ? OR username = ?) GROUP BY chat_id HAVING count(*) > 1");
	getChatStmt.setString(1,sender);
	getChatStmt.setString(2,receiver);
	ResultSet rs = getChatStmt.executeQuery();
	
	if(rs.next()== false){
		conn.prepareStatement("INSERT INTO chats(lastUpdate) VALUES(NOW())")
		    .executeUpdate();
		rs = conn.prepareStatement("SELECT LAST_INSERT_ID() as newCId")
		         .executeQuery();
		rs.next();
		chatId = rs.getLong("newCId");
		System.out.println("ChatId: " + chatId);
		PreparedStatement addUserToChatStmt = conn.prepareStatement("INSERT INTO chats_join_table(chat_id,user_id) VALUES(?,(SELECT user_id FROM users WHERE username =?))");
		addUserToChatStmt.setLong(1,chatId);
		addUserToChatStmt.setString(2,sender);
		addUserToChatStmt.executeUpdate();
		addUserToChatStmt.setString(2,receiver);
		addUserToChatStmt.executeUpdate();
	}else{
		chatId = rs.getLong("chatId");
	}
	
	PreparedStatement addMessageStmt = conn.prepareStatement("INSERT INTO messages(poster_id,chat_id,message) VALUES((SELECT u.user_id FROM users u WHERE u.username= ?),?,?)");
	addMessageStmt.setString(1,sender);
	addMessageStmt.setLong(2,chatId);
	addMessageStmt.setString(3,message);
	addMessageStmt.executeUpdate();
	
	PreparedStatement updateChatsMessage = conn.prepareStatement("UPDATE chats SET lastUpdate = NOW() WHERE chat_id = ?");
	updateChatsMessage.setLong(1,chatId);
	updateChatsMessage.executeUpdate();

	conn.commit();
}
$$;

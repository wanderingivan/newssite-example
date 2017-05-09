-- Stored Procedures

DROP PROCEDURE IF EXISTS addComment//
DROP PROCEDURE IF EXISTS addVote//
DROP PROCEDURE IF EXISTS addMessage//
DROP PROCEDURE IF EXISTS sendMessage//
DROP PROCEDURE IF EXISTS unread//
DROP PROCEDURE IF EXISTS countUnread//

CREATE PROCEDURE `addComment` (IN message VARCHAR(250), IN articleId BIGINT, IN username VARCHAR(50))
BEGIN
	INSERT INTO comments(message,article_id,poster_id) VALUES (message,articleId,(SELECT u.user_id FROM users u WHERE u.username = username));
END//

CREATE PROCEDURE `addVote`(IN commentId BIGINT, IN vote INTEGER)
BEGIN
	UPDATE comments SET votes = votes + vote WHERE comment_id = commentId;
	SELECT votes FROM comments WHERE comment_id = commentId;
END//

CREATE PROCEDURE `addMessage`(IN username VARCHAR(50),IN chatId BIGINT, IN message VARCHAR(250))
BEGIN
	INSERT INTO messages(poster_id,chat_id,message) VALUES((SELECT u.user_id FROM users u WHERE u.username= username),chatId,message);
  	UPDATE chats SET lastUpdate = NOW() WHERE chat_id = chatId;
  	SELECT * FROM messages WHERE message_id = LAST_INSERT_ID();
END//

CREATE PROCEDURE `sendMessage` (IN msg VARCHAR(250),IN sender VARCHAR(50),IN receiver VARCHAR(50))
BEGIN
DECLARE chatId BIGINT;
SELECT chat_id FROM chats_join_table WHERE user_id IN (SELECT user_id FROM users WHERE username = sender OR username = receiver) GROUP BY chat_id HAVING count(*) > 1 INTO chatId;

IF chatId IS NULL THEN
    INSERT INTO chats(lastUpdate) VALUES(NOW());
    SELECT LAST_INSERT_ID() INTO chatId;
    INSERT INTO chats_join_table(chat_id,user_id) VALUES(chatId,(SELECT user_id FROM users WHERE username =sender));
    INSERT INTO chats_join_table(chat_id,user_id) VALUES(chatId,(SELECT user_id FROM users WHERE username =receiver));	
END IF;
CALL addMessage(sender,chatId,msg); 
END//

CREATE PROCEDURE `unread` (IN username VARCHAR(50))
BEGIN 
DECLARE user_id BIGINT;
    SELECT u.user_id FROM users u WHERE u.username = username INTO user_id;
    SELECT *
      FROM messages cm 
           INNER JOIN users u 
           ON u.user_id = cm.poster_id 
     WHERE cm.chat_id IN (SELECT cj.chat_id FROM chats_join_table cj WHERE cj.user_id=user_id) 
       AND cm.poster_id !=user_id 
       AND cm.read=0;
    UPDATE messages cm SET cm.read = 1 WHERE cm.chat_id IN (SELECT chat_id from chats_join_table WHERE user_id=user_id) AND cm.poster_id !=user_id AND cm.read=0;
END//

CREATE PROCEDURE `countUnread` (IN username VARCHAR(50))
BEGIN 
DECLARE user_id BIGINT;
    SELECT u.user_id FROM users u WHERE u.username = username INTO user_id;
    SELECT count(*) as unread
      FROM messages cm 
           INNER JOIN users u 
           ON u.user_id = cm.poster_id 
     WHERE cm.chat_id IN (SELECT cj.chat_id FROM chats_join_table cj WHERE cj.user_id=user_id) 
       AND cm.poster_id != user_id 
       AND cm.read=0;
END//
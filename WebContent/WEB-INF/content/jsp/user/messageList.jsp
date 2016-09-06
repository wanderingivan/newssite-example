<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/message.js"></script>
    <div class="mainContent">
      <div class="row">
       <div class="col-md-4 col-sm-6 col-xs-12">
		<div class="userMessages">
		  <s:iterator value="chats" status="stat">
            <s:url action="loadUser" namespace="/user" var="loadUser">
              <s:param name="username" value="getOther(username).getUsername()"/>
            </s:url>
            <s:url action="loadImage" namespace="/util" var="loadImage">
              <s:param name="path" value="getOther(username).getImagePath()"/>
			</s:url>
            <s:url action="loadChat" namespace="/message" var="loadChat">
              <s:param name="chatId" value="%{id}"/>
            </s:url>
            <div class="media">
              <div class="media-left">
 		  	    <sj:a id="chat%{id}" 
				  href="%{loadChat}" 
				  targets="results" 
				  indicator="indicator"  
			    >
		  	      <img class="message-img" src="<s:property value='#loadImage'/>" alt="Chat with <s:property value='getOther(username).getUsername()'/>" title="Chat with <s:property value='getOther(username).getUsername()'/>"/>
			    </sj:a>
              </div>
              <div class="media-body">
		  	    <h4><sj:a id="chat%{id}" 
				  	      href="%{loadChat}" 
				          targets="results" 
				          indicator="indicator">  
			          <s:text name="global.chat_with"/>
			        </sj:a>&nbsp;
			        <s:a href="%{loadUser}">
			          <s:property value="getOther(username).getUsername()"/>
			        </s:a></h4>
                <p>${messages.first().getMessage()}</p>
                <p><s:date name="messages.first().getSent()" nice="true"/></p>
              </div>
            </div>
            <s:if test="#stat.isFirst()">
              <s:set var="first">
                 <s:param name="value">
                   <s:property />
                 </s:param>
              </s:set>
			</s:if>
	      </s:iterator>
		</div>
        </div>
        <div class="col-md-offset-1 col-md-6 col-sm-6 col-xs-12">

		  <div id="results">
			<div class="chatList">
			  <s:form id="chat%{#first.id}addMessage" action="addMessage" namespace="/message" theme="simple" cssClass="form-inline">
                <div class="form-group" style="width:100%; margin-bottom:20px;">
                  <button type="submit" class="btn btn-primary">
                    <i class="fa fa-envelope"></i>
                    <s:property value="getText('global.send')"/>
                  </button>
                  <s:textarea class="form-control share-text" placeholder="%{getText('global.send_message')}" name="message" required="true" maxLength="180"/>
                </div>
			      <s:hidden name="chatId" value="%{#first.id}"/>
			      <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
			  </s:form>
			  <s:iterator value="#first.messages">
			    <s:url action="loadUser" namespace="/user" var="loadUser">
			      <s:param name="username" value="%{poster.username}"/>
			    </s:url>
			    <s:url action="loadImage" namespace="/util" var="loadPic">
  					<s:param name="path" value="poster.imagePath"/>	      
			    </s:url>
			    <div class="media well well-sm">
			      <div class="media-left">
			        <img class="" style="width:55px;height:55px;border-radius:4px" src="<s:property value='#loadPic'/>" alt="User Profile Image"/> 
			      </div>
			      <div class="media-body">
			        <h5 class="media-heading"><s:text name="global.sentBy"/>&nbsp;<s:a href="%{loadUser}">${poster.username}</s:a></h5>
			        <p><s:property value="message"/></p>
			        <p><s:date name="sent" nice="true"/></p>
			      </div>
			    </div>
			  </s:iterator>
		  	  <img id="indicator" class="img-responsive" style="display:none;width:100px;height:100px;" src="${pageContext.request.contextPath}/images/indicator.gif" />
			</div>	
		  </div>
        </div>
       </div>
      </div>
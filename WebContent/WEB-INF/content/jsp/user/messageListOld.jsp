<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/message.jsp"></script>
		<div class="userMessages">
		  <s:iterator value="chats" var="chat">

		  	<h3>Chat with <s:property value="getOther(username)"/></h3>
			<div id="chatId%{id}" class="chatList">
			  <s:iterator value="messages">
			    <s:url action="showUser" namespace="/user" var="loadUser">
			      <s:param name="username">
			        <s:property value="poster.username"/>
			      </s:param>
			    </s:url>
			    <s:url action="loadImage" namespace="/util" var="loadPic">
				  <s:param name="path">
			        <s:property value="poster.imagePath"/>
			      </s:param>		      
			    </s:url>
			    <div class="media">
			      <div class="media-left">
			        <img class="img-responsive" src="<s:property value='#loadPic'/>" alt="!Missing"/> 
			      </div>
			      <div class="media-body">
			        <h5 class="media-heading">Sent by <s:a href="%{loadUser}">${poster.username}</s:a></h5>
			        <s:property value="message"/>
			        <s:date name="sent" nice="true"/>
			        <a class="btn" data-toggle="tab" href="#form">Reply</a>
			      </div>
			    </div>
			  </s:iterator>
			  <s:form id="chat%{id}addMessage" action="addMessage" namespace="/message">
			    <s:textarea placeholder="Your Message" name="message"/>
			    <s:hidden name="sender">
            	  <s:param name="value">
              		<sec:authentication property="principal.username"/>
                  </s:param>
                </s:hidden>
			    <s:hidden name="chatId">
                  <s:param name="value">
                    <s:property value="id"/>
                  </s:param>
                </s:hidden>
			    <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
			    <sj:submit class="btn" key="global.sendMessage"
			    		   dataType="json"
			    		   onSuccessTopics="addMessage"
			    		   target="t"
			    		   button="true"
			    />
			  </s:form>
			</div>	
	      </s:iterator>
		</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
  <s:iterator value="messages">
	<s:url action="loadUser" namespace="/user" var="loadUser">
	  <s:param name="username" value="%{poster.username}"/>
	</s:url>
	<s:url action="loadImage" namespace="/util" var="loadPic">
  	  <s:param name="path" value="poster.imagePath"/>	      
	</s:url>
	<li>
	  <div class="media well well-sm">
		<div class="media-left">
		  <img class="img-responsive" style="width:55px;height:55px;border-radius:4px" src="<s:property value='#loadPic'/>" alt="User Profile Image"/> 
	    </div>
	    <div class="media-body">
		  <h5 class="media-heading"><s:text name="global.sentBy"/>&nbsp;<s:a href="%{loadUser}">${poster.username}</s:a></h5>
		  <p><s:property value="message"/></p>
		  <p><s:date name="sent" nice="true"/></p>
		</div>
	  </div>
	</li>
  </s:iterator>
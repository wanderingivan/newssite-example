<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			 <div class="list">
	  		      <h2 class="article_title" style="text-transform:initial">${message}</h2>
	  		 	  <s:iterator value="users">
	  		   		<s:url action="loadUser" namespace="/user" var="showUser">
	  		          <s:param name="username">
	  		  	        <s:property value="key"/>
	  		  	      </s:param>
	  		        </s:url>
	  		        <s:url action="loadImage" namespace="/util" var="loadImage">
	  		          <s:param name="path">
	  		  	        <s:property value="value"/>
	  		  	      </s:param>
	  		        </s:url>
  		  	        <div class="media">
	  		  	      <div class="media-left">	  		  		
	  		  	        <img src="<s:property value='#loadImage'/>"/>
	  		          </div>
	  		          <div class="media-body">
	  		  	        <s:a class="media-heading" href="%{showUser}"><s:property value="key"/></s:a>
	  		          </div>
	  		        </div>
	  		      </s:iterator>
	  		 </div>
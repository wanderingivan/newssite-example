<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	  	    <div class="list">
	  		 <h2 class="article_title" style="text-transform:initial">${message}</h2>
	  		 <s:iterator value="articles">
	  		  <s:url action="loadArticle" namespace="/article" var="showArticle">
	  		  	<s:param name="headline" value="%{headline}"/>
	  		  </s:url>
	  		  <s:url action="loadImage" namespace="/util" var="loadImage">
	  		    <s:param name="path" value="%{imagePath}"/>
	  		  </s:url>
  		  	  <div class="media">
	  		  	<div class="media-left">	  		  		
	  		  	  <s:a href="%{showArticle}"><img src="<s:property value='#loadImage'/>"/></s:a>
	  		    </div>
	  		    <div class="media-body">
	  		  	  <s:a class="media-heading" href="%{showArticle}"><s:property value="headline"/></s:a>
	  		  	  <p><s:property value="caption"/></p>
	  		    </div>
	  		  </div>
	  		 </s:iterator>
	  		</div>
	  	   
	  	
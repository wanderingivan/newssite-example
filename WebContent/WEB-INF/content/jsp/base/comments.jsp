<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
			      <ul>
			        <s:iterator value="comments">
			        <li>
        	  	  	  	<s:url action="loadUser" namespace="/user" var="showUserUrl">
     		        	  <s:param name="username" value="%{poster.username}"/>
     		        	</s:url>
        	  	  		<s:url action="loadArticle" namespace="/article" var="articleUrl">
     		        	  <s:param name="headline" value="%{article.headline}"/>
      		  	  		</s:url>        		  	  		  
      		  	  		<s:url action="loadImage" namespace="/util" var="loadImage">
      			    	  <s:param name="path" value="%{poster.imagePath}"/>
      			  	    </s:url>			          
					  <div class="media">
					    <a class="media-left">
						  <img alt="img" src="<s:property value='#loadImage'/>"/>
					    </a>
					    <div class="media-body">
						  <div class="media-heading sidecolumn">
						    <s:a href="%{showUserUrl}"><h5><s:property value="poster.username"/>&nbsp;<s:text name="global.said"/></h5></s:a>
						    <p><s:property value="message"/></p>
						    <p class="text-muted"><s:date name="posted" nice="true"/></p>
						    <s:a href="%{articleUrl}">&nbsp;<s:property value="article.headline"/></s:a>
					      </div>
					    </div>
					  </div>			          
			        </li>
			        </s:iterator>			      
			      </ul>
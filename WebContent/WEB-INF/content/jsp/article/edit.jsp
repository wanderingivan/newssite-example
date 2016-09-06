<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 	      
 	  <div class="articleMain">    	
 	  <sec:authorize access="isAuthenticated() and (hasRole('ROLE_ADMIN') or authentication.name =='${author}')">
	  	<s:form id="articleForm" theme="simple" cssClass="form-wrapper alt" action="editArticle" namespace="/article" enctype="multipart/form-data">
          <s:fielderror fieldName="article.headline" cssClass="alert alert-danger"/>
	      <s:textfield name="article.headline" value="%{article.headline}"/>
          <s:fielderror fieldName="article.category" cssClass="alert alert-danger"/>
	      <s:select label="Category" name="article.category"
			  			headerKey = "-1" headerValue="Select Category"
			  			list= "#{'politics':'politics','international':'international','sports':'sports','entertainment':'entertainment'}"
			  			value="#{article.category}"
		  />
	      <s:label for="article.caption" key="global.caption"/>
          <s:fielderror fieldName="article.caption" cssClass="alert alert-danger"/>
	      <s:textarea name="article.caption" value="%{article.caption}"/>
	      <s:iterator status="stat" value="articleParagraphs">
	       <div class="form-group paragraph">
	      	<s:textarea id="paragraphInput">
	      	 	<s:param name="name">
	      	 	  articleParagraphs[<s:property value="#stat.index"/>]
	      	 	</s:param>
	      		<s:param name="value">
	      			<s:property value="text"/>
	      		</s:param>
	      	</s:textarea>
	       </div>
	      </s:iterator>
	      <s:a href="#" id="addParagraph" class="btn"><s:text name="global.add_paragraph"/></s:a>
	      <s:file name="articlePic" accept="image/jpeg, image/png"/>
		  <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
		  <s:hidden name="article.id" value="%{article.id}"/>
		  <s:hidden name="article.imagePath" value="%{article.imagePath}"/>
	      <s:submit key="global.edit"/>
	    </s:form> 	  
	  </sec:authorize>
	  </div>
	  <script>$(document).ready(function(){setUpForm();});</script> 

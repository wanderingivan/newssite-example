<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

	<s:url action="loadUser" namespace="/user" var="showAuthor">
		<s:param name="username" value="%{article.author.username}"/>
	</s:url>
	<s:url action="loadImage" namespace="/util" var="loadArticleImage">
    	<s:param name="path" value="article.imagePath"/>
    </s:url>
    <s:url action="loadImage" namespace="/util" var="loadAuthorImage">
    	<s:param name="path" value="article.author.imagePath"/>
    </s:url>
    <s:url namespace="index" var="article.category">
    	<s:param name="action" value='article.category'/>
    </s:url>
    <div class="row">
    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
      <div class="article_content">
      
      	<ol class="breadcrumb">
      	  <li><s:a namespace="/index" action="latest"><s:text name="global.home"/></s:a></li>
      	  <li><s:a namespace="/index" href="%{article.category}" style="text-transform:capitalize;"><s:text name="global.%{article.category}"/></s:a></li>
      	  <li>${article.headline}</li>
      	</ol>

      	<div class="controls">
 	  	 <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN') or authentication.name =='${article.author.username}'">
 	  	  <a data-toggle="tab" href=#article><s:text name="global.show_article"/></a>
 	  	  <a data-toggle="tab" href=#editForm><s:text name="global.edit_article"/></a>
 	     </sec:authorize>
 	     <sec:authorize access="hasRole('ROLE_ADMIN')">
 	      <s:url action="delete" namespace="/article" var="delete">
 	      	<s:param name="id" value="article.id"/>
 	      </s:url>
 	      <s:a href="%{delete}">Delete Article</s:a>
 	     </sec:authorize>
 	 	</div>
 	 	
 	 	<div class="tab-content">

 	 	  <div  id="article" class="tab-pane fade in active">
 	 	    <h2 class="article_title">${article.headline}</h2>
 	 	    <div class="article_img">
 	 	      <img class="thumbnail" alt="Article Image" src="<s:property value='#loadArticleImage'/>"/>
 	 	    </div>
 	 	    <div class="article_body">
 	 	      <div class="article_info">
 	 	       <div class="row">
 	 	       	 <div class="col-md-3 col-sm-3 col-xs-6">
 	      	      <div class="img-box">
 	      	      	<img class="img-responsive" src="<s:property value='#loadAuthorImage'/>" alt="!Missing"/>
 	      	      </div>
 	      	      <div class="info">
 	      	      	<p>Author:</p>
 	      	      	<s:a href="%{showAuthor}">${article.author.username}</s:a>
 	      	      </div>
 	 	       	 </div>
 	 	       	 <div class="col-md-3 col-sm-3 col-xs-6">
 	      	  		<div class="img-box">
 	      	  			<span class="fa fa-calendar"></span>
 	      	  		</div>
 	      	  		<div class="info">
 	      	  			<p><s:text name="global.last_edited"/>:</p>
 	      	  			<strong><s:date name="article.lastEdited" format="dd/MM/yyyy"/></strong>
 	      	  		</div>
 	 	       	 </div>
 	 	       	 <div class="col-md-3 col-sm-3 col-xs-6">
 	      	  		<div class="img-box">
 	      	  		  <span class="fa fa-comment-o"></span>
 	      	  		</div>
 	      	  		<div class="info">
 	      	  	      <p><s:text name="global.comments"/>:</p>      	  	 
 	      	  	      <strong><s:property value="article.comments.size"/></strong>
 	      	  		</div>
 	 	       	 </div>
 	 	       	 <div class="col-md-3 col-sm-3 col-xs-6">
 	      	  		<div class="img-box">
 	      	  		  <span class="fa fa-eye"></span>	
 	      	  		</div>
 	      	  		<div class="info">
 	      	  		  <p><s:text name="global.views"/></p>
 	      	  		  <strong><s:property value="article.hits"/></strong>
 	      	  		</div>
 	 	       	 </div>
 	 	       </div>
 	 	      </div>
 	 	      <div class="article_caption">
 	 	        <b>${article.caption}</b>
 	 	      </div>
 	 	      <div class="article_paragraphs"> 	 	       
 	          	<s:iterator value="article.paragraphs">
 	          	  <p><s:property value="text"/></p>
 	          	</s:iterator> 	         	      
 	 	      </div>
 	 	      <div class="comments-area">

 	            <sec:authorize access="isAnonymous()">
 	              <p class="text-center"><a data-toggle="modal" data-target="#loginModal"><s:text name="global.log_in"/></a>&nbsp;<s:text name="global.to_comment"/></p>
 	            </sec:authorize>
 	            <sec:authorize access="isAuthenticated()">
			     <div id="commentForm" class="comment-form">
				  <s:form action="addComment" namespace="/message" theme="simple">
				    <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
				    <s:hidden name="articleId" value="%{article.id}"/>
				    <div class="form-group">
					  <s:textarea id="message" cssClass="comment_textarea form-control" placeholder="%{getText('global.your_comment')}" name="message" rows="5" cols="50"/>
	      		      <s:submit key="global.add_comment"/>				
				    </div>
				  </s:form>
			     </div> 	          
 	            </sec:authorize>
 	            <s:url var="ajaxComments" namespace="/message" action="loadArticleComments">
 	              <s:param name="articleId" value="%{article.id}"/>
 	            </s:url>
				<sj:div href="%{ajaxComments}"  indicator="indicator">
					<img id="indicator" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>
				</sj:div>
 	          
 	          </div>
 	        </div>
 	 	  </div>
 	 	    
  	  	  <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN') or authentication.name =='${article.author.username}'">
 	  		<div id="editForm" class="tab-pane fade">
 	  		  <s:form id="articleForm" cssClass="form-wrapper alt" theme="simple" action="editArticle" namespace="/article" enctype="multipart/form-data">
                <div class="form-group">
	      		  <s:label for="article.headline" key="global.headline"/>
	      	      <s:textfield name="article.headline" value="%{article.headline}"/>
                </div>
                <div class="form-group">
	      		  <s:select label="Category" name="article.category" cssClass="text-center"
			  			headerKey = "-1" headerValue="Select Category"
			  			list= "#{'politics':'politics','international':'international','sports':'sports','entertainment':'entertainment'}"
			  			value="#{article.category}"
		  		  />
                </div>
                <div class="form-group">
	      		  <s:label for="article.caption" key="global.caption"/>
	      		  <s:textarea name="article.caption" style="height:125px;" value="%{article.caption}"/>
                </div>
	      	    <s:iterator status="stat" value="article.paragraphs">
                  <div class="form-group paragraph ">
                    <s:label for="articleParagraphs[%{#stat.index}]" value="Paragraph %{#stat.count}"/>
	      		    <s:textarea id="paragraphInput" style="height:250px;">
	      	 		  <s:param name="name">
	      	 	        articleParagraphs[<s:property value="#stat.index"/>]
	      	 	      </s:param>
	      		      <s:param name="value">
	      			    <s:property value="text"/>
	      		      </s:param>
	      	        </s:textarea>
                  </div>
	            </s:iterator>
                <div class="form-group">
	              <s:a href="" id="addParagraph" class="btn btn-alt"><s:text name="global.add_paragraph"/></s:a>
                </div>
                <div class="form-group">
                  <s:label for="articlePic" key="global.articlePic"/>
	              <s:file name="articlePic" accept="image/jpeg, image/png"/>
                </div>
		        <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
	   		    <s:hidden name="article.imagePath" value="%{article.imagePath}"/>
		        <s:hidden name="article.id" value="%{article.id}"/>
	            <s:submit key="global.post" cssClass="btn btn-form"/>
	          </s:form> 	  
 	        </div>
 	      </sec:authorize>
 	      
 	   </div>    
 	</div><!-- /Main -->	 
   </div>
   <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
     <div class="article_side">
       <s:url action="loadCategory" namespace="/article" var="category">
         <s:param name="category" value="%{article.category}"/>
       </s:url> 
       <div class="sidebar">
         <s:a href="%{loadCategory}"><h2><s:text name="global.more_in"/>&nbsp;<s:text name="global.%{article.category}"/></h2></s:a>
         <br/>
         <sj:div id="articles" href="%{category}" indicator="indicator2">
			<img id="indicator2" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="width:150px;height:150px;display:none"/>
		 </sj:div>
       </div>
       
		<script>$(document).ready(function(){setUpForm();});</script> 
     </div>
    </div>  
   </div>
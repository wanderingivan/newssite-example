<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	  <div class="">
		<div class="row">
	      <div class="col-md-12">
 	        <s:actionerror/>
 	        <div class="col-md-offset-2 col-md-8 col-sm-12">
	      	<h3 class="text-center"><s:text name="global.create_article"/></h3>
	      	<s:form id="articleForm" cssClass="form-wrapper alt" theme="simple" action="createArticle" namespace="/article" enctype="multipart/form-data">
			  <div class="form-group">
                <s:label for="article.headline" key="global.headline"/>
                <s:fielderror id="headlineError" fieldName="article.headline" cssClass="alert alert-danger"/>
	      	    <s:textfield id="headline" name="article.headline" placeholder="%{getText('global.headline')}" />
              </div>
              <div class="form-group">
                <s:fielderror id="headlineError" fieldName="article.category" cssClass="alert alert-danger"/>
			    <s:select id="category"  name="article.category" pattern="politics|international|entertainment|sport"
			  			  headerKey = "-1" headerValue="%{getText('global.select_category')}"
			  			  list= "#{'politics':'politics','international':'international','sports':'sports','entertainment':'entertainment'}"
			    />
			  </div>
              <div class="form-group">
			    <s:label key="global.caption"/>
                <s:fielderror id="captionError" fieldName="article.caption" cssClass="alert alert-danger"/>
	      	    <s:textarea id="caption" name="article.caption" placeholder="%{getText('global.caption')}"/>
              </div>
              <s:if test="articleParagraphs == null">
              <div class="form-group paragraph">
	      	    <s:label key="global.paragraph"/>
	      	    <s:textarea id="paragraphInput" style="height:250px;" name="articleParagraphs[0]" placeholder="%{getText('global.paragraph')}"/>
              </div>
              </s:if>
              <s:else>	      
              <s:iterator status="stat" value="articleParagraphs">              
                <div class="form-group paragraph">
	      	      <s:label key="global.paragraph"/>
	      		  <s:textarea id="paragraphInput" style="height:250px;" placeholder="%{getText('global.paragraph')}">
	      	 	    <s:param name="name">
	      	 	      articleParagraphs[<s:property value="#stat.index"/>]
	      	 	    </s:param>
	      		    <s:param name="value">
	      			  <s:property/>
	      		    </s:param>
	      	      </s:textarea>
	      	    </div>
	          </s:iterator>
	          </s:else>
              <div class="form-group">
	      	    <s:a href="" id="addParagraph" class="btn btn-alt"><s:text name="global.add_paragraph"/></s:a>
              </div>
              <div class="form-group">       	  
	      	    <s:label for="articlePic" key="global.articlePic"/>
	      	    <s:file name="articlePic" accept="image/jpeg, image/png"/>
              </div>	      
			  <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
	      	  <s:submit id="createArticleSubmit" key="global.create" cssClass="btn btn-form"/>
	      	  <s:submit name="preview" key="global.preview" action="preview" cssClass="btn btn-form"/>
	      	</s:form>
	      	</div>
	      </div>
		</div>  
	  </div>
	  <script>$(document).ready(function(){setUpForm();});</script> 

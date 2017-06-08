<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<s:url action="loadImage" namespace="/util" var="loadArticleImage">
    	<s:param name="path">
      	  <s:property value="tempPicture"/>
        </s:param>
    </s:url>
	  <div class="">

		<div class="row">
          <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
            <div class="article_content">
      	      <ol class="breadcrumb">
      	        <li><s:a href="#">Home</s:a></li>
      	        <li><s:a href="#">${article.category}</s:a></li>
      	        <li>${article.headline}</li>
      	      </ol>
 	 	      <div class="tab-content">

 	 	      <div  id="article" class="tab-pane fade in active">
 	 	        <h2 class="article_title">${article.headline}</h2>
 	 	        <div class="article_img">
 	 	          <img class="thumbnail" alt="Article Image" src="<s:property value='#loadArticleImage'/>"/>
 	 	        </div>
 	 	        <div class="article_body">
 	 	          <div class="article_info">
  	      	        <ul class="list-inline">
 	      	  	      <li>
 	      	  		    <div class="img-box">
 	      	  			  <span class="fa fa-calendar"></span>
 	      	  		    </div>
 	      	  		    <div class="info">
 	      	  			  <p><s:text name="global.last_edited"/>:</p>
 	      	  			  		Not Saved
 	      	  		    </div>
 	      	  	      </li>
 	      	  	      <li>
 	      	  		     <div class="img-box">
 	      	  		       <span class="fa fa-comment-o"></span>
 	      	  		     </div>
 	      	  		     <div class="info">
 	      	  	           <p><s:text name="global.comments"/>:</p>      	  	 
 	      	  	           			0
 	      	  		     </div>
 	      	  	      </li>
 	      	  	      <li>
 	      	  		    <div class="img-box">
 	      	  		      <span class="fa fa-eye"></span>	
 	      	  		    </div>
 	      	  		    <div class="info">
 	      	  		      <p><s:text name="global.views"/></p>
 	      	  		      			1
 	      	  		    </div>
 	      	  	      </li>
 	      	        </ul>	 	      
 	 	          </div>
 	 	          <div class="article_caption">
 	 	            <b>${article.caption}</b>
 	 	          </div> 
 	 	          <div class="article_paragraphs"> 	 	       
 	          	    <s:iterator value="articleParagraphs">
 	          	      <p><s:property/></p>
 	          	    </s:iterator> 	         	      
 	 	          </div>
 	 	        </div>
 	          </div>    
 	        </div><!-- /Main -->	 
          </div>
          </div>
	      <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
 	        <s:actionerror/>
 	        <div class="form-wrapper" style="margin:20px;">
	      	  <h3 class="text-center"><s:text name="global.create_article"/></h3>
	      	  <s:form id="articleForm" theme="simple" action="editArticle" namespace="/article" enctype="multipart/form-data" cssClass="form-wrapper alt">
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
	      	    <s:iterator status="stat" value="articleParagraphs">
                <div class="form-group paragraph">
                  <s:label for="articleParagraphs[%{#stat.index}]" value="Paragraph %{#stat.count}"/>
	      		  <s:textarea id="paragraphInput" style="height:250px;">
	      	 		<s:param name="name">
	      	 	      articleParagraphs[<s:property value="#stat.index"/>]
	      	 	    </s:param>
	      		    <s:param name="value">
	      			  <s:property/>
	      		    </s:param>
	      	      </s:textarea>
                </div>
	            </s:iterator>
                <div class="form-group">
	              <s:a href="" id="addParagraph" class="btn btn-alt"><s:text name="global.add_paragraph"/></s:a>
                </div>
                <div class="form-group">
                  <s:label for="articlePic" key="global.articlePic"/>
	              <s:file name="articlePic" accept="image/png"/>
                </div>
		        <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
		        <s:hidden name="article.id" value="%{article.id}"/>
	            <s:submit key="global.create" cssClass="btn btn-form"/>
                <div class="form-group">
                  <s:a action="createArticlePage" namespace="/article" style="width:100%;" cssClass="btn btn-alt"><s:text name="global.discard"/></s:a>
                </div>
	          </s:form>
	      	</div>
	      </div>
		</div>  
	  </div>
	  <script>$(document).ready(function(){setUpForm();});</script> 

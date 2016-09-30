<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
        <div class="article_content">
				  <s:form action="addComment" namespace="/message" theme="simple">
				    <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
				    <s:hidden name="articleId" value="%{articleId}"/>
				    <s:hidden name="headline" value="%{headline}"/>
				    <div class="form-group">

	                  <s:fielderror fieldName="message" cssClass="alert alert-danger"/>
					  <s:textarea id="message" cssClass="comment_textarea form-control" placeholder="%{getText('global.your_comment')}" name="message" rows="5" cols="50"/>
	      		      <s:submit key="global.add_comment"/>				
				    </div>
				  </s:form>
	    </div>
	  </div>
	</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<div class="main">
	  <div class="container">
		<div class="row">
	      <div class="col-md-12">
          
	      	<h3 class="text-center"><s:text name="global.edit_user"/></h3>
            <div class="col-md-offset-1 col-md-10 col-sm-12">
	      	<s:form action="changePassword" namespace="/user" cssClass="form-wrapper alt" theme="simple" enctype="multipart/form-data">
              <div class="form-group">
	      	      <s:label for="oldPassword" key="global.old_password"/>
	              <s:fielderror fieldName="oldPassword" cssClass="alert alert-danger text-center"/>
	      	      <s:password cssClass="" name="oldPassword" placeholder="******"/>
              </div>
              <div class="form-group">
	      	      <s:label for="newPassword" key="global.new_password"/>
	              <s:fielderror fieldName="newPassword" cssClass="alert alert-danger text-center"/>
	      	      <s:password cssClass="" name="newPassword" placeholder="******"/>
              </div>
			  <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
              <div class="form-group">
	      	      <s:submit key="global.change_password" cssClass="btn btn-form"/>
              </div>
	      	</s:form>
            </div>
	      </div>
		</div>  
	  </div>
	</div>
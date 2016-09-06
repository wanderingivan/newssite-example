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
	      	<s:form action="editUser" namespace="/user" cssClass="form-wrapper alt" theme="simple" enctype="multipart/form-data">
              <div class="form-group">
	              <s:fielderror fieldName="user.username" cssClass="alert alert-danger text-center"/>
	      	      <s:label for="user.username" key="global.username"/>
	      	      <s:textfield name="user.username" value="%{user.username}"/>
              </div>
              <div class="form-group">
	              <s:fielderror fieldName="user.email" cssClass="alert alert-danger text-center"/>
	      	      <s:label for="user.email" key="global.email"/>
	      	      <s:textfield name="user.email" value="%{user.email}"/>
              </div>
              <div class="form-group">
	              <s:fielderror fieldName="details" cssClass="alert alert-danger text-center"/>
	      	      <s:label for="details" key="global.details"/>
	      	      <s:textarea name="details" value="%{user.details}"/>
              </div>
              <div class="form-group">
	      	      <s:label for="profilePic" key="global.profilePic"/>
	      	      <s:file name="profilePic"  accept="image/jpeg, image/png"/>
              </div>
              <s:hidden name="user.imagePath" value="%{user.imagePath}"/>
			  <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
			  <s:hidden name="user.id" value="%{user.id}"/>
              <div class="form-group">
	      	      <s:submit key="global.edit"/>
              </div>
	      	</s:form>
            </div>
	      </div>
		</div>  
	  </div>
	</div>
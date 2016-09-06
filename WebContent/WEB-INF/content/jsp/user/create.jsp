<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<div class="main">
	  <div class="container">
		<div class="row">
	      <div class="col-md-12">
          
            <div class="col-md-offset-1 col-md-10 col-sm-12">
	      	<h3 class="text-center"><s:text name="global.sign_up"/></h3>
	      	<s:form action="createUser" cssClass="form-wrapper alt" theme="simple" namespace="/user" enctype="multipart/form-data">
              <div class="form-group">
	      	    <s:label for="user.username" key="global.username"/>
	            <s:fielderror fieldName="user.username" cssClass="alert alert-danger"/>
	      	    <s:textfield name="user.username"/>
              </div>
              <div class="form-group">
	      	    <s:label for="user.email" key="global.email"/>
	            <s:fielderror fieldName="user.email" cssClass="alert alert-danger"/>
	      	    <s:textfield name="user.email"/>
              </div>
              <div class="form-group">
	      	    <s:label for="details" key="global.description"/>
	            <s:fielderror fieldName="details" cssClass="alert alert-danger"/>
	      	    <s:textarea name="details"/>
              </div>
              <div class="form-group">
	      	    <s:label for="user.password" key="global.password"/>
	            <s:fielderror fieldName="user.password" cssClass="alert alert-danger"/>
	      	    <s:password name="user.password" placeholder="******"/>
              </div>
              <div class="form-group">
	      	    <s:label for="profilePic" key="global.profilePic"/>
	      	    <s:file name="profilePic"  accept="image/jpeg, image/png"/>
              </div>
              <s:token/>
			  <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
              <div class="form-group">
	      	    <s:submit key="global.sign_up" cssClass="btn btn-form"/>
              </div>
	      	</s:form>
            </div>
	      </div>
		</div>  
	  </div>
	</div>
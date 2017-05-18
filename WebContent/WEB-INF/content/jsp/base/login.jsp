<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

 <div class="login-form">
 <div class="row">
	<div class="col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6">
  <div class="form-wrapper">
	<p style="color:#ffa500"><s:text name="global.login_error"/></p>
    <form class="form-inline" action="/NewsSite/login" style="simple" method="POST" name="login">
      <div class="form-group">
        <i class="fa fa-user"></i>
        <s:textfield id="f_username" name="username" value="" placeholder="%{getText('global.username')}"/>
      </div>
      <div class="form-group">
        <i class="fa fa-key"></i>
        <s:password  id="f_password"  name="password" placeholder="%{getText('global.password')}"/>
      </div>
	  <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
	  <div class="form-group">
	    <button id="loginSubmit" type="submit" class="btn btn-form" style="width:100%;"><s:text name="global.log_in"/></button>
      </div>
    </form>
  </div>
  </div>
 </div>
 </div>

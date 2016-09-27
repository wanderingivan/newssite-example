<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
  <div class="error">
	<h1 class="code">403</h1>
	<p>Sorry you do not have permission to view this resource.</p>
	<s:a action="latest" namespace="/index">Go Back</s:a>
  </div>
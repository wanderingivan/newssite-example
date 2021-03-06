<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
	<!-- Set Up Struts2 Jquery Plugin -->
	<sj:head loadFromGoogle="true"/>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/jquery.slick/1.5.7/slick.css"/>
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/jquery.slick/1.5.7/slick-theme.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newssite.css"/>
	
	<title><tiles:insertAttribute name="title"/></title>

  </head>
  <body>


  	<div class="container">
      <tiles:insertAttribute name="header"/>
	  <tiles:insertAttribute name="nav"/>
	</div>
	<div class="container">
	  <div class="mainContent">
	  <tiles:insertAttribute name="main"/>
	  </div>
    </div>
    <div class="container-fluid">
      <tiles:insertAttribute name="footer"/>
    </div>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/newssite.min.js"></script>
  </body>
</html>
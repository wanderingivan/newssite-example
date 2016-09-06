<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<s:url id="localeEN" includeParams="get">
      <s:param name="request_locale">en</s:param>
    </s:url>
    <s:url id="localeBG" includeParams="get">
      <s:param name="request_locale">bg</s:param>
    </s:url>
      <div class="header">
		<div class="row">
		  <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
			<s:a action="latest" namespace="/index" class="logo">
			  News
			  <strong>Site</strong>
			  <span>A News Site Theme</span>
			</s:a>
		  </div>
		  <div class="col-md-2 col-sm-2 col-xs-12">
		  	<ul class="list-inline text-center">
		  		<li style="padding-right:0px;"><s:a href="%{localeBG}">Български</s:a></li>
		  		<li style="padding-right:0px;"><s:a href="%{localeEN}">English</s:a></li>
		  	</ul>
		  </div>
		</div>
	  </div><!-- /Header -->
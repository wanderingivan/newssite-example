<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
         <s:iterator value="articles" end="3">
           <s:url action="loadArticle" namespace="/article" var="loadArticle">
         	 <s:param name="headline">
         	   <s:property value="key"/>
         	 </s:param>
           </s:url>
           <s:url action="loadImage" namespace="/util" var="loadImage">
         	 <s:param name="path">
         	   <s:property value="value"/>	
         	 </s:param>
           </s:url>
           <div class="row">
             <div class="small_cat_content">
         	   <div class="smallcatimg_container">
         	     <s:a href="%{loadArticle}">
         	       <img alt="Article Image" src="<s:property value='#loadImage'/>">
         	       <h5 class="text-center"><s:property value="key"/></h5>
         	     </s:a>
         	   </div>
         	   <s:a href="%{loadArticle}"><h3 class="post_title"><s:property value="key"/></h3></s:a>
             </div>
           </div>
         </s:iterator>


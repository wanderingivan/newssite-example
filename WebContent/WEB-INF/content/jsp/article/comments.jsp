<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<style>

							.fa-fa {
  								font-family: FontAwesome, 'Helvetica Neue', Helvetica, Arial, sans-serif !important;
							}
							
</style>
 	            <div class="comment-count">
 	              <h4 class="text-right">
 	                <s:if test="comments.size() > 1">
 	                  <span class="colored">${comments.size()}</span>&nbsp;<s:text name="global.comments"/>
 	                </s:if>
 	                <s:elseif test="comments.size() == 1">
 	        	  	  <span class="colored">1</span>&nbsp;<s:text name="global.comment"/>
 	                </s:elseif>
 	                <s:else>
 	          	      <s:text name="global.be_the_first_to"/>&nbsp;<span class="colored"><s:text name="global.comment"/></span>&nbsp;!
 	                </s:else>
 	              </h4>
 	            </div>
 	          
 	            <s:if test="comments.size() > 0"> 
 	        	  <div class="comment-list">
 	         	    <s:iterator value="comments">
 	          	      <s:url action="loadUser" namespace="/user" var="showUser">
		                <s:param name="username">
			    	      <s:property value="poster.username"/>
		          	    </s:param>
				      </s:url>
				      <s:url action="loadImage" namespace="/util" var="loadUserImage">
    			        <s:param name="path">
      	  			      <s:property value="poster.imagePath"/>
        		        </s:param>
    			      </s:url>
    			      <s:url action="upvote" namespace="/message" var="upvote">
    			      	<s:param name="commentId">
    			      		<s:property value="id"/>
    			      	</s:param>
    			      </s:url>
    			      <s:url action="downvote" namespace="/message" var="downvote">
    			      	<s:param name="commentId">
    			      		<s:property value="id"/>
    			      	</s:param>
    			      </s:url>
 	                  <div class="media">
 	          	        <div class="media-left">
 	          	          <img class="media-object" src="<s:property value='#loadUserImage'/>"/>
 	          	        </div>
 	          	        <div class="media-body">
 	          	          <p>${message}</p>
 	          	        </div>
 	          	        <div class="media-right">
 	          	        	<s:form id="upvoteForm%{id}" action ="upvote" theme="simple" namespace="/message">
 	          	        		<s:hidden name="commentId">
 	          	        		   <s:param name="value" value="%{id}"/>
 	          	        		</s:hidden>
 	          	        		<s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
 	          	        		<div class="type-button">
	 	          	        	  <sj:a formIds="upvoteForm%{id}" id="upvoteLink%{id}" targets="r" onBeforeTopics="checkCookie" onCompleteTopics="completeVote" onErrorTopics="errorVote" ><i class="fa fa-arrow-circle-up"></i></sj:a>
 	          	        		</div>
 	          	        	</s:form>
 	          	        	<s:div id="cID%{id}"><s:property value="votes"/></s:div>
 	          	        	<s:form  id="downvoteForm%{id}" action ="downvote" namespace="/message" theme="simple">
 	          	        		<s:hidden name="commentId">
 	          	        		   <s:param name="value" value="%{id}"/>
 	          	        		</s:hidden>
 	          	        		<s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
	 	          	        	<sj:a formIds="downvoteForm%{id}" id="downvoteLink%{id}" targets="r" onBeforeTopics="checkCookie" onCompleteTopics="completeVote" onErrorTopics="errorVote"><i class="fa fa-arrow-circle-down"></i></sj:a>
 	          	        	</s:form> 	          	        	
 	          	        </div>
 	          	        <div class="row">
 	          	        	<div class="col-md-4 col-sm-4 col-xs-12"><span class="fa fa-user"></span>&nbsp;<s:a id="poster" class="media-heading" href="%{showUser}">${poster.username}</s:a></div>
 	          	        	<div class="col-md-6 col-sm-4 col-xs-12"><span class="fa fa-clock-o"></span><s:date name="posted" nice="true"/></div>
 	          	        	<div class="col-md-2 col-sm-4 col-xs-12"><span class="fa fa-pencil"></span><a href="#commentForm" class="btn-reply">Reply</a></div>
 	          	        </div>
 	                  </div>
 	                </s:iterator>
 	              </div>
 	            </s:if>
  <script type ="text/javascript">$(document).ready(addCookieHandlers);</script>
  <script>$(document).ready(replyListener)</script>
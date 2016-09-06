<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<s:url action="loadImage" namespace="/util" var="loadImage">
      <s:param name="path">
        <s:property value="user.imagePath"/>
      </s:param>
    </s:url>
    <div class="userMain">
      <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
          <div class="controls">
      	    <sec:authorize access="isAuthenticated() and (hasRole('ROLE_ADMIN') or authentication.name=='${user.username}')">
      	      <a data-toggle="tab" href="#user"><s:text name="global.show_profile"/></a>
      	      <a data-toggle="tab" href="#editForm"><s:text name="global.edit_profile"/></a>
      	    </sec:authorize>
          </div>
          <div class="tab-content">
           	<div  id="user" class="tab-pane fade in active">
      	  	  <h2 class="user_title"><s:text name="global.profile"/></h2>
      	  	  <div class="user_main">
      	        <div class="user_main_right">
      	  		<sec:authorize access="isAuthenticated() and (hasRole('ROLE_ADMIN') or authentication.name=='${user.username}')">
      	  			<s:url action="loadUserMessages" namespace="/message" var="loadMessages">
      	  				<s:param name="username" value="%{user.username}"/>
      	  			</s:url>
      	  			<s:a href="%{loadMessages}"><s:text name="global.your_messages"/></s:a>
      	  		</sec:authorize>
			      <h4><s:text name="global.about_me"/></h4>
			      <p>${user.details}</p>
 	            <sec:authorize access="isAuthenticated() and authentication.name !='${user.username}'">
      	          <div  id="sendButton" class="btn btn-showForm"><s:text name="global.send_message"/></div>
      	          <div  class="sendForm form-group" style="display:none">
      	        	<s:form action="sendMessage" namespace="/message" theme="simple">
      	        		<s:hidden name="receiver">
      	        		  <s:param name="value">
      	        		    <s:property value="user.username"/>
      	        		  </s:param>
      	        		</s:hidden>
      	        		<s:textarea name="message" placeholder="%{getText('global.your_message')}" style="display:block"/>
      	        	    <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
      	        		<s:submit key="global.send_message" />
      	        	</s:form>
      	          </div>
      	        </sec:authorize>      	    
      	        <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
      	          <s:url action="delete" namespace="/user" var="delete">
      	            <s:param name="username" value="%{user.username}"/>
      	          </s:url>
      	          <s:a cssClass="btn btn-showForm" href="%{delete}">Delete this user</s:a>
      	        </sec:authorize>
      	        </div>
      	    	<div class="user_main_left">
      	      	  <div class="user_img_container">
      	            <img class="thumbnail" alt="!missing" src="<s:property value='#loadImage'/>"/>
      	      	  </div>
      	      	  <div class="user_info">
      	            <p><s:text name="global.signed_up"/>&nbsp;<span class="fa fa-calendar"></span><s:date name="user.createdOn" format="dd/MM/yyyy"/></p>
      	          </div>
      	        </div>
      	      </div>
      	  	</div>
      	  	<sec:authorize access="isAuthenticated() and (hasRole('ROLE_ADMIN') or authentication.name=='${user.username}')">
      	  	<div id="editForm" class="tab-pane fade">
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
	      	      <s:submit key="global.edit"  cssClass="btn btn-form"/>
              </div>
	      	</s:form>
	      	<div class="password-link">
      	  	  <sec:authorize access="authentication.name=='${user.username}'">
      	  		<s:a action="changePasswordPage" namespace="/user" cssClass="btn btn-showForm"><s:text name="global.change_password"/></s:a>
      	  	  </sec:authorize>
      	  	</div>
      	  	</div>
      	  	</sec:authorize>
          </div>
      	  <s:if test="user.articlesWritten.size() > 0">
      	  <div class="row">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
          	  <div class="user_articles">
                <h3><s:text name="global.articles_contributed"/></h3>
                <s:iterator value="user.articlesWritten">
                  <s:url action="loadArticle" namespace="/article" var="loadArticle">
		  	  	    <s:param name="headline">
		  	          <s:property value="headline"/>
		  	  		</s:param>
		  	  	  </s:url>
		  	  	  <s:url action="loadImage" namespace="/util" var="loadImage">
		  	        <s:param name="path">
		  	          <s:property value="imagePath"/>
		  	        </s:param>
		  	      </s:url>
                  <div class="media">
              		<div class="media-left">
                	  <s:a href="%{loadArticle}"><img src="<s:property value='#loadImage'/>" alt="img"/></s:a>
              		</div>
              		<div class="media-right">
                	  <s:a href="%{loadArticle}"><h4 class="media-heading">${headline}</h4></s:a>
                	  <p>${caption}</p>
              	    </div>
            	  </div>
            	</s:iterator>
          	  </div>
        	</div>
      	  </div>
      	  </s:if>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
        <s:if test="user.comments.size()>0">
          <div class="user_comments">
            <h3 class="text-center"><s:text name="global.recent_comments"/></h3>
            <s:iterator value="user.comments">
              <s:url action="loadArticle" namespace="/article" var="loadArticle">
		  	  	<s:param name="headline">
		  	      <s:property value="article.headline"/>
		  	  	</s:param>
		  	  </s:url>
		  	  <s:url action="loadImage" namespace="/util" var="loadImage">
		  	    <s:param name="path">
		  	      <s:property value="article.imagePath"/>
		  	    </s:param>
		  	  </s:url>
              <div class="media">
                <s:a href="%{loadArticle}"><h4 class="media-heading">${article.headline}</h4></s:a>
                <div class="media-left">
                  <s:a href="%{loadArticle}"><img src="<s:property value='#loadImage'/>" alt="img"/></s:a>
                </div>
                <div class="media-right">
                  <p>${message}</p>
                  <p><span class="fa fa-clock-o"></span><s:date name="posted" nice="true"/></p>
                </div>
              </div>
            </s:iterator>
          </div>
          </s:if>
        </div>
      </div>
    </div><!-- /Main -->
    <script>
      $(document).ready(function(){
      	$("#sendButton").click(function(){
      	  $(".sendForm").toggle(750);
      	});
      });
    </script>
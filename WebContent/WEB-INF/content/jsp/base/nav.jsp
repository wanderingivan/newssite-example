<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


	<div class="navarea">
	  <nav class="navbar navbar-default" role="navigation">
          
		<div class="container-fluid">
      <div class="navbar-header">
        <button  class="navbar-toggle collapsed btn-toggle" data-target="#navbar" data-toggle="collapse" type="button" aria-expanded="false">
          <span class="sr-only"><s:property value="getText('global.navigation')"/></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>          
		  <div id="navbar" class="navbar-collapse collapse">
          
            <ul class="nav navbar-nav top_nav">
          
			  <li><s:a action="politics" namespace="/index" class="btn-category"><s:text name="global.politics"/></s:a></li>
			  <li><s:a action="international" namespace="/index" class="btn-category"><s:text name="global.international"/></s:a></li>
			  <li><s:a action="sports" namespace="/index" class="btn-category"><s:text name="global.sports"/></s:a></li>
			  <li><s:a action="entertainment" namespace="/index" class="btn-category"><s:text name="global.entertainment"/></s:a></li>
          
		      <li class="hidden-xs">
		        <a id="t" class="btn-category" href="#"><i class="fa fa-search-minus"></i></a>
		      </li>
		      <li class="nav-search">
		      	<s:form theme="simple" action="search" namespace="/index">
		      		<s:textfield class="search-input" name="query" placeholder="%{getText('global.search')}"/>
		      		<s:hidden name="type" value="article"/>
			      	<s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
		      		<button type="submit" class="search-submit"><i class="fa fa-search-minus"></i></button>
		      	</s:form>
		      </li>

			  <sec:authorize access="isAnonymous()">
  		      <li class="user-control">
  		        <a data-toggle="modal" href="#" data-target="#loginModal" class="btn-category"><s:text name="global.log_in"/></a>
  		      </li>
  		      </sec:authorize>
          
		      <sec:authorize access="isAuthenticated()">
		      	<s:set var="name">
		          <sec:authentication property="principal.username"/>
		      	</s:set>
			  	<s:url action="loadUser" namespace="/user" var="homepage">
		      	  <s:param name="username" value="#name"/>
		      	</s:url>
		      	<li class="user-control">	      
		      	  <s:a href="%{homepage}" class="btn-category"><s:text name="global.your_homepage"/></s:a>
		      	</li>
 		        <sec:authorize access="hasRole('ROLE_ADMIN')">
		          <li class="user-control"><s:a action="welcome" namespace="/admin" class="btn-category"><s:text name="global.admin"/></s:a></li>
		        </sec:authorize> 		    	  
		        <sec:authorize access="hasRole('ROLE_WRITER')">
		          <li class="user-control"><s:a action="createArticlePage" class="btn-category" namespace="/article"><i class="fa fa-plus-circle"></i>&nbsp;Article</s:a>
		        </sec:authorize>
		      	<li class="user-control">
		        <form action="/NewsSite/logout" method="POST">
  				  <input class="btn-logout" type="submit" value="Log out" />
    			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    </form>
			    </li>
		      </sec:authorize>

            </ul>
          </div>
        </div>
      </nav>
    </div>
    <!-- /Navbar -->
    <!-- Modal -->
    <div id="loginModal" class="modal fade" role="dialog">
      <div class="modal-dialog">

        <div class="modal-content">
          <div class="modal-header">
          	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="text-center">Login</h4>
          </div>
          
          <div class="modal-body">

 	        <div class="alert alert-info">
	  	     Premade accounts are username1 - user account  and username2 - writer account with password <i>password</i>
	        </div>
         	<div class="form-wrapper">
         		<form action="/NewsSite/login" method="POST" name="login" class="form-inline">
         		  <div class="form-group">
         		    <i class="fa fa-user"></i>
         		  	<s:textfield name="username" value="" placeholder="%{getText('global.username')}"/>
         		  </div>
				  <div class="form-group">
         		    <i class="fa fa-key"></i>
         		  	<s:password name="password" placeholder="%{getText('global.password')}"/>
         		  </div>
			      <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
         		  <s:submit key="global.log_in" cssClass="btn btn-form"/>
         		</form>
         	</div>
         	 
          	<h5 class="text-center"><s:text name="global.or"/></h5>
          	<h4 class="text-center"><s:text name="global.sign_up"/></h4>
          
         	<div class="form-wrapper">
		      	<s:form theme="simple" action="createUser" namespace="/user" enctype="multipart/form-data">
         		  <div class="form-group">
         		    <i class="fa fa-user"></i>
	    	  	  	<s:textfield name="username" value="" placeholder="%{getText('global.username')}"/>
         		  </div>
         		  <div class="form-group">
         		    <i class="fa fa-envelope"></i>
	      	  	  	<s:textfield name="email" placeholder="%{getText('global.email')}"/>
         		  </div>
         		  <div class="form-group">
         		    <i class="fa fa-pencil-square-o"></i>
	      	      	<s:textarea name="description" placeholder="%{getText('global.description')}"/>
         		  </div>
         		  <div class="form-group">
         		    <i class="fa fa-key"></i>
	      	  	  	<s:password name="password" placeholder="%{getText('global.password')}"/>
         		  </div>
	      	  	  <s:label for="profilePic" key="global.profilePic"/>
	      	  	  <s:file name="profilePic"  accept="image/png"/>
			      <s:hidden name="%{#attr._csrf.parameterName}" value="%{#attr._csrf.token}"/>
	      	  	  <s:submit key="global.sign_up" cssClass="btn btn-form"/>
	      		</s:form>
         	</div> 

          </div>
          
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>          
          </div>
          
        </div>
        
      </div>
    </div>
    <!-- /Modal -->
    <script>
      $(document).ready(function(){
	    $("#t").click(function(){
		  $('.nav-search').toggle(750);
		  $('.user-control').toggle(750);
		 });
	   });
     </script>
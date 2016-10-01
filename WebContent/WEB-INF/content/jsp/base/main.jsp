<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

		<div class="row">
		  <div class="col-lg-6 col-md-6 col-sm-6 col-sm-push-3 col-xs-12">
			<h2 class="category_title">
			  <span class="bold_line"><span></span></span><span class="solid_line"></span>
			  <s:a href="" class="title_text"><s:text name="global.%{carouselCategory}"/></s:a>
			</h2>							  
			<div class="main_middle">
			  <div class="slick_slider_main">
			   <s:subset source="carousel" count="5">
			    <s:iterator>
        	  	  <s:url action="loadArticle" namespace="/article" var="articleUrl">
     		        <s:param name="headline" value="%{headline}"/>
     		      </s:url>
      		  	  <s:url action="loadImage" namespace="/util" var="loadImage">
      			    <s:param name="path" value="imagePath"/>
      			  </s:url>
			      <div>
			      	<s:a href="%{articleUrl}"><img src="<s:property value='#loadImage'/>" style="height:320px;width:100%;" alt="Article Image"/></s:a>
			      	<h2><s:a href="%{articleUrl}" class="carousel-headline"><s:property value="headline"/></s:a></h2>
			      	<p class="cat-p"><s:property value="caption" /></p>
			      </div>
			    </s:iterator>
			   </s:subset>
			  </div>
			</div>
		  </div>
		  <div class="col-lg-3 col-md-3 col-sm-3 col-sm-pull-6 col-xs-12">
			<div class="single_category">
			  <h2 class="category_title"><span class="bold_line"><span></span></span><span class="solid_line"></span><a href="" class="title_text"><s:text name="global.most_commented"/></a></h2>
			  <s:url var="loadMostCommented" action="loadCategory" namespace="/article">
			    <s:param name="category">mostComments</s:param>
			  </s:url>
			  <sj:div href="%{loadMostCommented}" indicator="indicatorComments">
			    <img id="indicatorComments" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="width:150px;height:150px;display:none"/>			    
			  </sj:div>
			</div>		  
		  </div>
		  <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
			<div class="single_category">
			  <h2 class="category_title"><span class="bold_line"><span></span></span><span class="solid_line"></span><a href="" class="title_text"><s:text name="global.most_read"/></a></h2>
			  <s:url var="loadMostRead" action="loadCategory" namespace="/article">
			    <s:param name="category">mostRead</s:param>
			  </s:url>
			  <sj:div href="%{loadMostRead}" indicator="indicatorRead">
			    <img id="indicatorRead" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="width:150px;height:150px;display:none"/>			    
			  </sj:div>
			</div>			  
		  </div>
		</div>
		<div class="row">
		  <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
		  	<div class="other_categories">
		  	
			 <div class="row">
			  <div class="col-md-12">
		  	  <div class="single_category_lg">
		  	    <s:if test="category1.size() > 0">
		  	  	  <s:set var="category" value="category1[0].getCategory()"/>
		  	  	</s:if> 
			    <h2 class="category_title">
			      <span class="bold_line"><span></span></span><span class="solid_line"></span>
			      <s:a action="%{category}" namespace="/index" class="title_text">
			      	<s:text name="global.%{category}"/>
			      </s:a>
			    </h2>			    
		  	  	<s:subset source="category1" count="3">
		  	  	  <s:iterator status="stat">
      			    <s:url action="loadArticle" namespace="/article" var="articleUrl">
      			  	  <s:param name="headline" value="%{headline}"/>
      			  	</s:url>
      			    <s:url action="loadImage" namespace="/util" var="loadImage">
      			  	  <s:param name="path" value="%{imagePath}"/>
      			    </s:url>
		  	  	    <s:if test="%{#stat.isFirst()}">
		  	  	      <div class="single_category_lg_left">
      			        <div class="largecatimg_container">
      			         <s:a href="%{articleUrl}"><img alt="!missing" src="<s:property value='#loadImage'/>"/></s:a>
      			        </div>
      			        <s:a href="%{articleUrl}"><h2 class="post_title"><s:property value="headline"/></h2></s:a>
      			        <p class="cat-p"><s:property value="caption"/></p>		  	  	  
		  	  	      </div>
		  	        </s:if>
		  	  	    <s:else>
		  	  	      <div class="single_category_lg_right">
      			        <div class="media">
      			          <s:a href="%{articleUrl}" class="media-left">
      			   	        <img alt="!missing" src="<s:property value='#loadImage'/>"/> 
      			          </s:a>
      			          <div class="media-body">
      			            <s:a href="%{articleUrl}"><h4 class="post_title"><s:property value="headline"/></h4></s:a>
	  			      		<p class="cat-p hidden-xs">
	  			        	  <s:if test="caption.length > 75">
	  			          		<s:property value="caption.substring(0,75)"/>...
	  			        	  </s:if>
	  			        	  <s:else>
	  			        	    <s:property value="caption"/>
	  			              </s:else>
	  			            </p>
      			          </div>
      			        </div>
      			      </div>
      			    </s:else>		  	  	   
		  	  	  </s:iterator>
		  	  	</s:subset> 
		  	  </div>			  
			  </div>
			 </div>
		  	  
			<div class="row">
			
			  <div class="col-md-12">
			  
		  	  <div class="multiple_category">
		  	  
		  	    <div class="multiple_category_single">
		  	    <s:if test="category2.size() > 0">
		  	  	  <s:set var="category" value="category2[0].getCategory()"/>
		  	  	</s:if> 
			    <h2 class="category_title">
			      <span class="bold_line"><span></span></span><span class="solid_line"></span>
			      <s:a action="%{category}" namespace="/index" class="title_text">
			      	<s:text name="global.%{category}"/>
			      </s:a>
			    </h2>			    
          		<s:subset source="category2" count="3">
		  	  	 <s:iterator status="stat">
      			    <s:url action="loadArticle" namespace="/article" var="articleUrl">
      			  	  <s:param name="headline" value="%{headline}"/>
      			  	</s:url>
      			    <s:url action="loadImage" namespace="/util" var="loadImage">
      			  	  <s:param name="path" value="%{imagePath}"/>
      			    </s:url>
      			  <s:if test="#stat.isFirst()">	
				  <div class="small_cat_content">
				    <div class="smallcatimg_container">
				      <s:a href="%{articleUrl}">
						<img alt="img" src="<s:property value='#loadImage'/>">
				      </s:a>
					</div>
				    <s:a href="%{articleUrl}">
					  <h3 class="post_title"><s:property value="headline"/></h3>
					</s:a>							
					<p class="cat-p"><s:property value="caption"/></p>
				  </div>      			    
      			  </s:if>
      			  <s:else>
				  <div class="media">
				    <s:a href="%{articleUrl}" class="media-left">
				      <img alt="img" src="<s:property value='#loadImage'/>"/>
				    </s:a>
					<div class="media-body">
      			      <s:a href="%{articleUrl}"><h4 class="post_title"><s:property value="headline"/></h4></s:a>
	  			      <p class="cat-p hidden-xs">
	  			        <s:if test="caption.length > 75">
	  			          <s:property value="caption.substring(0,75)"/>...
	  			        </s:if>
	  			        <s:else>
	  			        	<s:property value="caption"/>
	  			        </s:else>
	  			      </p>
	  			    </div>
				  </div>      			  
      			  </s:else>	  	  	  
		  	  	 </s:iterator>
		  	  	</s:subset>
		  	  	</div>

		  	    <div class="multiple_category_single">
		  	    <s:if test="category3.size() > 0">
		  	  	  <s:set var="category" value="category3[0].getCategory()"/>
		  	  	</s:if> 
			    <h2 class="category_title">
			      <span class="bold_line"><span></span></span><span class="solid_line"></span>
			      <s:a action="%{category}" namespace="/index" class="title_text">
			      	<s:text name="global.%{category}"/>
			      </s:a>
			    </h2>			    
          		<s:subset source="category3" count="3">
		  	  	 <s:iterator status="stat">
      			    <s:url action="loadArticle" namespace="/article" var="articleUrl">
      			  	  <s:param name="headline" value="%{headline}"/>
      			  	</s:url>
      			    <s:url action="loadImage" namespace="/util" var="loadImage">
      			  	  <s:param name="path" value="%{imagePath}"/>
      			    </s:url>
      			  <s:if test="#stat.isFirst()">	
				  <div class="small_cat_content">
				    <div class="smallcatimg_container">
				      <s:a href="%{articleUrl}">
						<img alt="img" src="<s:property value='#loadImage'/>">
				      </s:a>
					</div>
				    <s:a href="%{articleUrl}">
					  <h3 class="post_title"><s:property value="headline"/></h3>
					</s:a>							
					<p class="cat-p"><s:property value="caption"/></p>
				  </div>      			    
      			  </s:if>
      			  <s:else>
				  <div class="media">
				    <s:a href="%{articleUrl}" class="media-left">
				      <img alt="img" src="<s:property value='#loadImage'/>"/>
				    </s:a>
					<div class="media-body">
      			      <s:a href="%{articleUrl}"><h4 class="post_title"><s:property value="headline"/></h4></s:a>
	  			      <p class="cat-p hidden-xs">
	  			        <s:if test="caption.length > 75">
	  			          <s:property value="caption.substring(0,75)"/>...
	  			        </s:if>
	  			        <s:else>
	  			        	<s:property value="caption"/>
	  			        </s:else>
	  			      </p>
	  			    </div>
				  </div>      			  
      			  </s:else>	  	  	  
		  	  	 </s:iterator>
		  	  	</s:subset>
		  	  	</div>
		  	  	
		  	  </div>			  
			  
			  </div>
			
			 </div>
		  	 <div class="row">
		  	  <div class="col-md-12">
		  	  <div class="single_category_lg">
		  	    <s:if test="category4.size() > 0">
		  	  	  <s:set var="category" value="category4[0].getCategory()"/>
		  	  	</s:if> 
			    <h2 class="category_title">
			      <span class="bold_line"><span></span></span><span class="solid_line"></span>
		  	      <s:if test="category4.size() > 0">
			        <s:a action="%{category}" namespace="/index" class="title_text">
			      	  <s:text name="global.%{category}"/>
			        </s:a>
			      </s:if>
			    </h2>			    
		  	  	<s:subset source="category4" count="3">
		  	  	  <s:iterator status="stat">
      			    <s:url action="loadArticle" namespace="/article" var="articleUrl">
      			  	  <s:param name="headline" value="%{headline}"/>
      			  	</s:url>
      			    <s:url action="loadImage" namespace="/util" var="loadImage">
      			  	  <s:param name="path" value="%{imagePath}"/>
      			    </s:url>
		  	  	    <s:if test="%{#stat.isFirst()}">
		  	  	      <div class="single_category_lg_right">
      			        <div class="largecatimg_container">
      			         <s:a href="%{articleUrl}"><img alt="!missing" src="<s:property value='#loadImage'/>"/></s:a>
      			        </div>
      			        <s:a href="%{articleUrl}"><h2 class="post_title"><s:property value="headline"/></h2></s:a>
      			        <p class="cat-p"><s:property value="caption"/></p>		  	  	  
		  	  	      </div>
		  	        </s:if>
		  	  	    <s:else>
		  	  	      <div class="single_category_lg_left">
      			        <div class="media">
      			          <s:a href="%{articleUrl}" class="media-left">
      			   	        <img alt="!missing" src="<s:property value='#loadImage'/>"/> 
      			          </s:a>
      			          <div class="media-body">
      			            <s:a href="%{articleUrl}"><h4 class="post_title"><s:property value="headline"/></h4></s:a>
	  			      		<p class="cat-p hidden-xs">
	  			        	  <s:if test="caption.length > 75">
	  			          		<s:property value="caption.substring(0,75)"/>...
	  			        	  </s:if>
	  			        	  <s:else>
	  			        		<s:property value="caption"/>
	  			        	  </s:else>
	  			      		</p>
      			          </div>
      			        </div>
      			      </div>
      			    </s:else>		  	  	   
		  	  	  </s:iterator>
		  	  	</s:subset> 
		  	   </div>			  
		  	   </div>
		  	 </div> 

		  	</div>
		  </div>
		  <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
		    <div class="sidebar_column">
			  <ul class="nav nav-tabs sidebar-tabs" role="tablist">
				<li class="active" role="presentation">
				  <a href="#mostPopular" aria-controls="home" role="tab"
					 data-toggle="tab" aria-expanded="true"><s:text name="global.most_popular"/></a>
				</li>
				<li class="" role="presentation">
				  <a href="#mostRecent" aria-controls="messages" role="tab"
				     data-toggle="tab" aria-expanded="true"><s:text name="global.recent_comments"/></a>
				</li>
			  </ul>
			  <div class="tab-content">
			    <s:url action="loadComments" namespace="/message" var="ajaxPopular">
			      <s:param name="ordering">
				    popular
				  </s:param>
			    </s:url>
			    <s:url action="loadComments" namespace="/message" var="ajaxLatest">			 
			      <s:param name="ordering">
					latest
                  </s:param>
			    </s:url>
			    <img id="indicator" src="${pageContext.request.contextPath}/images/indicator.gif" alt="Loading..." style="display:none"/>			    
			    <sj:div id="mostPopular" cssClass="tab-pane fade active in" role="tabpanel" indicator="indicator" href="%{ajaxPopular}">

			    </sj:div>
			    <sj:div id="mostRecent" cssClass="tab-pane fade" role="tabpanel"  href="%{ajaxLatest}">

			    </sj:div>
			  </div>
			</div>    		  
		  </div>
		</div>
   <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.slick/1.5.7/slick.min.js"></script>
   <script type="text/javascript">
    $(document).ready(function(){
      $('.slick_slider_main').slick({
        dots: true,
        infinite: true,
		autoplay:true,
		arrows : true,
		draggable : false,
		fade : true,
        speed: 300,
        slidesToShow: 1,
        slidesToScroll: 1
		});
    });
   </script>
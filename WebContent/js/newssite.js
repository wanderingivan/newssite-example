/**
 * 
 */

function setUpForm(){
	$('#addParagraph').click(createInput);
}

function setVotes(id,votes){
	$("#cID"+id).html(votes);
}

function disableControl(id){
	$("#upvoteSubmit"+id).prop('disabled',true);
	$("#downvoteSubmit"+id).prop('disabled',true);
}

function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
	  var c = ca[i];
	  while (c.charAt(0)==' ') c = c.substring(1,c.length);
	  if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
    return null;
}


function getInputHtml(idx){
	return  '<textarea name="product.details['+idx+']" id="detailInput" class="form-control" placeholder="Add more details"></textarea>'
}


function getInputHtml(idx){
	return  '<div class="form-group paragraph"><label for="paragraphs['+idx+']">Paragraph '+(idx+1)+':</label>' +
            	'<textarea style="height:250px;" name="articleParagraphs['+idx+']" value="" id="paragraphInput"/></div>';
}

function getTasksHtml(task){
	return "<li>" +
	          "<div id='task"+task.id+"' class='media' style='display:none'>" +
				  "<div class='media-body'>" +
				    "<p class='media-heading'>"+task.assigner+"</p>" +
				    "<p class='text-muted'>"+task.description+"</p>" +
                    "<p class='text-muted'>"+task.created+"</p>" +
				  "</div>" +
			  "</div>" +
		   "</li>" +
		   "</li><li class='divider'></li>";
}

function getMessageHtml(message){
	return "<li>" +
	          "<div id='message"+message.id+"' class='media' style='display:none'>" +
				  "<div class='media-left'><img src='data:image/jpeg;base64,"+message.sender.imagePath+"'></img></div>" +
				  "<div class='media-body'>" +
				    "<p class='media-heading'>"+message.sender.username+"</p>" +
				    "<p class='text-muted'>"+message.message+"</p>" +
                    "<p class='text-muted'>"+message.sent+"</p>" +
				  "</div>" +
			  "</div>" +
		   "</li>" +
		   "</li><li class='divider'></li>";
}


function populateUnreadMessages(){
	console.log("populating messages")
	var target = $("#messagesDropdown");
	$.ajax({url:'/NewsSite/message/unread-messages',
		    dataType:'html',
		    complete :function(data){console.log(data);}
			}).done(function(html){target.html(html)});
	
}

function populatePendingTasks(){
	var target = $("#dropdownTasks");
     if($("#dropdownTasks li").length === 0){
    	 	$.getJSON('/NewsSite/admin/pendingTasks',function(jd){// XXX FIX THIS URL!!!
    	 		target.empty();
    	 		if(jd.tasks.length == 0){
    	 			target.html("<li class='divider'></li>");
    	 			target.append("<p style='margin-left:15px;color:#22a17d;'>You have no pending tasks</p>");
    	 		}else{
    	 			$.each(jd.tasks,function(index,data){
    	 				target.append(getTasksHtml(data));
    	 				$("#task"+data.id).toggle('slide',{direction:'right'},750);
    	 				$('#task'+data.id).effect("highlight", {color:"rgba(34, 209, 163, 0.5)"}, 1000);
    	 			});
    	 		}
		});
     }
}


function getText(name,text){
	return '"' + name +
	       ' said: ' + text + '"';
}

function replyListener(){
	$('.btn-reply').click(function(){
		var comment = $(this).closest(".media");
		var username = comment.find("#poster").text();
		var text = comment.find('p').text();
		console.log("Username: " +username);
		console.log("Text: " +text);
		$('textarea#message').val(getText(username,text));
	});
	
}


function createInput(){
	var p = $('#articleForm .form-group.paragraph');
	var st = getInputHtml(p.length);
	p.last().after(st);
}

function loadArticleComments(){
	$('#loadComments a').click(function(event){
		var articleHeadline = $('#headline').val();
		var prevMin = $('#min').val();
		$.getJSON('ajaxArticleComments',{
			headline : articleHeadline,
			min : prevMin
		}, function(response){
			if(response !=null){// FIXME Find out how to get the size from the list 
				$.each(response.comments,function(comment){
					$('#loadComments').append(
							
					);
				})
			}else{
				$('#loadComments').empty();
			}
		})
	});
	
}

function addCookieHandlers(){
    $.subscribe("checkCookie", function(event,data){
    	var id =  event.originalEvent.formData[0].value;
    	var cookie = "cID" + id; 
        if(readCookie(cookie) != null){
            event.originalEvent.options.submit =false;
        	disableControl(id);
       }
    })
    
    $.subscribe("completeVote", function(event,data){
    	var id = event.originalEvent.request.responseJSON.commentId;
    	var votes = event.originalEvent.request.responseJSON.votes;
    	createCookie("cID"+id,true,1);
    	setVotes(id,votes);
    	disableControl(id);
    })
}



$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
    var token;
    if (!options.crossDomain) {
      token = $('meta[name="_csrf"]').attr('content');
      if (token) {
        return jqXHR.setRequestHeader('X-CSRF-Token', token);
      }
    }
  });

$(document).ready(function(){

	//Task & Unread Messages count functions
	$.subscribe("addUnreadCount",function(event,data){
		var jd = JSON.parse(event.originalEvent.data);
		$("#unread").html(jd.unread + " Unread");
		}
	);

	$.subscribe("addTasksCount",function(event,data){
		var jd = JSON.parse(event.originalEvent.data);
		$("#tasks").html(jd.message);
		}
	);
	
	$.subscribe('loadLog',function(event,data){
        var result = event.originalEvent.request.responseText;
        $('#log').html(result);
	 });
	
	// Ajax Dropdowns
	$("#dropdownMenuMessages").click(populateUnreadMessages);
	$("#dropdownMenuTasks").click(populatePendingTasks);
	

	
	$("#msgButton").click(function(){
	    $("#msgButton").toggle(200);
	    	$("#msgForm").toggle(300);
	});
    
    $(".fa").click(function(){
    	$(this).closest('.panel').find('.panel-body')
                                 .slideToggle(700);
    });	
    

    loadArticleComments();
    
    
});

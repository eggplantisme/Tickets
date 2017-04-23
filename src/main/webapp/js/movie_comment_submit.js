/**
 * 
 */
function ajax_submit(id) {
	if ($("#comment_content").val() != "") {
		$.post("./" + id, 
				{
					"comment": $("#comment_content").val(),
					"deleteId": ""
				},
				function(data, status) {
					var add_comment = '<li class="row clearfix list-group-item"  id="${comment.commentId}">' +
											'<div class="col-md-2 column">' +
												data.data.username + 
											'</div>' +
											'<div class="col-md-1 column">' +
												'<span class="glyphicon glyphicon-chevron-right"></span>'
											'</div>'
											'<div class="col-md-8 column">' +
												data.data.comment +
											'</div>' +
											'<div class="col-md-1 column">';
					if (data.data.username != "路人") {
						add_comment += '<button onclick="delete_comment(' + data.data.commentId +', ' + data.data.mId + ')">' +
								'<span class="glyphicon glyphicon-trash"></span>' +
									'</button>';
						//TODO 需要获取添加的评论的commentID，但是不知怎么获取，这个id是自己增加的。
					}
					add_comment += '</div></li>';
					$("#comment_field").append(add_comment);
				}
			);
	} else {
		alert("评论不能为空");
	}
}
function delete_comment(commentId, mId) {
	$.post("./" + mId, 
			{
				"comment": "",
				"deleteId": commentId
			},
			function(data, status) {
				$("li").remove("#" + data.data.commentId);
			}
		);
}

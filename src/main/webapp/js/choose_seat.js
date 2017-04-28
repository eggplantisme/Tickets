/**
 * 
 */
var row = 0;
var column = 0;
$(document).ready(function() {
	var seat_num = 0;
	$(".seats").click(function() {
			if ($(this).hasClass("btn-default")) {
				if (seat_num >= 1) {//一人最多定一张
					alert("一次只能订一张哦！^_^");
				} else {
					seat_num++;
					$(this).removeClass("btn-default");
					$(this).addClass("btn-success");
					var rc_data = $(this).attr("name").split('_');
					row = rc_data[0];
					column = rc_data[1];
				}
			} else if ($(this).hasClass("btn-success")) {
				seat_num--;
				$(this).removeClass("btn-success");
				$(this).addClass("btn-default");
				row = 0;
				column = 0;
			}
	});
	
});
function choose_seat(sId, name) {
	if (name == 0) {
		alert("请登录");
	} else {
		if (row == 0 && column == 0) {
			alert("请选择一个座位");
		} else {
			$.post("./" + sId,
					{"seatRow": row,
					"seatcolumn": column},
					function(data,status) {
						window.location.href = data;
					}
			)
		}
	}
}
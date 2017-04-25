/**
 * 
 */

$(document).ready(function() {
	var seat_num = 0;
	$(".seats").click(function() {
		if (seat_num >= 5) {//一人最多定五张
			alert("一次只能订五张哦！^_^");
		} else {
			if ($(this).hasClass("btn-default")) {
				seat_num++;
				$(this).removeClass("btn-default");
				$(this).addClass("btn-success");
			} else if ($(this).hasClass("btn-success")) {
				seat_num--;
				$(this).removeClass("btn-success");
				$(this).addClass("btn-default");
			}
		}
	});
});
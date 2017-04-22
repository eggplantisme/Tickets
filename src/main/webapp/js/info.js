/**
 * 
 */
ori_text = "haha";
$(document).ready(function() {
	$("#update_name").click(function() {
		if($("#update_name").text() == "编辑") {
			ori_text = $("#name").val();
			$("#name").removeAttr("disabled");
			$("#update_name").text("提交");
		} else {
			$("#name").attr("disabled", "disabled");
			$("#update_name").text("编辑");
			if (ori_text != $("#name").val()) {
				$.post("./info", 
						{
					"name" : $("#name").val()
						}, 
						function(data, status) {
							$("#update_name").tooltip("toggle");
							$("#welcome").text("Welcome " + $("#name").val());
						});
			}
		}
		
	});
});

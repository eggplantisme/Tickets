$(function(){
	window.z = 0;
	$$('input').name('submit').hide();
	var user = 0;
	var pass = 0;
	//用户名验证；
	$$('input').name('username').bind('focus',function(){
		$$('.user_put').css('display','block').css('z-index', ''+ window.z++);
		$$('.user_error').css('display','none');
		$$('.user_ok').css('display','none');
	}).bind('blur',function(){
		if( $$(this).val() == '' ) {
			$$('.user_put').css('display','none');
			user = 0;
			$$('input').name('submit').hide();
		}
		else{
			var str = $$(this).val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			if( str.length < 6 ){
				$$('.user_put').css('display','none');
				$$('.user_error').css('display','block');
				$$('.user_ok').css('display','none');
				user = 0;
				$$('input').name('submit').hide();
			}else{
				$$('.user_ok').css('display','block');
				$$('.user_put').css('display','none');
				$$('.user_error').css('display','none');
				user = 1;
				if (user && pass) {
					$$('input').name('submit').show();
				}
			}
		}
	}).bind('keyup',function(){
		if( $$(this).val().replace(/\s/g , '').length >= 6 ){
			$$('.user_ok').css('display','block');
			$$('.user_put').css('display','none');
			$$('.user_error').css('display','none');
			user = 0;
			$$('input').name('submit').hide();
		}else{
			$.ajax({
				url:"",
	            type:"put",
	            contentType: "application/json",
	            data: '{"username" : "' + $$('input').name('username').val() + '", "password" : "'+ $$('input').name('password').val() +'"}',
	            success:function(msg){
	            	if (msg == "username") {
	            		$$('.user_put').css('display','none');
	    				$$('.user_error').css('display','block');
	    				$$('.user_ok').css('display','none');
	    				user = 0;
	    				$$('input').name('submit').hide();
	            	} else if (msg == "password") {
	            		$$('.user_put').css('display','block');
	        			$$('.user_error').css('display','none');
	        			$$('.user_ok').css('display','none');
	        			user = 1;
	            		$$('.pass_put').css('display','none');
	        			$$('.pass_error').css('display','block');
	        			$$('.pass_ok').css('display','none');
	        			pass = 0;
	        			$$('input').name('submit').hide();
	            	} else {
	            		$$('.user_put').css('display','block');
	        			$$('.user_error').css('display','none');
	        			$$('.user_ok').css('display','none');
	        			user = 1;
	            		$$('.pass_put').css('display','none');
	        			$$('.pass_error').css('display','none');
	        			$$('.pass_ok').css('display','block');
	        			pass = 1;
	        			if (user && pass) {
	        				$$('input').name('submit').show();
	        			}
	            	}
	            },
	            error:function(xhr,textstatus,thrown){
	            	//TODO
	            }
			});
			
		}
	});
	
	
	//密码验证
	
	$$('input').name('password').bind('focus',function(){
		$$('.pass_put').css('display','block').css('z-index',''+ window.z++);
		$$('.pass_error').css('display','none');
		$$('.pass_ok').css('display','none');
	}).bind('blur' , function(){
		if( $$(this).val() == '' ){
			$$('.pass_put').css('display','none');
			pass = 0;
			$$('input').name('submit').hide();
		}else if( ! (new RegExp(/^[a-zA-Z0-9_]{6,18}$$/)).test( $$(this).val() ) ){
			$$('.pass_put').css('display','none');
			$$('.pass_error').css('display','block');
			$$('.pass_ok').css('display','none');
			pass = 0;
			$$('input').name('submit').hide();
		}else{	
			$.ajax({
				url:"",
	            type:"put",
	            contentType: "application/json",
	            data: '{"username" : "' + $$('input').name('username').val() + '", "password" : "'+ $$('input').name('password').val() +'"}',
	            success:function(msg){
	            	if (msg == "username") {
	            		$$('.user_put').css('display','none');
	    				$$('.user_error').css('display','block');
	    				$$('.user_ok').css('display','none');
	    				user = 0;
	    				$$('input').name('submit').hide();
	            	} else if (msg == "password") {
	            		$$('.user_put').css('display','block');
	        			$$('.user_error').css('display','none');
	        			$$('.user_ok').css('display','none');
	        			user = 1;
	            		$$('.pass_put').css('display','none');
	        			$$('.pass_error').css('display','block');
	        			$$('.pass_ok').css('display','none');
	        			pass = 0;
	        			$$('input').name('submit').hide();
	            	} else {
	            		$$('.user_put').css('display','block');
	        			$$('.user_error').css('display','none');
	        			$$('.user_ok').css('display','none');
	        			user = 1;
	            		$$('.pass_put').css('display','none');
	        			$$('.pass_error').css('display','none');
	        			$$('.pass_ok').css('display','block');
	        			pass = 1;
	        			if (user && pass) {
	        				$$('input').name('submit').show();
	        			}
	            	}
	            },
	            error:function(xhr,textstatus,thrown){
	            	//TODO
	            }
			});
		}
	}).bind('keyup',function(){
		if( (new RegExp(/^[a-zA-Z0-9_]{6,18}$$/)).test( $$(this).val() )){
			$$('.pass_put').css('display','none');
		}else{
			$$('.pass_put').css('display','block');
		}
	});
});
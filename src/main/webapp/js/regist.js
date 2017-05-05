$(function(){
	window.z = 0;
	$$('input').name('submit').hide();
	var user = 0;
	var pass = 0;
	var cpass = 0;
	var phone = 0;
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
			$('.user_error').text("请输入正确用户名!");
			var str = $$(this).val();
			str = str.replace(/\s/g , '');//输入空格时自动忽略，\s表示空格
			if( str.length < 6 ){
				$$('.user_put').css('display','none');
				$$('.user_error').css('display','block');
				$$('.user_ok').css('display','none');
				user = 0;
				$$('input').name('submit').hide();
			}else{
				$.ajax({
					url:"",
		            type:"put",
		            contentType: "application/json",
		            data: '{"username" : "' + str + '"}',
		            success:function(msg){
		                if (msg == "YES") {
		                	$('.user_error').text("这个名字已经被使用。");
		                	$$('.user_put').css('display','none');
		    				$$('.user_error').css('display','block');
		    				$$('.user_ok').css('display','none');
		    				user = 0;
		    				$$('input').name('submit').hide();
		                } else {
		                	$('.user_error').text("请输入正确用户名!");
		                	$$('.user_ok').css('display','block');
		    				$$('.user_put').css('display','none');
		    				$$('.user_error').css('display','none');
		    				user = 1;
		    				if (user && pass && cpass && phone) {
		    					$$('input').name('submit').show();
		    				}
		                }
		            },
		            error:function(xhr,textstatus,thrown){
		            	//TODO
		            }
				});
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
			$$('.user_put').css('display','block');
			$$('.user_error').css('display','none');
			$$('.user_ok').css('display','none');
			user = 1;
			if (user && pass && cpass && phone) {
				$$('input').name('submit').show();
			}
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
			$$('.pass_put').css('display','none');
			$$('.pass_error').css('display','none');
			$$('.safe').css('display','none');
			$$('.pass_ok').css('display','block');
			pass = 1;
			if (user && pass && cpass && phone) {
				$$('input').name('submit').show();
			}
		}
			
	}).bind('keyup',function(){
		if( (new RegExp(/^[a-zA-Z0-9_]{6,18}$$/)).test( $$(this).val() )){
			$$('.pass_put').css('display','none');
			$$('.safe').css('display','block');
			switch( safeTest($$(this).val()) ){
				case 1:
					$$('.s1').css('background','red');
					$$('.s2').css('background','#ccc');
					$$('.s3').css('background','#ccc');
					$$('.word').text('低').css('color','red');
					break;
				case 2:
					$$('.s1').css('background','orange');
					$$('.s2').css('background','orange');
					$$('.s3').css('background','#ccc');
					$$('.word').text('中').css('color','orange');
					break;
				case 3:
					$$('.s').css('background', 'green');
					$$('.word').text('高').css('color','green');
					break;
			}
		}else{
			$$('.safe').css('display','none');
			$$('.pass_put').css('display','block');
		}
	});
	
	
	function safeTest(str){
		var strlen = str.length;
		var codeCont = 0;
		if( /\d/.test(str) ) codeCont++;
		if( /[a-zA-Z]/g.test(str) ) codeCont++;
		if( /_/g.test(str) ) codeCont++;
		if( strlen < 10 && codeCont == 1) return 1;
		else if( strlen < 10 && codeCont == 2 ) return 2;
		else return 3;
	
	}
	
	//确认密码验证
	
	
	
	$$('input').name('ConfirmPassword').bind('focus',function(){
		$$('.confirm_put').css('display','block');
		$$('.confirm_error').css('display','none');
		$$('.confirm_ok').css('display','none');
	}).bind('blur',function(){
		$$('.confirm_put').css('display','none');
		if( $$(this).val() == '' ) {
			$$('.confirm_error').css('display','none');
			cpass = 0;
			$$('input').name('submit').hide();
		}
		else{
			if( $$(this).val() != $$('input').name('password').val() ){
				$$('.confirm_error').css('display','block');
				cpass = 0;
				$$('input').name('submit').hide();
			}else{
				$$('.confirm_error').css('display','none');
				$$('.confirm_ok').css('display','block');
				cpass = 1;
				if (user && pass && cpass && phone) {
					$$('input').name('submit').show();
				}
			}
		}
		
	});
	//电话验证
	
	
	$$('input').name('PhoneNumber').bind('focus',function(){
	
		$$('.email_put').css('display','block');
		$$('.email_error').css('display','none');
		$$('.email_ok').css('display','none');
	
	}).bind('blur',function(){
		window.ulIfClick = false;
		if( $$(this).val() == '' ){
			$$('.email_put').css('display','none');
			$$('.email_error').css('display','none');
			$$('.email_ok').css('display','none');
			$$('.auto_complete').css('display','none');
			phone = 0;
			$$('input').name('submit').hide();
		}else{
			if( /^1\d{10}$$/.test($$(this).val()) ){
				$$('.email_put').css('display','none');
				$$('.email_error').css('display','none');
				$$('.email_ok').css('display','block');
				phone = 1;
				if (user && pass && cpass && phone) {
					$$('input').name('submit').show();
				}
			}else{			
				$$('.auto_complete li').bind('click',function(){
					$$('input').name('email').val( $$(this).text() );
					if( /^1\d{10}$$/.test($$('input').name('email').val()) ){
						$$('.email_put').css('display','none');
						$$('.email_error').css('display','none');
						$$('.email_ok').css('display','block');
						phone = 1;
						if (user && pass && cpass && phone) {
							$$('input').name('submit').show();
						}
					}
					$$('.auto_complete').css('display','none');
					window.ulIfClick = true;
				});
				setTimeout(function(){
					if(  !window.ulIfClick ){
						$$('.auto_complete').css('display','none');
						$$('.email_put').css('display','none');
						$$('.email_error').css('display','block');
						$$('.email_ok').css('display','none');
					}else{
	
					}
				},200);
				phone = 0;
				$$('input').name('submit').hide();
			}
		}
	}).bind('keyup',function(e){
	
		if( window.nextLi == undefined ) window.nextLi = 0;
		if( !$$(this).val().match(/@/) ){
			if( $$(this).val() == '' )
				$$('.auto_complete').css('display','none');
			else{
				$$('.auto_complete').css('display','block');
				if( e.keyCode != 13 ){
					$$('.auto_complete li').css('background','#eee');
				}
			}
				
			var str = $$(this).val();
			$$('.auto_complete span').text(str);		
		}else{
			$$('.auto_complete').css('display','none');
		}
		
		if(e.keyCode == 40 && $$('.auto_complete').css('display') == 'block'){
			$$('.auto_complete li').eq(window.nextLi++%4).css('background','#ff8').siblings().css('background','#eee');
			
		}
		if(e.keyCode == 13){
			window.nextLi = undefined;
			for(var i = 0; i < $$('.auto_complete li').length(); i++){
				//alert( $$('.auto_complete li').eq(i).css('backgroundColor') );
				if($$('.auto_complete li').eq(i).css('backgroundColor') == 'rgb(255, 255, 136)'){
					$$('input').name('email').val( $$('.auto_complete li').eq(i).text() );
					$$('.auto_complete').css('display','none');
					if( /^\w+@[a-zA-Z0-9]+(\.[a-z]{2,3}){1,2}$$/.test($$(this).val()) ){
						$$('.email_put').css('display','none');
						$$('.email_error').css('display','none');
						$$('.email_ok').css('display','block');
					}
				}
			}
		}
	
	});
});
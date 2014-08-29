<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="static/lib/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" charset="UTF-8"
	href="/static/css/picker.css">
<script src="/static/js/jquery.js"></script>
</head>
<body>
	<div id="mc">
		<div id="mt">Picker</div>
		<div id="mn">
			<span style="margin-right: 20px" data-tab="login" class="active">ç»é</span>
			<span data-tab="register" class="negative">æ³¨å</span>
		</div>
		<div id="msg" data-now=""></div>
		<div id="login_form">
			<form action="/login" method="post"
				onsubmit="return login_check($(this))">
				<div>
					<input name="email" placeholder="é®ç®±">
				</div>
				<div>
					<input name="password" type="password" placeholder="å¯ç ">
				</div>
				<div style="text-align: right">
					<button type="submit" class="btn btn-success">ç»é</button>
				</div>
			</form>
		</div>
		<div id="register_form">
			<form action="/register" method="post"
				onsubmit="return register_check($(this))">
				<div>
					<input name="email" placeholder="é®ç®±"
						onblur="email_unique_check($(this).val(), $('#email_unique_check'))">
				</div>
				<div id="email_unique_check" data-value=false></div>
				<div>
					<input name="name" placeholder="å§å"
						onblur="name_unique_check($(this).val(), $('#name_unique_check'))">
				</div>
				<div id="name_unique_check" data-value=false></div>
				<div>
					<input name="password" type="password"
						placeholder="å¯ç , ç±6-20ä½æ°å­åå­ç¬¦ç»æ">
				</div>
				<div>
					<input name="password_confirm" type="password"
						placeholder="ç¡®è®¤å¯ç ">
				</div>
				<div style="text-align: right">
					<button id="do_register" type="submit" class="btn btn-success">ç¡®è®¤æ³¨å</button>
				</div>
			</form>
		</div>
	</div>
	<script>
        function email_unique_check(email, status_obj) {
            if(!email)
                return ;
            unique_check({email: email}, status_obj, 'email')
        }

        function name_unique_check(name, status_obj) {
            if(!name)
                return ;
            unique_check({name: name}, status_obj, 'name')
        }
        //æ£æ¥é®ç®±å°åæå§åæ¯å¦è¢«å ç¨
        function unique_check(data, status_obj, now) {
            status_obj.data('value', 'waiting');
            $.ajax({
                url: '/register/check',
                data: data,
                success: function (req){
                    var msg = req['status'];
                    if(msg == '')
                        msg = 'è¿æ¥éè¯¯';
                    if(msg == 'success'){
                        status_obj.data('value', true);
                        if($('#msg').data('now') == now)
                            $('#msg').hide();
                    }else{
                        status_obj.data('value', false);
                        show_msg(msg, now);
                    }
                },
                error: function () {
                    status_obj.data('value', false);
                    show_msg('error');
                }
            });
        }

        function validateEmail(email) {
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        function login_check(form) {
            if(!validateEmail(form.find('input[name=email]').val())){
                show_msg('é®ç®±æ ¼å¼ä¸æ­£ç¡®');
                return false;
            }
            if(form.find('input[name=password]').val().length < 6 ||
                    form.find('input[name=password]').val().length > 20){
                show_msg('å¯ç é¿åº¦ä¸æ­£ç¡®');
                return false;
            }
            return true;
        }

        function show_msg(msg, now) {
            $('#msg').hide().html(msg).slideDown();
            if(now){
                $('#msg').data('now', now)
            }
        }

        function register_check(form) {
            var email = form.find('input[name=email]').val();
            var name = form.find('input[name=name]').val();
            var password = form.find('input[name=password]').val();
            if(!validateEmail(email)){
                show_msg('é®ç®±æ ¼å¼ä¸æ­£ç¡®');
                return false;
            }
            if(!(name.length >= 2)){
                show_msg('å§ååºä¸º2ä½ä»¥ä¸çå­ç¬¦');
                return false;
            }
            if(password.length < 6 || password.length > 20){
                show_msg('å¯ç åºè¯¥æ6-20ä½æ°å­æèå­ç¬¦ç»æ');
                return false;
            }
            if(form.find('input[name=password]').val() !=
                    form.find('input[name=password_confirm]').val()){
                show_msg('ä¸¤æ¬¡è¾å¥å¯ç ä¸ç¸å');
                return false;
            }
            if($('#email_unique_check').data('value') == false){
                show_msg('è¯·éæ°è¾å¥é®ç®±å°å');
                return false;
            }
            if($('#name_unique_check').data('value') == false){
                show_msg('è¯·éæ°è¾å¥å§å');
                return false;
            }
            if($('#email_unique_check').data('value') == 'waiting' ||
                    $('#name_unique_check').data('value') == 'waiting'){
                //æ­£å¨æ¥è¯¢
                $('#do_register').html('æäº¤ä¸­...');
                var times = 10;
                var check = function () {
                    console.log('check');
                    times -= 1;

                    if(times == 0){
                        show_msg('å¯¹ä¸èµ·, è¿æ¥éè¯¯ãè¯·å·æ°éæ¥ã');
                        return ;
                    }
                    if($('#email_unique_check').data('value') == 'waiting' ||
                            $('#name_unique_check').data('value') == 'waiting'){
                        setTimeout(check, 500);
                    }
                };
                setTimeout(check, 500);
                return false;
            }
            return true;
        }

        $(document).ready(function () {
            $('body').on('click', '.negative', function () {
                $('#msg').hide();
                var active = $('.active');
                active.removeClass('active');
                $(this).removeClass('negative');
                active.addClass('negative');
                $(this).addClass('active');

                if($(this).data('tab') == 'login'){
                    $('#register_form').hide();
                    $('#login_form').fadeIn('slow');
                }else{
                    $('#login_form').hide();
                    $('#register_form').fadeIn('slow');
                }
            });
        });
    </script>

	<style>
#msg {
	margin: 5px auto;
	background-color: #FCF8E3;
	padding: 5px;
	width: 270px;
	color: #8A6D3B;
	border-radius: 5px;
	display: none;
}

.btn {
	padding: 4px 8px;
}

form {
	width: 270px;
	margin: 0 auto;
}

input {
	width: 250px;
	padding: 5px;
	border-radius: 5px;
	margin-bottom: 10px;
	border: 1px solid #dddddd;
}

#mc {
	width: 500px;
	text-align: center;
	margin: 70px auto 0;
}

#mt {
	font-size: 4rem;
	font-weight: bold;
	color: #5082AA;
	border-bottom: 2px solid #f9f9f9;
}

#login_form,#register_form {
	margin-top: 15px;
}

#register_form {
	display: none;
}

#mn {
	margin-top: 15px;
	text-align: center;
}

#mn>span {
	display: inline-block;
	width: 70px;
	text-align: center;
	font-size: 1.3rem;
	font-weight: bold;
	letter-spacing: 5px;
	color: #999999;
}

#mn>span:hover {
	cursor: pointer;
}

#mn .active {
	color: #5082AA;
}

#mn .negative:hover {
	color: #5082AA;
}
</style>
</body>
</html>
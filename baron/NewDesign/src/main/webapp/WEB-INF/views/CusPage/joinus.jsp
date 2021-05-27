<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/demo.css?ver=1" />
<link rel="stylesheet" type="text/css" href="resources/css/loginform.css?ver=2" />
<title>Insert title here</title>

<style type="text/css">

html, body, form, fieldset, legend, ol, li {
	margin: 0;
	padding: 0;
	}

body {
	background: #ffffff;
	color: #111111;
	font-family: 맑은고딕,굴림,돋움;
	padding: 20px;	
}

form {
    background: #9cbc2c;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-khtml-border-radius: 5px;
	border-radius: 5px;
	counter-reset: fieldsets;
	padding: 20px;
	width: 700px;	
}

		
form fieldset {
	border: none;
	margin-bottom: 10px;
}
		
form fieldset:last-of-type {
	margin-bottom: 0;
}
			
form legend {
	color: #384313;
	font-size: 16px;
	font-weight: bold;
	padding-bottom: 10px;
	text-shadow: 0 1px 1px #c0d576;
}
				
form > fieldset > legend:before {
	counter-increment: fieldsets;
}
				
form fieldset fieldset legend {
	color: #111111;
	font-size: 13px;
	font-weight: normal;
	padding-bottom: 0;
}
			
form ol li {
	background: #b9cf6a;
	background: rgba(255,255,255,.3);
	border-color: #e3ebc3;
	border-color: rgba(255,255,255,.6);
	border-style: solid;
	border-width: 2px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-khtml-border-radius: 5px;
	border-radius: 5px;
	line-height: 30px;
	list-style: none;
	padding: 5px 10px;
	margin-bottom: 2px;
}
							
form ol ol li {
	background: none;
	border: none;
	float: left;
}
			
form label {
	float: left;
	font-size: 13px;
	width: 110px;
}
				
form fieldset fieldset label {
	background:none no-repeat left 50%;
	line-height: 20px;
	padding: 0 0 0 30px;
	width: auto;
}
					
					
form fieldset fieldset label:hover {
	cursor: pointer;
}
				
form input:not([type=radio]) {
	background: #ffffff;
	border: none;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	-khtml-border-radius: 3px;
	border-radius: 3px;
	font: italic 13px 맑은고딕,굴림,돋움;
	outline: none;
	padding: 5px;
	width: 200px;
}
					
form input:not([type=submit]):focus, {
	background: #eaeaea;
}
					
				
form button {
	background: #384313;
	border: none;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	-khtml-border-radius: 20px;
	border-radius: 20px;
	color: #ffffff;
	display: block;
	font: 16px 맑은고딕,굴림,돋움;
	letter-spacing: 1px;
	margin: auto;
	padding: 7px 25px;
	text-shadow: 0 1px 1px #000000;
	text-transform: uppercase;
}
					
form button:hover {
	background: #1e2506;
	cursor: pointer;
}

</style>


<script src="resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">

$(document).ready(function(){
	$('#id').on('input', checkId);
	$('#email').on('input', checkEmail);
	$('#password2').on('input', checkPw);
	
});

function checkPw(){
	var pw1 = $('#password').val();
	var pw2 = $('#password2').val();
	
		if(pw1 == pw2)
		{
			$('#checkpw').html('');
		}
		else
		{
			$('#checkpw').html('위와 동일해야합니다');
		}

}

function checkEmail(){
	var getMail = $('#email').val();
	
	if(getMail.search('@') < 0)
	{
		$('#checkEmail').html('올바른 메일형식이 아닙니다');
	}
	else
	{
		$.ajax({
				url: 'isThereEmail',
				type: 'get',
				data:{email: getMail},
				dataType: 'text',
				success: function(checkMail)
				{
					if(checkMail != '')
					{
						$('#checkEmail').html('이미 존재하는 메일주소입니다.');
					}
					else{
						$('#checkEmail').html(checkMail + '');
					}
				}
				
			});
		}
	}
	


function checkId(){
	var getId = $('#id').val();
	
	if(getId.length <= 5)
	{
		$('#checkid').html('6글자 이상 입력해주세요');
	}
	else
	{
		$.ajax({
			url: 'isThereId',
			type: 'post',
			data:{id: getId},
			dataType: 'text',
			success: function(checkid){
				if(checkid != '')
				{
					$('#checkid').html('이미 존재하는 id입니다.');
				}
				else{
					$('#checkid').html(getId + '는 사용가능합니다');
				}
			},
			error: function(checkid)
			{
				alert('에러');
			}
			
		});
	}
	
}

function infomation(){
	alert('회원가입이 완료되었습니다');
}
</script>


</head>
<body>
<div>
	<%@ include file="../TopMenu.jsp" %>
	
</div>
<div>
	<%@ include file="../backup.jsp" %>
</div>
<div class="container" >

			<article class="gocenter2" style="margin-bottom: 100px;">
				<form action="tryjoin" method="post" onsubmit="infomation();">
				<fieldset>
				<legend>가입 입력란</legend>
				<ol>
				  <li>
				    <label for="userid">아이디</label>
				    <input id="id" name="id" type="text"><span id="checkid" class="infomsg"> 아이디를 입력해주세요</span>
				  </li>
				  <li>
				    <label for="pwd1">비밀번호</label>
				    <input id="password" name="password" type="password"  required>
				  </li>
				  <li>
				    <label pwd="pwd2">비밀번호 확인</label>
				    <input id="password2" type="password" required><span id="checkpw" class="infomsg"></span>
				  </li>  
				   <li>
				    <label pwd="email">메일 주소</label>
				     <input id="email" name="email" type="email"  required><span id="checkEmail" class="infomsg"> </span>
				  </li>
				</ol>
				</fieldset>

					<br>
				  <input type="submit" id="confirm" value="가입하기" style="margin-left: 180px; cursor: pointer;">
				</form>
				
				
			</article>
			
		
			
			
</div>

</body>
</html>
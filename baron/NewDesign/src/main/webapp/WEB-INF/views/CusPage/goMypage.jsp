<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/www/resources/css/board.css?ver=1" />
<script src="/www/resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">

$(document).ready(function(){
	$('#updateButton').on('click', updateCustomerInfo);	
	$('#password2').on('input', confirmPw);
	
	var w2 = $('#leftad').width();
	var h2 = $('#leftad').height();
	$('#ad').attr('width', '100%');
	$('#ad').attr('height', '100%');
	
});

function confirmPw(){

	if($('#password').val() == $('#password2').val())
	{
		$('#checkpw').html('비밀번호가 일치합니다.');
	}
	else
	{
		$('#checkpw').html('위와 동일하지 않습니다.');
	}
}

function updateCustomerInfo(){
	
	//var origianlPw = $('#originalPw').val();
	var originalPw = document.getElementById('originalPw').value;
	var pw = $('#pw').val();
	var getId = $('#id').val();
	var getPassword = $('#password').val();
	var getPassword2 = $('#password2').val();
	var getEmail = $('#email').val();
	var getCusnum = ${myInfo.customerNumber};
	console.log('origianlPw = ' + originalPw);
	console.log('pw = ' +  pw);

	if(pw != originalPw)
	{
		alert('현재 비밀번호가 맞지 않습니다');
		$('#originalPw').focus();
		return;
	}
	
	$.ajax({
		url: 'updateCustomer',
		type: 'post',
		data: {id:getId, password:getPassword, email:getEmail, customerNumber:getCusnum},
		dataType: 'json',
		success: function(getCustomer){
			console.log(getCustomer);
			$('#id').val(getCustomer.id);
			$('#email').val(getCustomer.email);
			$('#password').val('');
			$('#originalPw').val('');
			$('#password2').val('');
			$('#checkpw').html('');
			$('#confirm').html('<p style="color: red;">회원 수정이 완료되었습니다.</p>');
			alert('회원 수정이 완료되었습니다.');
		},
		error: function(getError){
			console.log(getError);
		}
	});
}

function changePw(){
	alert('비밀번호를 변경합니다. 아래 항목을 입력해주세요');
	$('#password').removeAttr("readonly");
	$('#password2').removeAttr("readonly");
	$('#password').css('background', 'white');
	$('#password2').css('background', 'white');
	
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

<div>
	<%@ include file="../leftMenu.jsp" %>
</div>


<div class="center">
<h2 style="text-align: center;">회원정보수정</h2>
<form style="margin-left: 20%; margin-top: 5%;">
	<table>
		<tr>
			<th colspan="2" style="text-align: left">아이디 <span style="color: red;">(아이디 변경도 가능합니다)</span></th>
		</tr>
		<tr>
			<td style="text-align: left"><input id="id" name="id" value="${loginId }" required="required"></td>
		</tr>
		
		<!-- ===================== -->
		
		<tr>
			<th style="text-align: left" colspan="2">기존 비밀번호</th>
		</tr>
		<tr>
			<td style="text-align: left">
				<input id="originalPw" name="originalPw" type="password" required="required">
				<input id="pw" type="hidden" value="${myInfo.password }">
			</td>
		</tr>
		
		<!-- ===================== -->
	
		<!-- ===================== -->
		
		<tr>
			<th style="text-align: left" colspan="2">사용자 이메일</th>
		</tr>
		<tr>	
			<td style="text-align: left"><input type="text" name="email" id="email" value="${myInfo.email }" required="required"><br></td>
		</tr>

		<tr>
			<th style="text-align: left" colspan="2">새 비밀번호<input type="button" onclick="changePw();" value="비밀번호 변경" style="height: 25px; width: 100px;"></th>
		</tr>
		<tr>	
			<td style="text-align: left"><input type="password" name="password" id="password" required="required" readonly="readonly" style="background-color: gray;"></td>
		</tr>
		
		
		<!-- ===================== -->
		
		<tr>
			<th style="text-align: left" colspan="2">새 비밀번호 재입력</th>
		</tr>
		<tr>	
			<td style="text-align: left"><input type="password" name="password2" id="password2" required="required" readonly="readonly" style="background-color: gray;"></td>
			<td id="checkpw" style="color: red;"></td>
		</tr>
		
		
		
	</table>
	<div></div>


	<div id="confirm"></div>
	<div class="button" id="updateButton">수정</div>
	<a href="/www/"><div class="button" id="cancleButton">취소</div></a>
</form>

</div>
<div class="right">
	
	<img width="" height="" alt="광고" src="/www/resources/img/ad/scitad.jpg" id="ad">

</div>



</body>
</html>
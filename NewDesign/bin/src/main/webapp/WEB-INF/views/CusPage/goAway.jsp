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

	$('#confirm').on('click', function(){
		var pGet = document.getElementById('pGet');
		var nInput = document.getElementById('pInput');
		console.log(pGet.value);
		console.log(nInput.value);
		if(pGet.value != nInput.value)
		{
			alert('비밀번호가 틀립니다');
			return false;
		}
		else
		{
			if(confirm('정말로 탈퇴 하시겠습니까?'))
			{
				alert('그동안 이용해주셔서 감사합니다.');
				document.getElementById('deleteCustomer').submit();
				return true;
			}
			else
			{
				return false;
			}
		}
		
			
	

	});
	
});



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

<div>
	<form action="deleteCustomer" id="deleteCustomer" method="post">
		<p>회원탈퇴 절차를 진행합니다.</p>
		<br><br>
		<p style="margin-left:3em; text-align: center; display: inline-block;">
		비밀번호 입력 <input type="password" id="pInput"> 
		<div class="button" id="confirm">확인</div> </p>
		<input type="hidden" name="id" id="id" value="${loginId }">
		<input type="hidden" id="pGet" value=${pass }>
	</form>
</div>

</div>


<div class="right"></div>



</body>
</html>
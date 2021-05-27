<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/demo.css?ver=3" />
<link rel="stylesheet" type="text/css" href="resources/css/loginform.css?ver=4" />
<title>Insert title here</title>
<script src="resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">

$(document).ready(function(){
	$('#login').on('click', submit);
	$('input').keydown(function(key) 
	{
		if (key.keyCode == 13) 
		{
			$('#login').trigger('click');
		}
	});
	
	$('#id').focus();
	
	
	
});


function submit(){
	document.getElementById('loginform').submit();
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


<div class="container">
		
			<article class="gocenter">
				<div >
					<form action="loginButton" method="post" id="loginform">
						<table>
						<tr>
							<th colspan="2" style="font-size: 70px; font-style:italic; color: green;">Login</th>
										
						</tr>
						
						<tr>
							<th><p>아이디</p></th> 
							<td><input id="id" name="id"></td>						
						</tr>
						
						<tr>
							<th><p>비밀번호</p></th> 
							<td><input type="password" id="password" name="password"></td>
						</tr>							
						</table>
						
							<div id="login" class="changeDisply toButton" style="margin-left: 75px; ">로그인</div>
							<a href="joinus"><div id="joinus" class="changeDisply toButton">회원가입</div></a>
					</form>
				</div>
				
				<div>
					<c:if test="${loginResult == 1}"><p>일치하는 회원정보가 없습니다.</p></c:if>
				</div>
				
				<div>
					<img alt="그림" src="resources/img/customer/loginNaver.jpg" width="220em" height="50px">
				</div>
			</article>
			
			<br>
			
			<footer>
				
				<div>
					
					
				
				</div>	
			
			</footer>
			
</div>

</body>
</html>
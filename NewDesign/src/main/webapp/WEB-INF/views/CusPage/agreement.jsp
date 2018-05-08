<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/demo.css?ver=1" />
<link rel="stylesheet" type="text/css" href="resources/css/loginform.css?ver=2" />

<title>동의서</title>
<style type="text/css">

.yesno
{
	color: yellow;
	margin-left: 20px;
	display:inline-block;
	width: 60px;
	height: 30px;
	border: thin;
	border-collapse: collapse;
	border-color: black;
	background-color: green;
	text-align: center;
}

</style>

</head>


<body>
<div>
	<%@ include file="../TopMenu.jsp" %>
</div>
<div>
	<%@ include file="../backup.jsp" %>
</div>
	
		<div class="gocenter">
			<div>
				개인정보이용 동의서
			</div>
			<h4>개인정보 이용에 관하여</h4>
			<ol>
				<li>작성하신 정보는 데이터베이스에 저장되며 권한만 있으면 누구든지 열람할 수 있습니다.</li>
				<li>회원 탈퇴시 회원정보는 소거되며 복구 할 수 없습니다.</li>
				
			</ol>
			<div>
				동의하십니까? <a href="agree"><div class="yesno">예</div></a> <a href="/www"><div class="yesno">아니오</div></a> 
			</div>

		</div>
	





</body>
</html>
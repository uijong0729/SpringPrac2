<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/www/resources/css/demo.css?ver=2" />
<title>Insert title here</title>
</head>
<body>

<div class="container">
			<c:if test="${loginId == null}">
				<div class="codrops-top clearfix">
						<span class="topright"><a class="codrops-icon codrops-icon-drop" href="/www/signin"><span>로그인</span></a></span>
						<span class="topright"><a class="codrops-icon codrops-icon-drop" href="/www/joinus"><span>회원가입</span></a></span>
				</div>
			</c:if>
			<c:if test="${loginId != null}">
				<div class="codrops-top clearfix">
						<span class="topright"><a class="codrops-icon codrops-icon-drop" href="/www/goLogout"><span>로그아웃</span></a></span>
						<span class="topright"><a class="codrops-icon codrops-icon-drop" href="/www/goMypage"><span>마이페이지</span></a></span>
				</div>
			</c:if>
			<header class="codrops-header">
				<h1><a href="/www">인테리어 바론</a> <span></span></h1>	
			</header>
</div>

</body>
</html>
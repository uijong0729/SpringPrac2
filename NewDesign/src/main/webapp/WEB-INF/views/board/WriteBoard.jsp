<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>바론 인테리어</title>
<link rel="stylesheet" type="text/css" href="/www/resources/css/board.css?ver=2" />

<script src="/www/resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
$(document).ready(function(){
	$('#formSubmit').on('click', function(){
		
		document.getElementById('writeform').submit();
	});
	$('#formCancle').on('click', function(){
		history.go(-1);
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
<br><br><br>
<form id="writeform" method="post" action="writeform" enctype="multipart/form-data">
	<table class="writeTable">
		<tr>
			<th>제목</th>
			<td><input name="title" type="text" style="width: 300px; height: 35px;"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" rows="20" cols="46" style="resize: none;"></textarea></td>
		</tr>	
		<tr>
			<th>파일</th>
			<td>
			<input type="file" name="upload">
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${loginId }">
</form>
<br><br>

<div id="formSubmit" class="toInlineBlock writeTable button" style="margin-right: 20px;">완료</div>

<div id="formCancle" class="toInlineBlock button">취소</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>바론 인테리어</title>
<link rel="stylesheet" type="text/css" href="/www/resources/css/board.css?ver=1" />

<script src="/www/resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">




$(document).ready(function(){
	$('#rewriteConfirm').on('click', function(){
		$('#title').attr('value', $('#title').val());
		$('#content').attr('value', $('#content').val());
		$('#originalfile').attr('value', $('#originalfile').val());
		$('#savedfile').attr('value', $('#savedfile').val());
		
		document.getElementById('rewriteBoardForm').submit();
	});
	$('#cancleConfirm').on('click', function(){
		history.go(-1);
	});
	
	$('#fileDeleteConfirm').on('click', function(){
		$('#originalfile').val('');
		$('#savedfile').val('');
		
	});
	
});



</script>

<style type="text/css">

</style>

</head>
<body>
<div>
	<%@ include file="../TopMenu.jsp" %>
	
</div>
<div>
	<%@ include file="../backup.jsp" %>
</div>

<div class="left"></div>


<div class="center">
<p></p><br><br><br>
<form id="rewriteBoardForm" method="post" action="rewriteBoardForm" enctype="multipart/form-data">
	<table>
	
		<tr>
			<td>제목 <input id="title" name="title" value="${bod.title }" style="width: 400px;"></td>
			<td></td>
		</tr>

		<tr>
			<td colspan="3">
			<textarea id="content" name="content" rows="24" cols="85" style="resize: none;">${bod.content }</textarea>
			</td>
		</tr>
	
		<tr>
			<td><input type="file" style="cursor: pointer;" name="upload">
			<input id="originalfile" name="originalfile" value="${bod.originalfile }" readonly="readonly" width="100px" style="border: none; cursor: pointer;">
			<input type="hidden" name="savedfile" id="savedfile" value="${bod.savedfile }">
			</td>
			<td>
			<div id="fileDeleteConfirm" class="button boardButton">삭제</div>
			</td>
		</tr>
		
		<tr></tr>
	</table>
	<input type="hidden" name="boardNum" value="${bod.boardNum }">
	<input type="hidden" name="id" value="${loginId }">
</form>

<c:if test="${loginId == bod.id }">
	<div id="rewriteConfirm" class="button boardButton">
	저장
	</div>
	
	<div id="cancleConfirm" class="button boardButton">
	취소
	</div>
</c:if>
</div>
<div class="right"></div>


</body>
</html>
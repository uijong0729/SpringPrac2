<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/www/resources/css/board.css?ver=1" />
<title>바론 인테리어</title>
<script src="/www/resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">

$(document).ready(function(){
	$('#write').on('click', writeform);
	$('#search').on('click', searchBoard);
	
	var w2 = $('#leftad').width();
	var h2 = $('#leftad').height();
	$('#ad').attr('width', '100%');
	$('#ad').attr('height', '100%');
});

function searchBoard(){
	document.getElementById('searchBoard').submit();
}

function writeform(){
	location.href="writeBoard";
}

function boardPage(page){
	location.href="goBoard?page=" + page;
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
<div class="left" id="leftad">

	<img width="" height="" alt="광고" src="/www/resources/img/ad/scitad.jpg" id="ad">

</div>

<article class="centerNoRight">

	<table class="boardbone">
		<tr class="boardbar">
			<th style="width: 10em">글 번호</th>
			<th style="width: 80em">글 제목</th>
			<th style="width: 20em">작성자</th>
			<th style="width: 10em">작성일</th>
			<th style="width: 10em">조회수</th>
		</tr>
		<c:forEach items="${boardAll }" var="bl">
		<tr>
			<td style="width: 10em; text-align: center;">${bl.boardNum }</td>
			<td style="width: 80em; text-align: center;"><a href="bd?boardNum=${bl.boardNum }">${bl.title }</a></td>
			<td style="width: 20em; text-align: center;">${bl.id }</td>
			<td style="width: 10em; text-align: center;">${bl.writeDate }</td>
			<td style="width: 10em; text-align: center;">${bl.hits }</td>
		</tr>
		</c:forEach>

	
	</table>


<hr>
<div style="text-align: center;">

<c:if test="${currentPage > 1 }">
<a href="goBoard?page=${currentPage - 1 }"><div class="toInlineBlock">이전</div></a>
</c:if>  
<div class="toInlineBlock">

	<c:forEach varStatus="s" begin="1" end="${countBoardList }" step="10">
		<!-- 해당 페이지의 링크 -->
		<c:if test="${s.count != currentPage }">
			<a href="#" onclick="javascript:boardPage(${s.count });">[${s.count }]</a>
		</c:if>
		
		<!-- 해당페이지이면 링크 없애기 -->
		<c:if test="${s.count == currentPage }">
			<b>[${s.count }]</b>
		</c:if>	
	</c:forEach>

</div>	
<c:if test="${currentPage < lastPage }">
<a href="goBoard?page=${currentPage + 1 }"><div class="toInlineBlock">다음</div></a>
</c:if>
	<br>
	<hr>
	
<form id="searchBoard" action="searchBoard" method="get" style="display: inline-block;">
	<select name="option" id="option">
		<c:if test="${option eq 'title' }">
			<option value="title" selected="selected">제목</option>
			<option value="id">작성자</option>
			<option value="content">내용</option>	
		</c:if>
		<c:if test="${option eq 'id' }">
			<option value="title">제목</option>
			<option value="id" selected="selected">작성자</option>
			<option value="content">내용</option>	
		</c:if>
		<c:if test="${option eq 'content' }">
			<option value="title">제목</option>
			<option value="id">작성자</option>
			<option value="content" selected="selected">내용</option>	
		</c:if>
		<c:if test="${option == null }">
			<option value="title">제목</option>
			<option value="id">작성자</option>
			<option value="content">내용</option>	
		</c:if>
	</select>
	<input type="text" name="keyword" value="${keyword }">
	<div class="toInlineBlock button" id="search">검색</div>
</form>
	
	
	
	<div class="toInlineBlock button" id="write">글쓰기</div>
</div>

</article>

</body>
</html>
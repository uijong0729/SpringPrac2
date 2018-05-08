<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/www/resources/css/board.css?ver=5" />
<title>인테리어 바론</title>
<script src="/www/resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
var page = 1;
var sw = 1;

$(document).ready(function(){
	$('#writeGallery').on('click', function(){
		var child = window.open("uploadform","newwin","width=600px, height=450px");
	});
	
	//사진
	var w = $('.frame').width();
	var h = $('.frame').height();
	$('.imgsize').attr('width', w);
	$('.imgsize').attr('height', h );
	
	//광고
	var w2 = $('#leftad').width();
	var h2 = $('#leftad').height();
	$('#ad').attr('width', '100%');
	$('#ad').attr('height', '100%');
	
	$('.imgsize').on('click', function(){
		
		if(sw == 1)
		{
			sw = sw - 1;
			//액자
			var w = $(this).width();
			var h = $(this).height();
			$(this).css('width', w * 2.4);
			$(this).css('height', h * 2.4);
			$(this).css({"position": "fixed", "z-index": "39", "top": "7%", "left":"10%"});
			
		}
		else
		{
			sw = sw + 1;
			//액자
			var w = $(this).width();
			var h = $(this).height();
			$(this).css('width', w / 2.4);
			$(this).css('height', h / 2.4);
			$(this).css({"position": "static", "z-index": "1"});

		}
	});

});

function deleteGal(){
	var galleryNum = $('#galleryNum').attr('value');
	
	$.ajax({
		url: 'deleteGallery',
		type: 'post',
		data: {galleryNum: galleryNum},
		success: function(){
			console.log('삭제');	
			location.reload();
		}
		
	});
}

function galleryPage(page)
{
	
	location.href="goGallery?page=" + page;
	
}
</script>

<style type="text/css">

.frame{

margin-bottom: 2em;

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

<div class="left" id="leftad">

	<img width="" height="" alt="광고" src="/www/resources/img/ad/scitad.jpg" id="ad">

</div>
<div class="centerNoRight">
	<c:if test="${loginId == 'manager' }"><div class="toInlineBlock button" id="writeGallery">업로드</div></c:if>
	<hr>
	
	<c:forEach items="${galleryAll }" var="gl" varStatus="index">
			<input type="hidden" value="${gl.galleryNum }" id="galleryNum" name="galleryNum">
			<div class="init toInlineBlock frame" number="${index.count }" style="width: 49%; height: 40%; border: 1px dotted gray;">
				<table>
					<tr><td>${gl.title }<c:if test="${loginId == 'manager' }"><input type="button" value="삭제" onclick="deleteGal()"></c:if></td></tr>
					<tr><td><img number="${index.count }" width="" height="" class="imgsize" align="middle" src="download?galleryNum=${gl.galleryNum }"/>
					
					</td></tr>
					
				</table>
			</div>
	</c:forEach>

<hr>

	<div class="toInlineBlock" style="margin-left: 46%;">
	<br>
		<c:forEach varStatus="s" begin="1" end="${countGallery }" step="4">
			
			<!-- 해당 페이지의 링크 -->
			<c:if test="${s.count != galleryPage }">
				<a href="#" onclick="galleryPage(${s.count })">[${s.count }]</a>
			</c:if>
			
			<!-- 해당페이지이면 링크 없애기 -->
			<c:if test="${s.count == galleryPage }">
				<b>[${s.count }]</b>
			</c:if>	
		</c:forEach>
	
	</div>	

</div>
	
	
	



</body>
</html>
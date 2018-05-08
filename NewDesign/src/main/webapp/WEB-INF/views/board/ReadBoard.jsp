/<%@ page language="java" contentType="text/html; charset=UTF-8"
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

	//스트링버퍼 설정
	var StringBuffer = function() {
	    this.buffer = new Array();
	};
	
	StringBuffer.prototype.append = function(str) {
	    this.buffer[this.buffer.length] = str;
	};
	
	StringBuffer.prototype.toString = function() {
	    return this.buffer.join("");
	};


$(document).ready(function(){
	$('#insertReply').on('click', insertReply);
	init();
	
	$('#rewrite').on('click', rewrite);
	$('#remove').on('click', remove);
	
	//광고창 div크기조절
	var h1 = $('#replydiv').height();
	var h2 = $('#boarddiv').height();
	var height = h1 + h2 + 100;
	$('.left').css('height', height);
	
	
	//광고창 크기조절
	var w2 = $('.left').width();
	var h2 = $('.left').height();
	$('#ad').attr('width', '100%');
	$('#ad').attr('height', '100%');
});

function rewrite(){
	document.getElementById('submitBoardForm').submit();
}

function remove(){
	if(${loginId != board.id })
	{
		alert('잘못된 접근입니다');
		return;
	}
	else
	{
		if(confirm('정말로 삭제하시겠습니까?') == true)
		{
			location.href= "deleteForm?boardNum=" + ${board.boardNum};
		}
	}
}


function insertReply(){
	var id = $('#id').attr('val');
	var content = $('#content').val();
	var boardNum = $('#boardNum').val();

	$.ajax({
		url: 'insertReply',
		type: 'post',
		data: {id:id, content:content, boardNum:boardNum},		
		success: function(reply){
			console.log(reply);
			console.log('성공');
			$('#content').val('');
			$('#text').val('');
			init();
		
		},
		error: function(msg){
			console.log(msg);
			console.log('에러');			
		}
		
	});
	
}
var page = 1;
function init(){
	var str = new StringBuffer();
	var reBuf = new StringBuffer(); 
	var boardNum = $('#boardNum').val();
	$.ajax({
		url: 'replyList',
		type: 'get',
		data:{boardNum: boardNum, page:page},
		dataType: 'json',
		success: function(reply){
			//덧글 출력
				$.each(reply.replyAll, function(index, item){
					var id = '${loginId}';
					//str.append('<tr><th class="reply replyId">'+ item.id +'</th><td class="reply replyContent">'+ item.content +'</td><td class="reply replyDate">'+ item.writeDate +'</td></tr>');
					str.append('<div style="display: inline-block; border: 1px solid black;"><span style="text-align: left">'+ item.id +' '+'</span>');
					if(item.id == id)
					{
						str.append('<input type="button" style="width:35px; height:18px; font-size: 9px; margin-left: 5px;" value="삭제" onclick="deleteReply('+item.replyNum+')">');
					}
					str.append('<hr><p style="text-align: left">'+item.content+'</p>');
					str.append('<p style="text-align: left; color: gray;">'+item.writeDate+'</p>');

					str.append('</div>');
				
				});

			$('#replyTable').html(str.toString());
			
			
			//페이지 출력
			reBuf.append('<ul>');
			for (var i = 1; i <= Math.ceil((reply.replyCount)/4.0) ; i++)
			{
				if(page == i)
				{
					reBuf.append('<li class="page button" page='+ i +'><b>['+ i +']</b></li>');
				}
				else
				{
					reBuf.append('<li class="page button" page='+ i +'>['+ i +']</li>');
				}
			}
			reBuf.append('</ul>');
			$('#replyPage').html(reBuf.toString());
			
			$('.page').on('click', commentPage);
		},
		error: function(msg){
			console.log(msg);
		}
		
	});
}

function commentPage(){
	var str = new StringBuffer();
	var reBuf = new StringBuffer(); 
	var page = $(this).attr('page');
	var boardNum = $('#boardNum').val();
	$.ajax({
		url: 'replyList',
		type: 'get',
		data:{boardNum: boardNum, page:page},
		dataType: 'json',
		success: function(reply)
		{
				//덧글 출력
				$.each(reply.replyAll, function(index, item){
					var id = '${loginId}';
					//str.append('<tr><th class="reply replyId">'+ item.id +'</th><td class="reply replyContent">'+ item.content +'</td><td class="reply replyDate">'+ item.writeDate +'</td></tr>');
					str.append('<div style="display: inline-block; border: 1px solid black;"><span style="text-align: left">'+ item.id +' '+'</span>');
					if(item.id == id)
					{
						str.append('<input type="button" style="width:35px; height:18px; font-size: 9px; margin-left: 5px;" value="삭제" onclick="deleteReply('+item.replyNum+')">');
					}
					str.append('<hr><p style="text-align: left">'+item.content+'</p>');
					str.append('<p style="text-align: left; color: gray;">'+item.writeDate+'</p>');

					str.append('</div>');
				
				});
				
				$('#replyTable').html(str.toString());
				
				var reBuf =  new StringBuffer(); 
				
				//페이지 출력
				reBuf.append('<ul>');
				for (var i = 1; i <= Math.ceil((reply.replyCount)/4.0) ; i++)
				{
					if(page == i)
					{
						reBuf.append('<li class="page button" page='+ i +'><b>['+ i +']</b></li>');
					}
					else
					{
						reBuf.append('<li class="page button" page='+ i +'>['+ i +']</li>');
					}
					
				}
				reBuf.append('</ul>');
				$('#replyPage').html(reBuf.toString());
				
				$('.page').on('click', commentPage);
		},//성공시 
		
		error: function(msg){
			console.log(msg);
		}//실패시
	

	});//ajax끝
	
}//함수 끝

function deleteReply(replyNum)
{
	$.ajax({
		url:'deleteReply',
		data: {replyNum:replyNum},
		type: 'post',
		success: function(){
			init();
		}
		
	});
}

</script>

<style type="text/css">

ul{
list-style: none;

}
li{
margin-left: 4px;
display: inline-block;
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

	<div class="left">
		<img width="" height="" alt="광고" src="/www/resources/img/ad/scitad.jpg" id="ad">
	</div>


<div class="centerNoRight" id="boarddiv">
<p></p><br><br><br>
<form id="submitBoardForm" method="post" action="submitBoardForm">
	<table>
	
		<tr>
			<td>제목</td><td><input name="title" value="${board.title }" readonly="readonly" style="border: none;"></td>
			<td>작성자</td><td><span>${board.id }</span></td>
		</tr>
		<tr>
			<td>작성일자</td><td><span>${board.writeDate }</span></td>
			<td>조회수</td><td>${board.hits }</td>
		</tr>
		
		
		<tr>
			<td>첨부된 파일</td>
			<td>
				<c:if test="${board.originalfile != ''}">
					<a href="download?boardNum=${board.boardNum }"><input readonly="readonly" name="originalfile" value="${board.originalfile }" style="width:200px; border: none; cursor: pointer;"></a>
				</c:if>
			</td>

		</tr>
		<tr>
			<td colspan="3">
				<textarea name="content" rows="24" cols="85" readonly="readonly" style="resize: none;">${board.content }</textarea>
			</td>
		</tr>
	</table>
	<input type="hidden" name="boardNum" value=${board.boardNum }>
	
</form>

<!-- 메뉴버튼 -->
<c:if test="${loginId == board.id }">
	<div id="rewrite" class="button boardButton">
	수정
	</div>
	
	<div id="remove" class="button boardButton">
	삭제	
	</div>
</c:if>
	<a href="goBoard"><div class="toInlineBlock button">목록으로</div></a>
</div>


<div class="init" style="width: 95%;" id="replydiv">

	<table id="replyTable" class="init" style="margin-left:24%; width: 78%" class="replyTable" >
		<%-- 보여주기 폼 : 작성일, 작성자, 작성시간, 덧글에 덧글, 수정, 삭제, 덧글내용--%>
	
		
	</table>
	
	
	<div id="replyPage" style="margin-left: 52%;">
	
	</div>
	
		<%-- 등록 폼 --%>
		<form id="submitReply" style="margin-left: 46%;">
			<table>
				<tr>
					<th><div id="id" val='${loginId }'>${loginId }</div><hr></th>
				</tr>
				<tr>
					<th><textarea id="content" rows="2" cols="30" style="resize: none;"></textarea></th>
					<th><p id="insertReply" style="font-size: 20px;" class="button">등록</p></th>
				</tr>
			</table>
			<input type="hidden" id="boardNum" name="boardNum" value="${board.boardNum }">
			
		</form>


</div>


</body>
</html>
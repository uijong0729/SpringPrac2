<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/www/resources/css/board.css?ver=1" />
<title>Insert title here</title>
<script src="/www/resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript" src="/www/resources/js/jquery.form.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$('#submit').on('click', goUpload);
});



function goUpload(){
	
	$("#uploadSubmit").ajaxForm({
         url : "uploadSubmit",
         enctype : "multipart/form-data",
         type:"post",
         dataType : "json",
         error : function(result){
             alert("에러") ;
         },
         success : function(result){
            // alert("성공") ;
         	//날 열어준 주체(customerJoinForm) : opener = 자식창에서 부모창을 지정 할 수 있는 변수
         	//opener.location.href="goGallery";
         	opener.location.reload();
   
         	$('#title').val('');
         	$('#upload').val('');
         }
     });

     $("#uploadSubmit").submit() ;



}


</script>
</head>
<body>



	<h1>사진 업로드</h1>
	<form action="uploadSubmit" id="uploadSubmit" enctype="multipart/form-data" method="post">
	<table style="text-align: left;">
		<tr>
			<td>제목</td>
			<td><input id="title" name="title" style="width: 20em;" required="required"></td>
		</tr>
	
	</table>
	
	
		<table>
			<tr>
				<td colspan="2">
					<input type="file" id="upload" name="upload" required="required">
				</td>
			</tr>	
		</table>
	</form>
	
	<br>
	<input id="submit" type="button" value="게시">


</body>
</html>
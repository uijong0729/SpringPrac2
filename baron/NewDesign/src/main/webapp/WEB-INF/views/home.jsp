<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인화면</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="description" content="Hover Effect Ideas: Inspiration for subtle hover effects" />
		<meta name="keywords" content="hover effect, inspiration, grid, thumbnail, transition, subtle, web design" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,800,300' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="resources/css/normalize.css?ver=2" />
		<link rel="stylesheet" type="text/css" href="resources/css/demo.css?ver=2" />
		<link rel="stylesheet" type="text/css" href="resources/css/component.css?ver=2" />


<script src="resources/js/jquery-3.2.1.min.js">
</script>

<script type="text/javascript">

$(document).ready(function(){
	$('.effect-lily').on('click', function(){
		
		if(${loginId == null})
		{
			location.href="signin";
		}
		else
		{
			location.href="/www/goLogout";
		}
	});
	
	$('.effect-sadie').on('click', goBoard);
	$('.effect-layla').on('click', function(){
		location.href="goGallery";
	});
	
});

function goBoard(){
	location.href="member/goBoard";
}


</script>

</head>
<body>
<div class="container">
<div>
	<%@ include file="TopMenu.jsp" %>
</div>
			<div class="grid">
				<figure class="effect-lily">
					<img src="resources/img/1.jpg" alt="img01"/>
					<figcaption>
						<c:if test="${loginId == null}"><h2> <span>로그인</span></h2>
						<p>로그인 하시려면 눌러주세요</p></c:if>
						<c:if test="${loginId != null}"><h2> <span>로그아웃</span></h2>
						<p>로그아웃 하시려면 눌러주세요</p></c:if>
					</figcaption>			
				</figure>
				<figure class="effect-sadie">
					<img src="resources/img/2.jpg" alt="img02"/>
					<figcaption>
						<h2>자유 <span>게시판</span></h2>
						<p>회원분들을 위한 게시판입니다.</p>	
					</figcaption>			
				</figure>
				<figure class="effect-honey">
					<img src="resources/img/3.jpg" alt="img07"/>
					<figcaption>
						<h2>견적의뢰<span> & 문의</span> <i>Now   </i></h2>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-layla">
					<img src="resources/img/4.jpg" alt="img04"/>
					<figcaption>
						<h2>시공 <span>갤러리</span></h2>
						<p>그동안의 작업들이 담긴 갤러리입니다.</p>
					</figcaption>			
				</figure>
				<figure class="effect-zoe">
					<img src="resources/img/5.jpg" alt="img14"/>
					<figcaption>
						<h2>인테리어<span>바론</span></h2>
						<span class="icon-heart"></span>
						<span class="icon-eye"></span>
						<span class="icon-paper-clip"></span>
						<p>방문을 환영합니다!</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-oscar">
					<img src="resources/img/6.jpg" alt="img08"/>
					<figcaption>
						<h2>사업자 <span>소개</span></h2>
						<p>인테리어 바론을 소개합니다.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-marley">
					<img src="resources/img/7.jpg" alt="img09"/>
					<figcaption>
						<h2>공지<span>사항</span></h2>
						<p>인테리어 바론에서 전달드리고자하는 사항들입니다.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-ruby">
					<img src="resources/img/8.jpg" alt="img10"/>
					<figcaption>
						<h2>목수 <span>구인구직</span></h2>
						<p>인테리어 바론에서 구인하거나 스스로 구직을 희망하시는 목수분들을 위한 공간입니다.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				<figure class="effect-roxy">
					<img src="resources/img/9.jpg" alt="img03"/>
					<figcaption>
						<h2>인테리어 상식 <span>& 정보</span></h2>
						<p>주택상식, 시공상식, 초보 목수분들을 위해 마련한 교육공간입니다.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
			</div>
			
		</div><!-- /container -->


		<script>
			// For Demo purposes only
			[].slice.call( document.querySelectorAll('a[href="#"') ).forEach( function(el) {
				el.addEventListener( 'click', function(ev) { ev.preventDefault(); } );
			} );
		</script>


</body>
</html>
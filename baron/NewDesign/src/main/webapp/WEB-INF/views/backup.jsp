<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/www/resources/css/test.css?ver=1" />

<script src="/www/resources/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
var App = {

		accessibleMenu: function(){
			$menu = $('.access-menu');
			$menuItem = $menu.find('> li > a');

			$subMenu = $('.access-submenu');
			$subMenuItem = $subMenu.find('> li > a');
			$submenuLastItem = $subMenu.find('> li:last-child > a');

			$menuItem.bind({
				focus: function(){
					$subMenu.removeClass('is-show');
					if($(this).next($subMenu)){
						$(this).next($subMenu).addClass('is-show');
					}
				},

				blur: function(){
					$subMenu.removeClass('is-show');
				}
			});

			$subMenuItem.bind({
				focus: function(){
					$(this).parent().parent().addClass('is-show');
				}
			});

			$submenuLastItem.bind({
				blur: function(){
					$subMenu.removeClass('is-show');
				}
			});
		},

		cancelAccessibleMenu: function(){
			$(document).click(function(){
				if($subMenu.hasClass('is-show')){
					$subMenu.removeClass('is-show');
				}
			});
		}

	};

	$(function(){
		App.accessibleMenu();
		App.cancelAccessibleMenu();
	});
</script>



</head>
<body>
		
	<nav role="navigation">
		<ul class="access-menu">
		<c:if test="${loginId == null}">
			<li>
				<a href="/www/signin">로그인</a>
			</li>
		</c:if>	
		<c:if test="${loginId != null}">
			<li>
				<a href="/www/goMypage">마이페이지</a>
			</li>
		</c:if>	
			
			<li>
				<a href="/www/member/goBoard">자유게시판</a>
			</li>
			<li>
				<a href="#">견적의뢰 & 문의</a>
			</li>
			<li>
				<a href="/www/goGallery">시공갤러리</a>
			</li>
			<li>
				<a href="#">사업자소개</a>
			</li>
      			<li>
				<a href="#">공지사항</a>
			</li>
      			<li>
				<a href="#">구인/구직</a>
			</li>
      			<li>
				<a href="#">인테리어 상식/정보</a>
			</li>
		</ul>
	</nav>
</body>
</html>
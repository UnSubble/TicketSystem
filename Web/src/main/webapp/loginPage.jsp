<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="tr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Giriş Yap / Kayıt Ol</title>
<link type="text/css" rel="stylesheet"
	href="./resource/loginPageStylesheet.css">
</head>
<body>
	<div class="container">
		<h1>Hoş Geldiniz</h1>
		<br> <label id="switch-label"> <input type="checkbox"
			id="login-register-switch" onchange="switchState()" /> <span
			id="login-span" class="active">Giriş Yap</span> <span
			id="register-span">Kayıt Ol</span>
		</label>

		<form method="post" id="auth-form" action="/Web/auth">
			<label for="username">Kullanıcı Adı</label> <input type="text"
				name="username" id="username" placeholder="Kullanıcı adınızı girin"
				required /> <label for="password">Şifre</label> <input
				type="password" name="password" id="password"
				placeholder="Şifrenizi girin" required />
			<c:if test="${error eq 1}">
				<span style="size: 150%; color: crimson;">Girdiğiniz
					kullanıcı adı veya şifre yanlış!</span>
			</c:if>
			<c:if test="${error eq 2}">
				<span style="size: 150%; color: crimson;">Girdiğiniz
					kullanıcı adı zaten alınmış!</span>
			</c:if>
			<button type="submit">Giriş Yap</button>
		</form>
	</div>

	<script src="./resource/loginpageswitcher.js"></script>
</body>
</html>

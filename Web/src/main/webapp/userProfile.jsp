<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="./resource/userProfileStylesheet.css">
</head>
<body>
	<div class="container">
		<div class="container">
			<div class="header">
				<h1>
					Hoş geldiniz, <span id="user-name">Kullanıcı Adı</span>
				</h1>
			</div>

			<div class="user-info">
				<h3>Kullanıcı Bilgileri</h3>
				<p>
					<strong>Ad:</strong> <span id="user-name">Kullanıcı Adı</span>
				</p>
			</div>

			<div class="ticket-list">
				<h3>Açtığınız Ticketlar</h3>
				<div id="ticket-container">
					<span><strong>Ticket 1:</strong> Konu: test
						<button class="inner-btn">Sil</button>
						<button class="inner-btn">Devam Et</button> </span>
				</div>
			</div>

			<button class="add-ticket-btn">Yeni Ticket Ekle</button>
		</div>
		<form id="logout-form" action="/Web/loginPage.jsp" method="POST">
			<button class="logout-btn"">Çıkış Yap</button>
		</form>
	</div>
</body>
</html>
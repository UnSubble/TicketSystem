<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="./resource/userProfileStylesheet.css">
<link type="text/css" rel="stylesheet"
	href="./resource/closedButtonStylesheet.css">
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

			<form method="GET" action="/Web/ticket">
				<div class="ticket-list">
					<h3>Açtığınız Ticketlar</h3>
					<c:forEach var="ticket" items="${ticketsBelongsToUser}"
						varStatus="ticketStat">
						<div id="ticket-container">
							<span><strong>Ticket ${ticketStat.index + 1}:</strong>
								Konu: ${ticket.title}
								<button class="inner-btn" name="deleteTicket"
									value="${ticket.id}">Sil</button>
								<button
									class="inner-btn ${ticket.closed ? 'closed-ticket-btn' : ''}"
									disabled="${ticket.closed ? 'disabled' : 'enabled'}"
									name="continueTicket" value="${ticket.id}">Devam Et</button> </span>
						</div>
					</c:forEach>
				</div>
			</form>

			<button class="add-ticket-btn">Yeni Ticket Ekle</button>
		</div>
		<form id="logout-form" action="/Web/loginPage.jsp" method="POST">
			<button class="logout-btn">Çıkış Yap</button>
		</form>
	</div>
</body>
</html>
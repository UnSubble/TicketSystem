<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kullanıcı Sayfası</title>
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
					Hoş geldiniz, <span id="user-name">${username}</span>
				</h1>
			</div>

			<div class="user-info">
				<h3>Kullanıcı Bilgileri</h3>
				<p>
					<strong>Ad:</strong> <span id="user-name">${username}</span><br>
					<strong>Son Giriş:</strong> <span id="last-login">${user.lastLogin}</span><br>
					<strong>Hesap Oluşturulma Tarihi:</strong> <span id="creation-date">${user.createdDay}</span>
				</p>
			</div>

			<form method="GET" action="/Web/ticket">
				<div class="ticket-list">
					<h3>Açtığınız Ticketlar</h3>
					<c:forEach var="ticket" items="${ticketsBelongsToUser}"
						varStatus="ticketStat">
						<div id="ticket-container"
							class="ticket ${ticket.solved ? 'solved-ticket' : ''}">
							<div class="ticket-details">
								<span class="ticket-title"> <strong>Ticket
										${ticketStat.index + 1}:</strong> ${ticket.title}
								</span>
								<c:if test="${ticket.solved}">
									<span class="solved-badge">✔ Çözüldü</span>
								</c:if>
							</div>
							<div class="ticket-actions">
								<button class="inner-btn" name="deleteTicket"
									value="${ticket.id}">Sil</button>
								<button
									class="inner-btn ${ticket.closed or ticket.solved ? 'closed-ticket-btn' : ''}"
									${ticket.closed or ticket.solved ? 'disabled' : ''}
									name="continueTicket" value="${ticket.id}">Devam Et</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</form>

			<form action="/Web/section" method="POST">
				<button class="add-ticket-btn" name="newTicket" value="true">Yeni
					Ticket Ekle</button>
			</form>
		</div>
		<form id="logout-form" action="/Web/logout" method="GET">
			<button class="logout-btn">Çıkış Yap</button>
		</form>
	</div>
</body>
</html>
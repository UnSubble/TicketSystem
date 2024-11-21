<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ticket Details</title>
<link rel="stylesheet" type="text/css"
	href="./resource/sectionPageStylesheet.css">
<link type="text/css" rel="stylesheet"
	href="./resource/closedButtonStylesheet.css">
</head>
<body>
	<div class="container">
		<div id="ticket-container" class="ticket-box">
			<h2>Ticket Detayları</h2>
			<p>
				<strong>Konu:</strong> ${ticket.title}
			</p>
			<p>
				<strong>İçerik:</strong> ${ticket.content}
			</p>
		</div>
		<div class="items-section">
			<h3>Sohbet</h3>
			<c:forEach var="item" items="${ticket.items}">
				<div
					class="item-container ${item.user.name eq username ? 'user-comment' : 'a-user-comment' }">
					<div class="item-avatar">U</div>
					<div class="item-content">
						<strong>${item.user.name}</strong>
						<p>${item.content}</p>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="comment-section">
			<h3>Yorum Yap</h3>
			<form action="/Web/addItemToTicket" method="post">
				<textarea name="commentContent"
					disabled="${ticket.closed ? 'disabled' : 'enabled'}"
					placeholder="Yorumunuzu buraya yazın..." required></textarea>
				<button type="submit"
					class="${ticket.closed ? 'closed-ticket-btn' : ''}"
					disabled="${ticket.closed ? 'disabled' : 'enabled'}">Gönder</button>
			</form>
		</div>
	</div>
</body>
</html>

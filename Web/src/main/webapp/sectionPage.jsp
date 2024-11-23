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
		<div class="back-button">
			<a href="/Web/auth" class="back-link">← Geri Dön</a>
		</div>
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
		<form action="/Web/addItemToTicket" method="post">
			<c:if test="${ticket.title.isEmpty()}"> <%-- TODO --%>
				<h3>Başlık:</h3>
				<input type="hidden" name="ticketId" value="${ticket.id}" />
				<input type="text" name="title" value="${ticket.title}"
					class="title-input" required />
				<div class="priority-section">
					<h3>Öncelik Seçimi</h3>
					<input type="hidden" name="ticketId" value="${ticket.id}" /> <select
						name="priority" class="priority-select"
						${ticket.closed ? 'disabled' : ''}>
						<option value="LOW" selected>LOW</option>
						<option value="NORMAL">NORMAL</option>
						<option value="HIGH">HIGH</option>
						<option value="URGENT">URGENT</option>
						<option value="CRITICAL">CRITICAL</option>
					</select>
				</div>
			</c:if>
			<div class="comment-section">
				<h3>Yorum Yap</h3>
				<textarea name="commentContent" ${ticket.closed ? 'disabled' : ''}
					placeholder="Yorumunuzu buraya yazın..." required></textarea>
				<button type="submit"
					class="${ticket.closed ? 'closed-ticket-btn' : ''}"
					${ticket.closed ? 'disabled' : ''}>Gönder</button>
			</div>
		</form>
	</div>
</body>
</html>

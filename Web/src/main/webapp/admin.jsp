<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./resource/adminPageStylesheet.css">
<link type="text/css" rel="stylesheet"
	href="./resource/closedButtonStylesheet.css">
<title>Admin Panel</title>
</head>
<body>
	<div class="admin-container">
		<header class="admin-header">
			<h1>Admin Panel</h1>
			<form action="/Web/logout" method="GET">
				<button class="logout-btn">Çıkış Yap</button>
			</form>
		</header>
		<main class="admin-content">
			<h2>Gelen Ticketlar</h2>
			<form action="/Web/ticket" method="GET">
				<div class="tickets">
					<c:forEach var="ticket" items="${listOfTickets}">
						<div class="ticket ${ticket.priority.toString().toLowerCase() }">
							<h3>
								Konu:
								<c:out value="${ticket.title}" />
							</h3>
							<p>
								Mesaj:
								<c:out value="${ticket.content}" />
							</p>
							<span>Gönderen: <c:out value="${ticket.user.name}" /></span>
							<div class="ticket-actions">
								<button class="ticket-btn delete-btn" name="deleteTicket"
									value="${ticket.id}">Sil</button>
								<button
									class="ticket-btn close-btn ${ticket.closed ? 'closed-ticket-btn' : ''}"
									name="closeTicket" ${ticket.closed ? 'disabled' : ''}
									value="${ticket.id}">Kapat</button>
								<button
									class="ticket-btn continue-btn ${ticket.closed ? 'closed-ticket-btn' : ''}"
									name="continueTicket" ${ticket.closed ? 'disabled' : ''}
									value="${ticket.id}">Devam Et</button>
								<button
									class="ticket-btn resolved-btn ${ticket.solved or ticket.closed ? 'closed-ticket-btn' : ''}"
									name="resolveTicket" ${ticket.closed ? 'disabled' : ''}
									value="${ticket.id}">Çözüldü</button>
							</div>

						</div>
					</c:forEach>
				</div>
			</form>
		</main>
	</div>
</body>
</html>


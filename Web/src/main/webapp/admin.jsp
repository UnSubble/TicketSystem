<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./resource/adminPageStylesheet.css">
<title>Admin Panel</title>
</head>
<body>
	<div class="admin-container">
		<header class="admin-header">
			<h1>Admin Panel</h1>
			<button class="logout-btn">Çıkış Yap</button>
		</header>
		<main class="admin-content">
			<h2>Gelen Ticketlar</h2>
			<form action="/Web/ticket" method="GET">
				<div class="tickets">
					<c:forEach var="ticket" items="${listOfTickets}">
						<div class="ticket">
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
								<button class="ticket-btn close-btn" name="closeTicket"
									value="${ticket.id}">Kapat</button>
								<button class="ticket-btn continue-btn" name="continueTicket"
									value="${ticket.id}">Devam Et</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</form>
		</main>
	</div>
</body>
</html>


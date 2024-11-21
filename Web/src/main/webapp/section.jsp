<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ticket Details</title>
    <link rel="stylesheet" type="text/css" href="./resource/sectionPageStylesheet.css">
</head>
<body>
    <div class="container">
        <div id="ticket-container" class="ticket-box">
            <h2>Ticket Detayları</h2>
            <p><strong>Konu:</strong> ${ticket.title}</p>
            <p><strong>İçerik:</strong> ${ticket.content}</p>
        </div>
        <div class="items-section">
            <h3>Yorumlar</h3>
            <c:forEach var="item" items="${ticket.items}">
                <div class="item-container">
                    <p><strong>${item.user.name}:</strong> ${item.content}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>

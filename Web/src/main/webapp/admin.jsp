<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<div class="tickets">
				<div class="ticket">
					<h3>Konu: Örnek Sorun</h3>
					<p>Mesaj: Bir sorunum var...</p>
					<span>Gönderen: kullanıcı@example.com</span>
				</div>
				<div class="ticket">
					<h3>Konu: Örnek Sorun 2</h3>
					<p>Mesaj: Bir başka sorunum var...</p>
					<span>Gönderen: kullanıcı2@example.com</span>
				</div>
			</div>
		</main>
	</div>
</body>
</html>

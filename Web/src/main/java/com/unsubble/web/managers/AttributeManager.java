package com.unsubble.web.managers;


import java.io.IOException;

import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AttributeManager {

	private AttributeManager() {
		throw new AssertionError();
	}
	
	public static void setTicketListAsAttribute(String targetUsername, HttpSession session, HttpServletResponse resp) throws IOException {
		AdminController adminController = AdminController.getInstance();
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		String username = session.getAttribute("username").toString();
		if (adminController.isAdmin(username)) {
			session.setAttribute("listOfTickets", ticketController.getAllTickets());
			resp.sendRedirect("/Web/adminPage.jsp");
		} else {
			UserRepositoryController userController = UserRepositoryController.getInstance();
			User user = userController.getUser(targetUsername);
			session.setAttribute("ticketsBelongsToUser", ticketController.getAllTicketsByUser(user));
			resp.sendRedirect("/Web/userProfilePage.jsp");
		}
	}
}

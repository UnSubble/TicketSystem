package com.unsubble.web.servlets;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.backend.model.Ticket;
import com.unsubble.backend.model.Ticket.Priority;
import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;
import com.unsubble.web.managers.PermissionChecker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/section")
public class TicketCreatorServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (PermissionChecker.redirectNonLogins(req, resp) || !"true".equals(req.getParameter("newTicket")))
			return;
		Object usernameObj = req.getSession().getAttribute("username");
		AdminController adminController = AdminController.getInstance();
		if (usernameObj != null && !adminController.isAdmin(usernameObj.toString())) {
			String username = usernameObj.toString();
			UserRepositoryController userController = UserRepositoryController.getInstance();
			TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
			User user = userController.getUser(username);
			Ticket ticket = new Ticket(user, "", "", Priority.LOW);
			if (ticketController.addTicket(ticket)) {
				req.getSession().setAttribute("ticket", ticket);
				resp.sendRedirect("/Web/ticket?createTicket=true");
			} else {
				Logger logger = LogManager.getLogger();
				logger.log(Level.ERROR, "Something went wrong while creating the ticket.");
				req.getRequestDispatcher("userProfilePage.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("userProfilePage.jsp").forward(req, resp);
		}
	}
}

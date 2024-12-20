package com.unsubble.web.servlets;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;
import com.unsubble.web.managers.PermissionChecker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/*")
public class PermissionCheckerServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;

	private void redirectAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (UserRepositoryController.getInstance().matches(username, password)
				&& AdminController.getInstance().isAdmin(username)) {
			TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
			Logger logger = LogManager.getLogger();
			logger.log(Level.INFO, "An admin joined to the system. Let's welcome him. Welcome " + username);
			req.getSession().setAttribute("listOfTickets", ticketController.getAllTickets());
			req.getRequestDispatcher("adminPage.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/Web/loginPage.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (PermissionChecker.redirectNonLogins(req, resp))
			return;
		redirectAdmin(req, resp);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (PermissionChecker.redirectNonLogins(req, resp))
			return;
		redirectAdmin(req, resp);
	}

}

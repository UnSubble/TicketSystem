package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Date;

import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/auth")
public class Authentication extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserRepositoryController userController = UserRepositoryController.getInstance();
		boolean isMatch = userController.matches(username, password);
		if (isMatch) {
			User targetUser = userController.getUser(username);
			targetUser.setLastLogin(new Date());
			req.getSession().setAttribute("username", username);
			if (AdminController.getInstance().isAdmin(username)) {
				req.getRequestDispatcher("admin").forward(req, resp);
			} else {
				TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
				req.getSession().setAttribute("ticketsBelongsToUser", ticketController.getAllTicketsByUser(targetUser));
				RequestDispatcher dispatcher = req.getRequestDispatcher("userProfile.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			req.getServletContext().setAttribute("error", 1);
			resp.sendRedirect("/Web/auth");
		}
	}

}

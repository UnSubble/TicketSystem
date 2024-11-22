package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Date;

import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;
import com.unsubble.web.utils.ObjectsUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/auth")
public class Authentication extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object usernameObj = session.getAttribute("username");
		ObjectsUtil.ifNotNullThenCatched(usernameObj, () -> {
			session.setMaxInactiveInterval(60 * 60); // An hour
			if (AdminController.getInstance().isAdmin(usernameObj.toString()))
				req.getRequestDispatcher("admin.jsp").forward(req, resp);
			else
				req.getRequestDispatcher("userProfile.jsp").forward(req, resp);
		}, () -> req.getRequestDispatcher("loginPage.jsp").forward(req, resp));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserRepositoryController userController = UserRepositoryController.getInstance();
		HttpSession session = req.getSession();
		boolean isMatch = userController.matches(username, password);
		if (isMatch) {
			User targetUser = userController.getUser(username);
			targetUser.setLastLogin(new Date());
			session.setAttribute("username", username);
			if (AdminController.getInstance().isAdmin(username)) {
				req.getRequestDispatcher("admin").forward(req, resp);
			} else {
				TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
				session.setAttribute("ticketsBelongsToUser", ticketController.getAllTicketsByUser(targetUser));
				RequestDispatcher dispatcher = req.getRequestDispatcher("userProfile.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			req.getServletContext().setAttribute("error", 1);
			resp.sendRedirect("/Web/auth");
		}
	}

}

package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Date;

import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.UserRepositoryController;
import com.unsubble.web.managers.AttributeManager;
import com.unsubble.web.utils.ObjectsUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/auth")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object usernameObj = session.getAttribute("username");
		ObjectsUtil.ifNotNullThenCatched(usernameObj, () -> {
			session.setMaxInactiveInterval(60 * 60); // An hour
			if (AdminController.getInstance().isAdmin(usernameObj.toString())) {
				req.getRequestDispatcher("adminPage.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("userProfilePage.jsp").forward(req, resp);
			}
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
			session.setAttribute("user", targetUser);
			AttributeManager.setTicketListAsAttribute(username, session, resp);
		} else {
			req.setAttribute("error", 1);
			req.getRequestDispatcher("loginPage.jsp").forward(req, resp);
		}
	}

}

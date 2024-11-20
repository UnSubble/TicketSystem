package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Date;

import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.UserRepositoryController;

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
		UserRepositoryController controller = UserRepositoryController.getInstance();
		boolean isMatch = controller.matches(username, password);
		if (isMatch) {
			controller.getUser(username).setLastLogin(new Date());
			req.getServletContext().setAttribute("username", username);
			if (AdminController.getInstance().getAdmin(username).isPresent()) {
				req.getRequestDispatcher("admin.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("/Web/userProfile.jsp");
			}
		} else {
			req.getServletContext().setAttribute("error", 1);
			resp.sendRedirect("/Web/auth");
		}
	}

}

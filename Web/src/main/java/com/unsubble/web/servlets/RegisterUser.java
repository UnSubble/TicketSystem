package com.unsubble.web.servlets;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.UserRepositoryController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/register")
public class RegisterUser extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserRepositoryController controller = UserRepositoryController.getInstance();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (controller.isTaken(username)) {
			req.getServletContext().setAttribute("error", 2);
			resp.sendRedirect("/Web/auth");
		} else {
			Date now = new Date();
			User newUser = new User(username, password, now, now);
			if (!controller.addUser(newUser)) {
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	
}

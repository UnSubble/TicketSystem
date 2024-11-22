package com.unsubble.web.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.UserRepositoryController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/register")
public class RegistererServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserRepositoryController controller = UserRepositoryController.getInstance();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Logger logger = LogManager.getLogger();
		if (controller.isTaken(username)) {
			req.getServletContext().setAttribute("error", 2);
			resp.sendRedirect("/Web/auth");
		} else {
			Date now = new Date();
			User newUser = new User(username, password, now, now);
			if (controller.addUser(newUser)) {
				resp.sendRedirect("/Web/loginPage.jsp");
				logger.log(Level.INFO, "[INFO] %s has registered at %s".formatted(username, LocalDateTime.now()));
			} else {
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				logger.log(Level.ERROR, "An error occured while registering the user.");
			}
		}
	}

}

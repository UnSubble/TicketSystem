package com.unsubble.web;

import java.io.IOException;
import java.util.Date;

import com.unsubble.backend.connector.UserRepository;
import com.unsubble.backend.manager.RepoManager;
import com.unsubble.backend.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/register")
public class RegisterUser extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserRepository userRepo = RepoManager.getUserRepository();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (userRepo.matches(username, password)) {
			req.getServletContext().setAttribute("error", 2);
			resp.sendRedirect("/Web/auth");
		} else {
			Date now = new Date();
			User newUser = new User(username, password, now, now);
			userRepo.addUser(newUser);
		}
	}
	
	
}

package com.unsubble.web;

import java.io.IOException;

import com.unsubble.backend.manager.RepoManager;

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
		boolean isMatch = RepoManager.getUserRepository().matches(username, password);
		if (isMatch) {
			resp.sendRedirect(password);
		} else {
			req.getServletContext().setAttribute("error", true);
			resp.sendRedirect("/Web/auth");
		}
	}

}

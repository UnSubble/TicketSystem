package com.unsubble.web;

import java.io.IOException;

import com.unsubble.backend.manager.RepoManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/*" })
public class Admin extends HttpServlet {
	
	private void redirectAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (RepoManager.getUserRepository().matches(username, password) 
				&& "unsubble".equalsIgnoreCase(username)) {
			req.getRequestDispatcher("admin.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/Web/loginPage.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redirectAdmin(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redirectAdmin(req, resp);
	}
	
	
}

package com.unsubble.web.servlets;

import java.io.IOException;

import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.UserRepositoryController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/*")
public class AdminPermission extends HttpServlet {
	
	private void redirectAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (UserRepositoryController.getInstance().matches(username, password) 
				&& AdminController.getInstance().getAdmin(username).isPresent()) {
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

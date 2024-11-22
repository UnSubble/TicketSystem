package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object usernameObj = session.getAttribute("username");
		if (usernameObj != null) {
			Enumeration<String> enumerate = session.getAttributeNames();
			while (enumerate.hasMoreElements()) {
				session.removeAttribute(enumerate.nextElement());
			}
			session.invalidate();
			Logger logger = LogManager.getLogger();
			logger.log(Level.INFO, "%s has logged out.".formatted(usernameObj));
		}
		resp.sendRedirect("/Web/auth");
	}
}

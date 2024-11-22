package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.web.utils.ObjectsUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object usernameObj = session.getAttribute("username");
		ObjectsUtil.ifNotNullThenCatched(usernameObj, () -> {
			Enumeration<String> enumerate = session.getAttributeNames();
			while (enumerate.hasMoreElements()) {
				session.removeAttribute(enumerate.nextElement());
			}
			session.invalidate();
			Logger logger = LogManager.getLogger();
			logger.log(Level.INFO, "%s has logged out.".formatted(usernameObj));
		}, null);
		resp.sendRedirect("/Web/auth");
	}
}

package com.unsubble.web.managers;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionChecker {
	
	private PermissionChecker() {
		throw new AssertionError();
	}
	
	public static boolean redirectNonLogins(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Object usernameObj = session.getAttribute("username");
		if (usernameObj == null) {
			resp.sendRedirect("/Web/loginPage.jsp");
		}
		return usernameObj == null;
	}
}

package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.backend.model.Ticket;
import com.unsubble.backend.model.User;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ticket")
public class TicketPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> params = req.getParameterMap();
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		if (params.containsKey("deleteTicket")) {
			for (String idStr : params.get("deleteTicket")) {
				int id = Integer.parseInt(idStr);
				Optional<Ticket> optTicket = ticketController.getTicketById(id);
				if (optTicket.isEmpty()) {
					Logger logger = LogManager.getLogger();
					logger.log(Level.ERROR, "No tickets were found with the given ID.");
					return;
				}
				ticketController.removeTicket(optTicket.get());
			}
			String username = req.getSession().getAttribute("username").toString();
			if (AdminController.getInstance().isAdmin(username)) {
				req.getSession().setAttribute("listOfTickets", ticketController.getAllTickets());
				resp.sendRedirect("/Web/admin.jsp");
			} else {
				UserRepositoryController userController = UserRepositoryController.getInstance();
				User user = userController.getUser(username);
				req.getSession().setAttribute("ticketsBelongsToUser", ticketController.getAllTicketsByUser(user));
				resp.sendRedirect("/Web/userProfile.jsp");
			}
		}
		if (params.containsKey("continueTicket")) {
			// TODO
		}
	}
	
}

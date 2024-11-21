package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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

	private void performTicket(Consumer<Ticket> job, String... params) throws ServletException, IOException {
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		for (String idStr : params) {
			int id = Integer.parseInt(idStr);
			Optional<Ticket> optTicket = ticketController.getTicketById(id);
			if (optTicket.isEmpty()) {
				Logger logger = LogManager.getLogger();
				logger.log(Level.ERROR, "No tickets were found with the given ID.");
				continue;
			}
			job.accept(optTicket.get());
		}
	}

	private void resetAttributes(HttpServletRequest req, HttpServletResponse resp, String usernameOnReq)
			throws IOException {
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		AdminController adminController = AdminController.getInstance();
		if (adminController.isAdmin(usernameOnReq)) {
			req.getSession().setAttribute("listOfTickets", ticketController.getAllTickets());
			resp.sendRedirect("/Web/admin.jsp");
		} else {
			UserRepositoryController userController = UserRepositoryController.getInstance();
			User user = userController.getUser(usernameOnReq);
			req.getSession().setAttribute("ticketsBelongsToUser", ticketController.getAllTicketsByUser(user));
			resp.sendRedirect("/Web/userProfile.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> params = req.getParameterMap();
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		AdminController adminController = AdminController.getInstance();
		String usernameOnReq = req.getSession().getAttribute("username").toString();
		boolean op = false;
		if (params.containsKey("deleteTicket")) {
			performTicket(ticketController::removeTicket, params.get("deleteTicket"));
			resetAttributes(req, resp, usernameOnReq);
			op = true;
		}
		if (adminController.isAdmin(usernameOnReq) && params.containsKey("closeTicket")) {
			performTicket(t -> t.setClosed(true), params.get("closeTicket"));
			resetAttributes(req, resp, usernameOnReq);
			op = true;
		}
		if (adminController.isAdmin(usernameOnReq) && params.containsKey("resolveTicket")) {
			performTicket(t -> t.setSolved(true), params.get("resolveTicket"));
			resetAttributes(req, resp, usernameOnReq);
			op = true;
		}
		if (!op && params.containsKey("continueTicket")) {
			performTicket(t -> {
				if (t.getUser().getName().equals(usernameOnReq) || adminController.isAdmin(usernameOnReq)) {
					req.getSession().setAttribute("ticket", t);
					try {
						req.getRequestDispatcher("section.jsp").forward(req, resp);
					} catch (ServletException | IOException e) {
						Logger logger = LogManager.getLogger();
						logger.log(Level.ERROR, "An error occurred while redirecting to the ticket");
						resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}
				}
			}, params.get("continueTicket"));
		}
	}

}

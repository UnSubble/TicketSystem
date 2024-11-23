package com.unsubble.web.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.backend.model.Ticket;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.managers.AttributeManager;
import com.unsubble.web.utils.ObjectsUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/ticket")
public class TicketHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;

	private void applyMatches(Consumer<Ticket> job, String... params) throws ServletException, IOException {
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

	private void saveTicket(Ticket ticket) {
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		ticketController.updateTicket(ticket);
	}

	private void performTicket(String usernameOnReq, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Map<String, String[]> params = req.getParameterMap();
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		AdminController adminController = AdminController.getInstance();
		boolean op = false;
		if (params.containsKey("deleteTicket")) {
			applyMatches(ticketController::removeTicket, params.get("deleteTicket"));
			AttributeManager.setTicketListAsAttribute(usernameOnReq, session, resp);
			op = true;
		}
		if (adminController.isAdmin(usernameOnReq) && params.containsKey("closeTicket")) {
			applyMatches(t -> {
				t.setClosed(true);
				saveTicket(t);
			}, params.get("closeTicket"));
			AttributeManager.setTicketListAsAttribute(usernameOnReq, session, resp);
			op = true;
		}
		if (adminController.isAdmin(usernameOnReq) && params.containsKey("resolveTicket")) {
			applyMatches(t -> {
				t.setSolved(true);
				saveTicket(t);
			}, params.get("resolveTicket"));
			AttributeManager.setTicketListAsAttribute(usernameOnReq, session, resp);
			op = true;
		}
		if (!op && params.containsKey("continueTicket")) {
			applyMatches(t -> {
				if (t.getUser().getName().equals(usernameOnReq) || adminController.isAdmin(usernameOnReq)) {
					req.getSession().setAttribute("ticket", t);
					try {
						req.getRequestDispatcher("sectionPage.jsp").forward(req, resp);
					} catch (ServletException | IOException e) {
						Logger logger = LogManager.getLogger();
						logger.log(Level.ERROR, "An error occurred while redirecting to the ticket.");
						resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}
				}
			}, params.get("continueTicket"));
		} else if (!op && params.containsKey("createTicket")) {
			if (!"true".equals(params.get("createTicket")[0]))
				return;
			Ticket ticket = (Ticket) req.getSession().getAttribute("ticket");
			ObjectsUtil.ifNotNullThenCatched(ticket,
					t -> req.getRequestDispatcher("sectionPage.jsp").forward(req, resp),
					t -> resp.sendRedirect("/Web/auth"));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object usernameObj = session.getAttribute("username");
		ObjectsUtil.ifNotNullThenCatched(usernameObj, obj -> performTicket(obj.toString(), req, resp),
				obj -> resp.sendRedirect("/Web/auth"));
	}

}

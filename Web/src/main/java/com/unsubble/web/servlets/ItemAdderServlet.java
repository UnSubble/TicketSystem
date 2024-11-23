package com.unsubble.web.servlets;

import java.io.IOException;

import com.unsubble.backend.model.SupportItem;
import com.unsubble.backend.model.Ticket;
import com.unsubble.backend.model.Ticket.Priority;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;
import com.unsubble.web.managers.AttributeManager;
import com.unsubble.web.managers.PermissionChecker;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/addItemToTicket")
public class ItemAdderServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;
	
	private void fillNewTicket(Ticket ticket, HttpServletRequest req) {
		String title = req.getParameter("title");
		Priority priority = Priority.valueOf(req.getParameter("priority"));
		String content = req.getParameter("commentContent");
		ticket.setTitle(title);
		ticket.setContent(content);
		ticket.setPriority(priority);
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		ticketController.updateTicket(ticket);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (PermissionChecker.redirectNonLogins(req, resp))
			return;
		HttpSession session = req.getSession();
		String username = session.getAttribute("username").toString();
		Ticket ticket = (Ticket) session.getAttribute("ticket");
		UserRepositoryController userController = UserRepositoryController.getInstance();
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		if (AdminController.getInstance().isAdmin(username) || ticket.getUser().getName().equals(username)) {
			if (ticket.getContent().isEmpty() && ticket.getTitle().isEmpty()) {
				fillNewTicket(ticket, req);
				AttributeManager.setTicketListAsAttribute(ticket.getUser().getName(), session, resp);
				return;
			} else {
				String content = req.getParameter("commentContent");
				SupportItem item = new SupportItem(ticket.getTitle(), content, userController.getUser(username));
				ticketController.addItemToTicket(ticket, item);
			}
		}
		req.getRequestDispatcher("sectionPage.jsp").forward(req, resp);
	}

}

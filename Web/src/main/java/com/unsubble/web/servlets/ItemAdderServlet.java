package com.unsubble.web.servlets;

import java.io.IOException;

import com.unsubble.backend.model.SupportItem;
import com.unsubble.backend.model.Ticket;
import com.unsubble.web.controllers.AdminController;
import com.unsubble.web.controllers.TicketRepositoryController;
import com.unsubble.web.controllers.UserRepositoryController;
import com.unsubble.web.managers.AttributeManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/addItemToTicket")
public class ItemAdderServlet extends HttpServlet {
	private static final long serialVersionUID = 20241123L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content = req.getParameter("commentContent");
		HttpSession session = req.getSession();
		String username = session.getAttribute("username").toString();
		Ticket ticket = (Ticket) session.getAttribute("ticket");
		UserRepositoryController userController = UserRepositoryController.getInstance();
		TicketRepositoryController ticketController = TicketRepositoryController.getInstance();
		if (AdminController.getInstance().isAdmin(username) || ticket.getUser().getName().equals(username)) {
			if (ticket.getContent().isEmpty() && ticket.getTitle().isEmpty()) {
				String title = content.split("\n")[0];
				content = content.substring(title.length()).trim();
				ticket.setTitle(title);
				ticket.setContent(content);
				AttributeManager.setTicketListAsAttribute(ticket.getUser().getName(), session, resp);
				return;
			} else {
				SupportItem item = new SupportItem(ticket.getTitle(), content, userController.getUser(username));
				ticketController.addItemToTicket(ticket, item);
			}
		}
		req.getRequestDispatcher("sectionPage.jsp").forward(req, resp);
	}

}

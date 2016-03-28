package fr.nations.amen.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessagesAmenServlet extends HttpServlet {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -8215580299321196367L;

	@Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    MessagesAmenUtils.insert(req.getParameter("who"), req.getParameter("message"),
	    		req.getParameter("subject"));
	    resp.sendRedirect("/indexForum.jsp");
	  }
	}

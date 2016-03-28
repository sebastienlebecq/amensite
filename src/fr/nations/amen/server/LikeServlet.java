package fr.nations.amen.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.client.Window;

public class LikeServlet extends HttpServlet {

	 @Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		 Window.alert("dopost");
		 resp.sendRedirect("amen/like");
		 
	}
	
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	    {
		 
		// Window.alert("doget");
		 resp.setHeader("Content-Type", "text/html");
	     resp.getWriter().println("coucou");
	    }
}

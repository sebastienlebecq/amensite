package fr.nations.amen.server;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AmenAuthServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8750649159429863282L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UserService userService = UserServiceFactory.getUserService();

        String thisURL = request.getRequestURI();
        if (request.getUserPrincipal() != null) {
            response.getWriter().println("<p>Hello, " +
                                         request.getUserPrincipal().getName() +
                                         "!  You can <a href=\"" +
                                         userService.createLogoutURL(thisURL) +
                                         "\">sign out</a>.</p>");
        } else {
            response.getWriter().println("<p>Please <a href=\"" +
                                         userService.createLoginURL(thisURL) +
                                         "\">sign in</a>.</p>");
        }
    }
}
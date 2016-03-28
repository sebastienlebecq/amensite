package fr.nations.amen.server.souvenirs;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.nations.amen.server.PMF;

public class InitCounter extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		VisitorCounter v = new VisitorCounter(1L, 10);
		pm.makePersistent(v);
		//VisitorCounter Counter = pm.getObjectById(VisitorCounter.class, 1L);
		//Counter.setCounter(10);
		
		
		
	}

	public InitCounter() {
		// TODO Auto-generated constructor stub
	}

}

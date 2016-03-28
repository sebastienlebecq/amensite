package fr.nations.amen.server.louanges;

//Répond sur /image

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.*;

public class GetLouangesUploadedServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();

		if (req.getParameter("blob-key") != null) {
			BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
			
			blobstoreService.serve(blobKey, resp);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doGet(req, resp);
	}
}
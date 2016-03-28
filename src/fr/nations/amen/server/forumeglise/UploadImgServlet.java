package fr.nations.amen.server.forumeglise;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class UploadImgServlet extends HttpServlet {    
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
//    	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
//    	        ImagesService imagesService = ImagesServiceFactory.getImagesService(); // Récupération du service d'images
//    	
//    	        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
//    	       List<BlobKey> blobKeys = blobs.get("uploadImg");
//    	        
//    	        // Récupération de l'URL de l'image
//    	       String urlImage = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));
//    	       
//    	       resp.setHeader("Content-Type", "text/html");
//    	       resp.getWriter().println(urlImage);
    	
    	
    	
    	 BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

         // Récupère une Map de tous les champs d'upload de fichiers
         Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

         // Récupère la liste des fichiers uploadés dans le champ "uploadedFile"
         // (il peut y en avoir plusieurs si c'est un champ d'upload multiple, d'où la List)
         List<BlobKey> blobKeys = blobs.get("uploadImg");
         
         System.out.println("uploadImg in UploadImgServlet");
         
         String cleFichierUploaded = blobKeys.get(0).getKeyString();
         
         resp.sendRedirect("/amen/uploadImg?id=" + cleFichierUploaded);
    	
   }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        System.out.println("entered do get");
      //Send the meta-data id back to the client in the HttpServletResponse response
      String id = req.getParameter("id");
      
      System.out.println("entered do get id is: " + id);
      resp.setHeader("Content-Type", "text/html");
      resp.getWriter().println(id);

    }
   
}
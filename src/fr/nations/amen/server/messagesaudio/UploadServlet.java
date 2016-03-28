package fr.nations.amen.server.messagesaudio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {    
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
            BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

            // Récupère une Map de tous les champs d'upload de fichiers
            Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

            // Récupère la liste des fichiers uploadés dans le champ "uploadedFile"
            // (il peut y en avoir plusieurs si c'est un champ d'upload multiple, d'où la List)
            List<BlobKey> blobKeys = blobs.get("uploadedFile");
            
            System.out.println("uploadedFile in UploadServlet");
            
            
            List<BlobKey> blobKeys1 = blobs.get("uploadedogg");
            
            List<BlobKey> blobKeys2 = blobs.get("uploadedwav");

            // Récupère la clé identifiant du fichier uploadé dans le Blobstore (à sauvegarder)
           String cleFichierUploaded = blobKeys.get(0).getKeyString();
           
           String cleuploadedogg = blobKeys1.get(0).getKeyString();
           
           String cleuploadedwav = blobKeys2.get(0).getKeyString();
           
        System.out.println("blobkeys à retourner:"+cleFichierUploaded+", "+cleuploadedogg+", "+cleuploadedwav);
        
//        ImagesService imagesService = ImagesServiceFactory.getImagesService(); 
//        
//        String urlImage = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));
//        
//        resp.sendRedirect("/amen/upload?id=" + cleFichierUploade+"&url="+urlImage);
        
        resp.sendRedirect("/amen/upload?id=" + cleFichierUploaded+ "&cleuploadedogg="+cleuploadedogg+"&cleuploadedwav="+cleuploadedwav);

//           PrintWriter p = resp.getWriter();
//           resp.setHeader("Content-Type", "text/html");
//           p.print("<p><label>Dans le cloud, le blob-key du fichier est : <input type='text' name='blobkey' value="
//           +cleFichierUploade+"'/></label></p>");
//           p.close();
   }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        System.out.println("entered do get");
      //Send the meta-data id back to the client in the HttpServletResponse response
      String id = req.getParameter("id");
      String cleuploadedogg = req.getParameter("cleuploadedogg");
      String cleuploadedwav = req.getParameter("cleuploadedwav");
      
      System.out.println("entered do get id is: " + id);
      resp.setHeader("Content-Type", "text/html");
      resp.getWriter().println(id+"/"+cleuploadedogg+"/"+cleuploadedwav);

    }
}

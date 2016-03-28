package fr.nations.amen.client.eglise.visionpublic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.Amen;
import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.views.EgliseView;
import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.client.youtube.ServiceYoutube;
import fr.nations.amen.shared.eglise.MenuEglise;
import fr.nations.amen.shared.eglise.MenuEgliseService;
import fr.nations.amen.shared.eglise.MenuEgliseServiceAsync;


public class MenuEgliseVision extends Composite  {
	
//	@UiField
//	HTML idNumberBox;
//
//	@UiField
//	HTML appellationBox;

	@UiField
	HTML descriptionBox;

//	@UiField
//	HTML dateMenuEgliseBox;

	@UiField
	VerticalPanel photoAreaBox;
	
	@UiField
	Frame iframePdf;
	
	private MenuEglise MenuEgliseInfo;
	 
	public MenuEgliseVision(MenuEglise MenuEglise) {
		
//MenuEgliseServiceAsync mEgliseService = GWT.create(MenuEgliseService.class); 
//		
//		Long id = new Long(longId);
//		
//		mEgliseService.getMenuEgliseById(id, new AsyncCallback<MenuEglise>() {
//
//			public void onSuccess(MenuEglise result) {
//				setMenuEglise(result);
//			}
//
//			public void onFailure(Throwable caught) {
//				// TODO: Show an error message.
//			}
//		});

		initWidget(uiBinder.createAndBindUi(this));
		this.setMenuEglise(MenuEglise);

	}
	
	public void setMenuEglise(final MenuEglise MenuEglise) {
		this.MenuEgliseInfo = MenuEglise;

		if (MenuEglise != null) {
//			idNumberBox.setText(MenuEglise.getId());
//			appellationBox.setText(MenuEglise.getAppellation());
			descriptionBox.setHTML(MenuEglise.getDescription());
//			dateMenuEgliseBox.setHTML(MenuEglise.getDate().toGMTString());
			
			if (MenuEgliseInfo.getUrlpdf()!=null)
				this.iframePdf.setUrl(MenuEgliseInfo.getUrlpdf());
			else 
				this.iframePdf.setVisible(false);

			photoAreaBox.clear();

			// A partir de l'identifiant d'album (obtenu après
			// l'enregistrement des photos du MenuEglise dans l'app web picasa)
			// rapprocher le fonctionnement des projets "picasa_app-demo",
			// "picavue-ro" et "gtiach11" pour implémenter un service json
			// sollicitant Picasa
			// et affichaant par JSNI les photos

			// ensemble des albums :
			// http://picasaweb.google.com/data/feed/api/user/slebecq?kind=album

			// pour obtenir l'album MenuEglise de mon profil public
			// où l'id de l'album provient de l'espace web picasa>
			// partager en mode public>lien, dernier paramètre :

			String refPicasa = MenuEglise.getAlbumRefPicasa();
			String refYoutube = MenuEglise.getRefYoutube();

			// Window.alert("refPicasa:"+ refPicasa);

			if ((refPicasa == null || refPicasa == "" || refPicasa.isEmpty())) {
				// Window.alert("aucune photo");
			}
			else {
				ServiceAmenPicasa.getPhotos(refPicasa, photoAreaBox);

			}

			if ((refYoutube == null || refYoutube == "" || refYoutube.isEmpty())) {
				//Window.alert("aucune video");
			}
			else {

//				SimplePanel panel = new SimplePanel(); // create panel to hold
//														// the player
				
				if (refYoutube.contains(";")){
					
					String[] strarray = refYoutube.split(";");
					
					for(String str : strarray){
						
						if(!str.isEmpty())
						photoAreaBox.add(ServiceYoutube.getYoutubePanel(str));
					}
					
				}
				else{
			
				photoAreaBox.add(ServiceYoutube.getYoutubePanel(refYoutube));
				}
			}
			
			//load old comments
//			 commentsPanel = new CommentsPanel(MenuEglise.getId());
//			 photoAreaBox.add(commentsPanel);
//			// commentsPanel.loadComments(MenuEglise.getId());
//			 
//			 CommentSubmitForm commentSubmitForm = new CommentSubmitForm(MenuEgliseInfo, commentsPanel);
//			 photoAreaBox.add(commentSubmitForm);
		}

			
	}

	private static MenuEgliseVisionUiBinder uiBinder = GWT
			.create(MenuEgliseVisionUiBinder.class);

//    interface Resources extends ClientBundle {
//        @Source("visiontitle.png") public ImageResource imgvisiontitle();
//    }
	
	interface MenuEgliseVisionUiBinder extends
			UiBinder<Widget, MenuEgliseVision> {
	}

}

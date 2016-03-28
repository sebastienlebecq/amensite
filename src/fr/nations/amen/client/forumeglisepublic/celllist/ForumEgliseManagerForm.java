package fr.nations.amen.client.forumeglisepublic.celllist;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.forumeglisepublic.celllist.comments.CommentSubmitForm;
import fr.nations.amen.client.forumeglisepublic.celllist.comments.CommentsPanel;
import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.client.welcome.CommentFESubmitForm;
import fr.nations.amen.client.welcome.CommentsFEPanel;
import fr.nations.amen.client.youtube.ServiceYoutube;
import fr.nations.amen.shared.forumeglise.ForumEglise;
import fr.nations.amen.shared.forumeglise.ForumEgliseService;
import fr.nations.amen.shared.forumeglise.ForumEgliseServiceAsync;

public class ForumEgliseManagerForm extends Composite {
	
	 ForumEgliseServiceAsync souvenirService = GWT.create(ForumEgliseService.class);
	
	private static SouvenirManagerFormUiBinder uiBinder = GWT
			.create(SouvenirManagerFormUiBinder.class);

	interface SouvenirManagerFormUiBinder extends
			UiBinder<Widget, ForumEgliseManagerForm> {
	}
	
	 private static  CommentsFEPanel commentsFEPanel;

	public ForumEgliseManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));
		//setSouvenir(null);
		//photoAreaBox.clear();
	}
	
//	@UiField
//	HTML categoryBox;

	@UiField
	HTML idNumberBox;

	@UiField
	HTML appellationBox;

	@UiField
	HTML descriptionBox;

//	@UiField
//	HTML dateSouvenirBox;

	@UiField
	VerticalPanel photoAreaBox;
	
//	@UiField
//	HTML indexInCellsBox;
	
	private static  CommentsPanel commentsPanel;

	private ForumEglise souvenirInfo;
	// private AllDataAsyncDataProvider dataProvider;
	private CellList<ForumEglise> souvenirList;
	private ForumEglise actuInfo2;

	public ForumEgliseManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private void initializeBox() {

		ForumEglise initActu = new ForumEglise("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		setSouvenir(new ForumEglise(""));

	}

	public void setSouvenir(final ForumEglise souvenir) {
		

		this.souvenirInfo = souvenir;

		if (souvenir != null) {
			
			ForumEgliseManagerForm.this.setVisible(true);
			
			photoAreaBox.clear();
//			categoryBox.setVisible(true);
			idNumberBox.setVisible(true);
			appellationBox.setVisible(true);
			descriptionBox.setVisible(true);
//			dateSouvenirBox.setVisible(true);
//			indexInCellsBox.setVisible(true);
			
			
			idNumberBox.setText(souvenir.getId());
//			if (souvenir.getCategory().startsWith("cat")){
//				categoryBox.setHTML(UniformDim.categoriesSouvenirs[Integer.valueOf(
//						souvenir.getCategory().substring(3))]);
//			}
			appellationBox.setText(souvenir.getAppellation());
			descriptionBox.setHTML(souvenir.getDescription());
			// this.imgSouvenirBox.set
//			dateSouvenirBox.setHTML(souvenir.getDate().toGMTString());
//			indexInCellsBox.setHTML(souvenir.getIndexInCells());
			photoAreaBox.clear();

			// A partir de l'identifiant d'album (obtenu après
			// l'enregistrement des photos du souvenir dans l'app web picasa)
			// rapprocher le fonctionnement des projets "picasa_app-demo",
			// "picavue-ro" et "gtiach11" pour implémenter un service json
			// sollicitant Picasa
			// et affichaant par JSNI les photos

			// ensemble des albums :
			// http://picasaweb.google.com/data/feed/api/user/slebecq?kind=album

			// pour obtenir l'album souvenir de mon profil public
			// où l'id de l'album provient de l'espace web picasa>
			// partager en mode public>lien, dernier paramètre :

			String refPicasa = souvenir.getAlbumRefPicasa();
			String refYoutube = souvenir.getRefYoutube();

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
			
//			//load old comments
//			 commentsPanel = new CommentsPanel(souvenir.getId());
//			 photoAreaBox.add(commentsPanel);
//			// commentsPanel.loadComments(souvenir.getId());
//			 
//			 CommentSubmitForm commentSubmitForm = new CommentSubmitForm(souvenirInfo, commentsPanel);
//			 photoAreaBox.add(commentSubmitForm);
			
			
			commentsFEPanel = new CommentsFEPanel(souvenir.getId());
			photoAreaBox.add(commentsFEPanel);
			// commentsPanel.loadComments(souvenir.getId());
			 
			 CommentFESubmitForm commentSubmitForm = new CommentFESubmitForm(souvenir.getId(),
					 souvenir.getAppellation(), commentsFEPanel);
			 photoAreaBox.add(commentSubmitForm);
			
			
			
		}
		else {
			ForumEgliseManagerForm.this.setVisible(false);
			
				photoAreaBox.clear();
				
//				categoryBox.setVisible(false);
				idNumberBox.setVisible(false);
				appellationBox.setVisible(false);
				descriptionBox.setVisible(false);
//				dateSouvenirBox.setVisible(false);
//				this.indexInCellsBox.setVisible(false);
				
				photoAreaBox.add(new HTML("<H2>Veuillez S'il vous plait selectionner dans la colonne de gauche <br>" +
						" les chants que vous souhaitez ecouter</H2>"));
				
				
		}

		
	}

	public void setDisplay(CellList<ForumEglise> actuList) {
		this.souvenirList = actuList;

	}

}

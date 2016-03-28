package fr.nations.amen.client.louangespublic.celllist;

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
import fr.nations.amen.client.louangespublic.celllist.comments.CommentSubmitForm;
import fr.nations.amen.client.louangespublic.celllist.comments.CommentsPanel;
import fr.nations.amen.client.mvp.places.LouangeDetailsPlace;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.client.rezosocio.ButtonFactory;
import fr.nations.amen.client.souvenirspublic.celllist.SouvenirManagerForm;
import fr.nations.amen.client.youtube.ServiceYoutube;
import fr.nations.amen.shared.louanges.Louange;

public class LouangesManagerForm extends Composite {

	// The maximum number of results to retrieve.
	final int MAX_RESULTS = 12;

	private static LouangesManagerFormUiBinder uiBinder = GWT
			.create(LouangesManagerFormUiBinder.class);

	interface LouangesManagerFormUiBinder extends
			UiBinder<Widget, LouangesManagerForm> {
	}

	public LouangesManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));

		setSouvenir(null);
		photoAreaBox.clear();
	}

//	@UiField
//	HTML goFacebook;
	
	@UiField
	HTML numeroBox;

	@UiField
	VerticalPanel myAudioBox;
	//AudioWidget messageAudio;

	@UiField
	HTML categoryBox;

	// @UiField
	// HTML blobKeyBox;

//	@UiField
//	HTML idNumberBox;

	@UiField
	HTML appellationBox;

	@UiField
	HTML descriptionBox;

	@UiField
	HTML dateSouvenirBox;

	@UiField
	VerticalPanel photoAreaBox;

//	@UiField
//	HTML indexInCellsBox;

	private static CommentsPanel commentsPanel;

	private Louange souvenirInfo;
	private CellList<Louange> souvenirList;

	private LouangeDetailsPlace place;

	public LouangesManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	// private void initializeBox() {
	//
	// Louange initActu = new Louange("");
	// initActu.setAppellation("");
	// initActu.setDescription("");
	// initActu.setDate(new Date());
	// setSouvenir(new Louange(""));
	//
	// }

	public void setSouvenir(Louange souvenir) {
		
		String srcImg = "";
		String description = ""; //TODO : supprimer balises html via regex
		
		/*import java.util.regex.Pattern;
		import java.util.regex.Matcher;
		 
		public class Regex {
			public static void main(String[] args) {
				String chaine = "Test regex Java pour <balise1>Wikibooks</balise1> francophone.";
				Pattern p = Pattern.compile("<.*>(.*)<.*>");
				Matcher m = p.matcher(chaine);
				while(m.find())
					System.out.println(m.group(1));
			}
		}*/
		
		
		String link = "";
		String name="";
		
		if (souvenir!=null){
				srcImg = "http://www.mc-amen.net/amen/getSouveniruploadImg?blob-key="
				+ souvenir.getThubnailUrl();
		
				name = souvenir.getSujet();
				
				if (souvenir.getDescription()!=null & souvenir.getDescription().length()>40){
					
					description = souvenir.getDescription().substring(0, 37)+"...";
				} else {
					description = souvenir.getDescription();
				}
				
				LouangeDetailsPlace sPlace = this.getPlace();
				
				if (sPlace!=null)
				//http://www.mc-amen.net/#Souvenir:4912910010679296&0&cat0
				link = "http://www.mc-amen.net/Souvenir:"+sPlace.getLouangeId()
						+"&"+sPlace.getStartPage()+"&"+sPlace.getCategory();
				//TODO: le hashtag bloque la reconnaissance dans facebook : le changer par programmation
				
				
		}
		
		//TODO: update to OpenGraph
		
		/*goFacebook.setHTML("<center><a href=\"https://www.facebook.com/dialog/share_open_graph?" +
				"app_id=401100423399950" +
				"&display=popup" +
				"&action_type=og.likes"+
				 "image[0][url]="+link+
				 "image[0][user_generated]=true"+
//				"&link=" + link +
				"&redirect_uri=http://www.mc-amen.net/" +
				"&name="+name+
				//"&picture=http://www.mc-amen.net/images/20130707_114518r2.jpg\">Partager</a>");
				"&picture="+srcImg+"" +
				"\"><img src='/images/shareFacebook.jpg' alt='Share on Facebook' height='40' width='200'></a></center><br>"
				);*/
		
//		goFacebook.setHTML("<center><a href=\"https://www.facebook.com/dialog/feed?" +
//				"app_id=401100423399950" +
//				"&display=popup" +
//				"&description="  +name +
//				"&link=" + link +
//				"&redirect_uri=http://www.mc-amen.net/" +
//				"&name="+name +
//				//"&picture=http://www.mc-amen.net/images/20130707_114518r2.jpg\">Partager</a>");
//				"&picture="+srcImg+"\"><img src='/images/shareFacebook.jpg' alt='Share on Facebook' height='40' width='200'></a></center><br>");

//		goFacebook.setStyleName("bgLinkFacebook");
		
		this.souvenirInfo = souvenir;

		if (souvenir != null) {

			LouangesManagerForm.this.setVisible(true);

			photoAreaBox.clear();
			numeroBox.setVisible(true);
			categoryBox.setVisible(true);
			//idNumberBox.setVisible(true);
			appellationBox.setVisible(true);
			descriptionBox.setVisible(true);
			dateSouvenirBox.setVisible(true);
			//indexInCellsBox.setVisible(true);

			//idNumberBox.setText(souvenir.getId());
			if (souvenir.getCategory().startsWith("cat")) {
				categoryBox.setHTML(UniformDim.categoriesLouanges[Integer
						.valueOf(souvenir.getCategory().substring(3))]);
			}
			appellationBox.setText(souvenir.getSujet());
			descriptionBox.setHTML(souvenir.getDescription());
			dateSouvenirBox.setHTML(souvenir.getDate().toGMTString());
			this.numeroBox.setHTML(souvenir.getNumero());
			//this.indexInCellsBox.setHTML(souvenir.getIndexInCells());

			photoAreaBox.clear();
			myAudioBox.clear();
			
			// ButtonFactory.getInstance().drawPlusOne(photoAreaBox);
			// ButtonFactory.getInstance().drawfblike(photoAreaBox);
			 
			 
			 myAudioBox.add(new HTML("<audio controls>" +
			 		"<source src='/amen/getMessageAudioUploaded?blob-key="
					+ souvenir.getBlobKeyogg()+"' type='audio/ogg'>" +
			 		"<source src='/amen/getMessageAudioUploaded?blob-key="
					+ souvenir.getBlobKey()+"' type='audio/mpeg'>" +
					"<source src='/amen/getMessageAudioUploaded?blob-key="
					+ souvenir.getBlobKeywav()+"' type='audio/wav'>" +
					"Your browser does not support the audio element of HTML5." +
			 		"</audio>"));

//			messageAudio.setSrcMp3("/amen/getMessageAudioUploaded?blob-key="
//					+ souvenir.getBlobKey());
//			messageAudio.setSrcogg("/amen/getMessageAudioUploaded?blob-key="
//					+ souvenir.getBlobKeyogg());
//			messageAudio.setSrcwav("/amen/getMessageAudioUploaded?blob-key="
//					+ souvenir.getBlobKeywav());
			
			
			

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

			if (refPicasa != null && refPicasa != "" && !refPicasa.isEmpty()) {
			
				ServiceAmenPicasa.getPhotos(refPicasa, photoAreaBox);

			}

			if (refYoutube != null && refYoutube != "" && !refYoutube.isEmpty()) {
			

				// SimplePanel panel = new SimplePanel(); // create panel to
				// hold
				// // the player

				if (refYoutube.contains(";")) {

					String[] strarray = refYoutube.split(";");

					for (String str : strarray) {

//						if (!str.isEmpty())
//							photoAreaBox.add(ServiceYoutube
//									.getYoutubePanel(str));
						
						photoAreaBox.add(new HTML("<iframe width='610' height='344' " +
								"src='https://www.youtube.com/embed/"+str+"' frameborder='0' " +
										"allowfullscreen></iframe>"));

					}

				} else {

					photoAreaBox.add( new HTML("<iframe width='610' height='344' " +
							"src='https://www.youtube.com/embed/"+refYoutube+"' frameborder='0' " +
									"allowfullscreen></iframe>"));
					//photoAreaBox
					//		.add(ServiceYoutube.getYoutubePanel(refYoutube));
				}
			}

			// load old comments
			commentsPanel = new CommentsPanel(souvenir.getId());
			photoAreaBox.add(commentsPanel);
			// commentsPanel.loadComments(souvenir.getId());

			CommentSubmitForm commentSubmitForm = new CommentSubmitForm(
					souvenirInfo, commentsPanel);
			photoAreaBox.add(commentSubmitForm);
		} else { // else if (souvenir != null)
			LouangesManagerForm.this.setVisible(false);

			photoAreaBox.clear();
			numeroBox.setVisible(false);
			categoryBox.setVisible(false);
			//idNumberBox.setVisible(false);
			appellationBox.setVisible(false);
			descriptionBox.setVisible(false);
			dateSouvenirBox.setVisible(false);

			photoAreaBox.add(new HTML(
					"<H2>Veuillez S'il vous plait selectionner dans la colonne de gauche <br>"
							+ " les chants que vous souhaitez ecouter</H2>"));

			 photoAreaBox.setVisible(true);
		}

	}

	private LouangeDetailsPlace getPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDisplay(CellList<Louange> actuList) {
		this.souvenirList = actuList;
	}

	public void setPlace(LouangeDetailsPlace place) {
		this.place = place;
		
	}

}

package fr.nations.amen.client.souvenirspublic.celllist;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sun.java.swing.plaf.windows.resources.windows;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.places.SouvenirDetailsPlace;
import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.client.rezosocio.ButtonFactory;
import fr.nations.amen.client.souvenirspublic.celllist.comments.CommentSubmitForm;
import fr.nations.amen.client.souvenirspublic.celllist.comments.CommentSubmitForm.CommentSubmitHandler;
import fr.nations.amen.client.souvenirspublic.celllist.comments.CommentsPanel;
import fr.nations.amen.client.youtube.ServiceYoutube;
import fr.nations.amen.shared.souvenirs.Commentaire;
import fr.nations.amen.shared.souvenirs.Souvenir;
import fr.nations.amen.shared.souvenirs.SouvenirsService;
import fr.nations.amen.shared.souvenirs.SouvenirsServiceAsync;

public class SouvenirManagerForm extends Composite {
	
	 SouvenirsServiceAsync souvenirService = GWT.create(SouvenirsService.class);
	
	private static SouvenirManagerFormUiBinder uiBinder = GWT
			.create(SouvenirManagerFormUiBinder.class);

	interface SouvenirManagerFormUiBinder extends
			UiBinder<Widget, SouvenirManagerForm> {
	}

	public SouvenirManagerForm() {
		

		initWidget(uiBinder.createAndBindUi(this));
		//setSouvenir(null);
		//photoAreaBox.clear();
	}

	// @UiField
	// FlowPanel photoArea;
	
	/*@UiField
	HTML goFacebook;*/
	
	@UiField
	HTML categoryBox;

	@UiField
	HTML idNumberBox;

	@UiField
	HTML appellationBox;

	@UiField
	HTML descriptionBox;
	
	@UiField
	HTML videoBox;

	@UiField
	HTML dateSouvenirBox;

	@UiField
	VerticalPanel photoAreaBox;
	
//	@UiField
//	HTML indexInCellsBox;
	
	@UiField
	Frame iframePdf;
	
	private static  CommentsPanel commentsPanel;

	private Souvenir souvenirInfo;
	// private AllDataAsyncDataProvider dataProvider;
	private CellList<Souvenir> souvenirList;
	private Souvenir actuInfo2;

	private SouvenirDetailsPlace place;

	public SouvenirManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
			}

	private void initializeBox() {

		Souvenir initActu = new Souvenir("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		setSouvenir(new Souvenir(""));

	}

	public void setSouvenir(final Souvenir souvenir) {
		
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
		
				name = souvenir.getAppellation();
				
				if (souvenir.getDescription()!=null & souvenir.getDescription().length()>40){
					
					description = souvenir.getDescription().substring(0, 37)+"...";
				} else {
					description = souvenir.getDescription();
				}
				
				SouvenirDetailsPlace sPlace = this.getPlace();
				
				if (sPlace!=null)
				//http://www.mc-amen.net/#Souvenir:4912910010679296&0&cat0
				link = "http://www.mc-amen.net/Souvenir:"+sPlace.getSouvenirId()
						+"&"+sPlace.getStartPage()+"&"+sPlace.getCategory();
				//TODO: le hashtag bloque la reconnaissance dans facebook : le changer par programmation
				
				
		}
		
		//TODO: update to OpenGraph
/*		goFacebook.setHTML("<center><a href=\"https://www.facebook.com/dialog/feed?" +
				"app_id=401100423399950" +
				"&display=popup" +
				"&description="  +name +
				"&link="+link +
				//"&redirect_uri=https://apps.facebook.com/mission-amen" +
				"&redirect_uri=https://stmichelamen.appspot.com/" +
				"&name="+name +
				//"&picture=http://www.mc-amen.net/images/20130707_114518r2.jpg\">Partager</a>");
				"&picture="+srcImg+"\"><img src='/images/shareFacebook.jpg' alt='Share on Facebook' height='40' width='200'></a></center><br>");

		goFacebook.setStyleName("bgLinkFacebook");*/
		
		this.souvenirInfo = souvenir;

		if (souvenir != null) {

			SouvenirManagerForm.this.setVisible(true);
			
			photoAreaBox.clear();
			categoryBox.setVisible(true);
			idNumberBox.setVisible(true);
			appellationBox.setVisible(true);
			descriptionBox.setVisible(true);
			dateSouvenirBox.setVisible(true);
			//indexInCellsBox.setVisible(true);
			iframePdf.setVisible(true);
			videoBox.setVisible(true);
			
			
			idNumberBox.setText(souvenir.getId());
			if (souvenir.getCategory().startsWith("cat")){
				categoryBox.setHTML(UniformDim.categoriesSouvenirs[Integer.valueOf(
						souvenir.getCategory().substring(3))]);
			}
			appellationBox.setText(souvenir.getAppellation());
			descriptionBox.setHTML(souvenir.getDescription());
			
			//"Narcisse.webm"
			//if (souvenir.getRefYoutube()!=null || !souvenir.getRefYoutube().equals("") ){
//			if (souvenir.getVideoMp4()!=null || !souvenir.getVideoMp4().equals("") ){
//			videoBox.setHTML("<video " +
//					" id='myvideo' controls='controls' preload='none'>" +
//					" <source type='video/webm' src="+souvenir.getVideoMp4()+" />" +
//					"</video>");
			
			//videoBox.setHTML("<video width='640' height='360' id='player1' controls='controls' preload='true'>"+
		    //"<source type='video/youtube' src='http://www.youtube.com/watch?v="+souvenir.getRefYoutube()+"' />"+
		    //"</video>");
			
			
			//}
			//else {
			//	videoBox.setVisible(false);
			//}
			// <source type="video/mp4" src="myvideo.mp4" />
		    //<source type="video/webm" src="myvideo.webm" />
		    //<source type="video/ogg" src="myvideo.ogv" />
			
			// this.imgSouvenirBox.set
			dateSouvenirBox.setHTML(souvenir.getDate().toGMTString());
			//indexInCellsBox.setHTML(souvenir.getIndexInCells());
			
			if (souvenir.getUrlpdf()!=null && !souvenir.getUrlpdf().equals(""))
				this.iframePdf.setUrl(souvenir.getUrlpdf());
			else 
				this.iframePdf.setVisible(false);
				
			//this.iframePdf.setUrl("https://drive.google.com/file/d/0B2pfwCXJf2zVbXh6UWVhVmhJeHc/view?usp=sharing");
			photoAreaBox.clear();
			
			// ButtonFactory.getInstance().drawPlusOne(photoAreaBox);
			// ButtonFactory.getInstance().drawfblike(photoAreaBox);

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
			String refGDrive = souvenir.getVideoGDrive();

			// Window.alert("refPicasa:"+ refPicasa);

			if (refPicasa != null && refPicasa != "" && !refPicasa.isEmpty()) {
				// Window.alert("aucune photo");
			
				ServiceAmenPicasa.getPhotos(refPicasa, photoAreaBox);
				
				//TODO : remplacement avec iframe :
				//<iframe id="ytplayer" type="text/html" width="720" height="405"
				//		src="https://www.youtube.com/embed/M7lc1UVf-VE"
				//		frameborder="0" allowfullscreen>
			}

			if (refYoutube != null && refYoutube != "" && !refYoutube.isEmpty()) {
				
				if (refYoutube.contains(";")){
					
					//TODO : mettre composant de liste
					String[] strarray = refYoutube.split(";");
					
					for(String str : strarray){
						
						if(!str.isEmpty())
						//photoAreaBox.add(ServiceYoutube.getYoutubePanel(str));
						
						
						photoAreaBox.add( new HTML("<iframe width='610' height='344' " +
								"src='https://www.youtube.com/embed/"+str+"' frameborder='0' " +
										"allowfullscreen></iframe>"));
						
//						photoAreaBox.add( new HTML("<video width='640' height='360' id='player1' controls='controls' preload='true'>"+
//							    //"<source type='video/youtube' src='http://www.youtube.com/watch?v="+str+"' />"+
//							    "<source type='video/youtube' src='https://www.youtube.com/watch?v=xhKmvsVMENk&feature=player_embedded' />"+
//							    "</video>"));

					}
				}
				else{
			
//				photoAreaBox.add(ServiceYoutube.getYoutubePanel(refYoutube));
				
					photoAreaBox.add( new HTML("<iframe width='610' height='344' " +
							"src='https://www.youtube.com/embed/"+refYoutube+"' frameborder='0' " +
									"allowfullscreen></iframe>"));
				
				
				}
			}
			
			if (refGDrive != null && refGDrive != "" && !refGDrive.isEmpty()) {
				
				if (refGDrive.contains(";")){
						String[] strarray = refGDrive.split(";");
					
					for(String str : strarray){
						
						if(!str.isEmpty())
						//photoAreaBox.add(ServiceYoutube.getYoutubePanel(str));
						
						
						photoAreaBox.add( new HTML("<iframe src='"+str+
							"' width='610' height='344'></iframe>"));
						
					}
				}
				else{

					photoAreaBox.add( new HTML("<iframe src='"+souvenir.getVideoGDrive()+
							"' width='610' height='344'></iframe>"));
					
				}
				
			}

			
			//load old comments
			 commentsPanel = new CommentsPanel(souvenir.getId());
			 photoAreaBox.add(commentsPanel);
			// commentsPanel.loadComments(souvenir.getId());
			 
			 CommentSubmitForm commentSubmitForm = new CommentSubmitForm(souvenirInfo, commentsPanel);
			 photoAreaBox.add(commentSubmitForm);
			 
			 
			 
//				//TODO: modify the DOM to include new meta for facebook:
			 //<html xmlns:og="http://ogp.me/ns#">
			// <head>
			 //<title>Article sur l'Open Graph</title>
//			 <!-- Open Graph data -->
//			 <meta property="og:title" content="Title Here" />
//			 <meta property="og:type" content="article" />
//			 <meta property="og:url" content="http://www.example.com/" />
//			 <meta property="og:image" content="http://example.com/image.jpg" />
//			 <meta property="og:description" content="Description Here" />
//			 <meta property="og:site_name" content="Site Name, i.e. Moz" />
//			 <meta property="og:price:amount" content="15.00" />
//			 <meta property="og:price:currency" content="USD" />

			 //set
			//Element root = Document.get().getElementById("html");
//			root.setAttribute("xmlns:og", "http://ogp.me/ns#");
			 
			 
//				 MetaElement eltMeta0 = Document.get().createMetaElement();
//				 eltMeta0.setAttribute("property", "og:title");
//				 eltMeta0.setAttribute("content", "Title Here");
//				 
//				 MetaElement eltMeta1 = Document.get().createMetaElement();
//				 eltMeta1.setAttribute("property", "og:type");
//				 eltMeta1.setAttribute("content", "Article");
//				 
//				 MetaElement eltMeta2 = Document.get().createMetaElement();
//				 eltMeta2.setAttribute("property", "og:url");
//				 eltMeta2.setAttribute("content", "http://www.example.com/");
//				 
//				 MetaElement eltMeta3 = Document.get().createMetaElement();
//				 eltMeta3.setAttribute("property", "og:image");
//				 eltMeta3.setAttribute("content", "http://www.mc-amen.net/amen/" +
//				 		"getSouveniruploadImg?blob-key="+souvenir.getThubnailUrl());
//				 
//				 MetaElement eltMeta4 = Document.get().createMetaElement();
//				 eltMeta4.setAttribute("property", "og:description");
//				 eltMeta4.setAttribute("content", "cool");
//
//				 
//				 NodeList<Element> node = Document.get().getElementsByTagName("head");
//				 Element eltHead = (Element) node.getItem(0);
//				 eltHead.appendChild(eltMeta0);
//				 eltHead.appendChild(eltMeta1);
//				 eltHead.appendChild(eltMeta2);
//				 eltHead.appendChild(eltMeta3);
//				 eltHead.appendChild(eltMeta4);
				 
				 
				 //check
//				 NodeList<Element> nodeMetas = Document.get().getElementsByTagName("meta");
//				 
//				 int max = nodeMetas.getLength();
//				 String alert="";
//				 
//				 for (int i=0; i<max; i++){
//				 
//				 Element eltCurrent = (Element) nodeMetas.getItem(i);
//				 
//				 String prop = eltCurrent.getPropertyString("property");
//				 String content = eltCurrent.getPropertyString("content");
//				 
//				 alert=alert+"//"+prop+","+content+":"+eltCurrent.toString() ;
//				 }
//				 
//				 Window.alert(""+eltHead);


				 
				 
			 

		}
		else {
			SouvenirManagerForm.this.setVisible(false);
			
				photoAreaBox.clear();
				
				categoryBox.setVisible(false);
				idNumberBox.setVisible(false);
				appellationBox.setVisible(false);
				descriptionBox.setVisible(false);
				dateSouvenirBox.setVisible(false);
				//this.indexInCellsBox.setVisible(false);
				this.iframePdf.setVisible(false);
				this.videoBox.setVisible(false);
				
				photoAreaBox.add(new HTML("<H2>Veuillez S'il vous plait selectionner dans la colonne de gauche <br>" +
						" les chants que vous souhaitez ecouter</H2>"));
				
				
		}

		
	}

	public void setDisplay(CellList<Souvenir> actuList) {
		this.souvenirList = actuList;

	}

	public void setPlace(SouvenirDetailsPlace place) {
		this.place = place;
		
	}

	public SouvenirDetailsPlace getPlace() {
		return place;
	}
	
	
	
	

}

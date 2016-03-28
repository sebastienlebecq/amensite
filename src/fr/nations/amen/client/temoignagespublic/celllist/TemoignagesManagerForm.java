package fr.nations.amen.client.temoignagespublic.celllist;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.client.rezosocio.ButtonFactory;
import fr.nations.amen.client.temoignagespublic.celllist.comments.CommentSubmitForm;
import fr.nations.amen.client.temoignagespublic.celllist.comments.CommentsPanel;
import fr.nations.amen.client.temoignagespublic.celllist.comments.CommentSubmitForm.CommentSubmitHandler;
import fr.nations.amen.shared.temoignages.Commentaire;
import fr.nations.amen.shared.temoignages.Temoignage;
import fr.nations.amen.shared.temoignages.TemoignagesService;
import fr.nations.amen.shared.temoignages.TemoignagesServiceAsync;

public class TemoignagesManagerForm extends Composite {

	// The maximum number of results to retrieve.
	final int MAX_RESULTS = 12;

	// private ArrayList<String> idArray = new ArrayList<String>();
	// TemoignagesServiceAsync souvenirService =
	// GWT.create(TemoignagesService.class);

	private static SouvenirManagerFormUiBinder uiBinder = GWT
			.create(SouvenirManagerFormUiBinder.class);

	interface SouvenirManagerFormUiBinder extends
			UiBinder<Widget, TemoignagesManagerForm> {
	}

	public TemoignagesManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));
		setTemoignage(null);
		photoAreaBox.clear();
	}

	// @UiField
	// FlowPanel photoArea;

	@UiField
	HTML idNumberBox;

	@UiField
	HTML appellationBox;

	@UiField
	HTML descriptionBox;

	@UiField
	HTML dateSouvenirBox;

	@UiField
	FlowPanel photoAreaBox;

	private Temoignage souvenirInfo;
	// private AllDataAsyncDataProvider dataProvider;
	private CellList<Temoignage> souvenirList;


	private CommentsPanel commentsPanel;

	public TemoignagesManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public void setTemoignage(Temoignage souvenir) {
		this.souvenirInfo = souvenir;

		if (souvenir != null) {
			
			this.setVisible(true);
			
			photoAreaBox.clear();
			 
			idNumberBox.setVisible(true);
			appellationBox.setVisible(true);
			descriptionBox.setVisible(true);
			dateSouvenirBox.setVisible(true);
			
			idNumberBox.setText(souvenir.getId());
			appellationBox.setText(souvenir.getAppellation());
			descriptionBox.setHTML(souvenir.getDescription());
			// this.imgSouvenirBox.set
			dateSouvenirBox.setHTML(souvenir.getDate().toGMTString());

			

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

			// Window.alert("refPicasa:"+ refPicasa);

			if ((refPicasa == null || refPicasa == "" || refPicasa.isEmpty())) {
				// Window.alert("aucune photo");
			} else {

				ServiceAmenPicasa.getPhotos(refPicasa, photoAreaBox);

			}

			// load old comments
			commentsPanel = new CommentsPanel(souvenir.getId());
			photoAreaBox.add(commentsPanel);
			commentsPanel.loadComments(souvenir.getId());

			CommentSubmitForm commentSubmitForm = new CommentSubmitForm(
					souvenirInfo, commentsPanel);
			photoAreaBox.add(commentSubmitForm);

			// commentSubmitForm.setCommentSubmitHandler(new
			// CommentSubmitHandler() {
			//
			//
			// public void submitComment(String comment) {
			//
			// Commentaire newInput = new
			// Commentaire(souvenirInfo.getId(),"internaute",
			// comment, new Date().getTime(), souvenirInfo.getAppellation());
			// // Window.alert("idSouvenir(GUI):"+souvenirInfo.getId());
			//
			// souvenirService.storeComment(souvenirInfo.getId(), newInput,
			// new AsyncCallback<Void>() {
			// public void onFailure(Throwable caught) {
			// // TODO: Show an error message
			// }
			//
			// public void onSuccess(Void result) {
			// commentsPanel.loadComments(souvenirInfo.getId());
			//
			// }
			// });
			// }
			// });
		} else {
			this.setVisible(false);
			idNumberBox.setVisible(false);
			appellationBox.setVisible(false);
			descriptionBox.setVisible(false);
			dateSouvenirBox.setVisible(false);
			
			
		}

	}

	public void setDisplay(CellList<Temoignage> actuList) {
		this.souvenirList = actuList;

	}
}

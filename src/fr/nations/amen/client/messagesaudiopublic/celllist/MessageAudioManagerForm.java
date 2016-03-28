package fr.nations.amen.client.messagesaudiopublic.celllist;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.messagesaudiopublic.celllist.comments.CommentSubmitForm;
import fr.nations.amen.client.messagesaudiopublic.celllist.comments.CommentsPanel;
import fr.nations.amen.client.picasa.ServiceAmenPicasa;
import fr.nations.amen.client.youtube.ServiceYoutube;
import fr.nations.amen.shared.messagesaudio.MessageAudio;

public class MessageAudioManagerForm extends Composite {

	// The maximum number of results to retrieve.
	final int MAX_RESULTS = 12;

	private static MessageAudioManagerFormUiBinder uiBinder = GWT
			.create(MessageAudioManagerFormUiBinder.class);

	interface MessageAudioManagerFormUiBinder extends
			UiBinder<Widget, MessageAudioManagerForm> {
	}

	public MessageAudioManagerForm() {
		initWidget(uiBinder.createAndBindUi(this));

		setSouvenir(null);
		photoAreaBox.clear();
	}

	@UiField
	AudioWidget messageAudio;
	
	@UiField
	HTML categoryBox;

//	@UiField
//	HTML blobKeyBox;

	@UiField
	HTML idNumberBox;

	@UiField
	HTML appellationBox;

	@UiField
	HTML descriptionBox;

	@UiField
	HTML dateSouvenirBox;

	@UiField
	VerticalPanel photoAreaBox;

	private static CommentsPanel commentsPanel;

	private MessageAudio souvenirInfo;
	private CellList<MessageAudio> souvenirList;
	private MessageAudio actuInfo2;

	public MessageAudioManagerForm(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private void initializeBox() {

		MessageAudio initActu = new MessageAudio("");
		initActu.setAppellation("");
		initActu.setDescription("");
		initActu.setDate(new Date());
		setSouvenir(new MessageAudio(""));

	}

	public void setSouvenir(final MessageAudio souvenir) {
		this.souvenirInfo = souvenir;

		if (souvenir != null) {
			idNumberBox.setText(souvenir.getId());
			if (souvenir.getCategory().startsWith("cat")){
				categoryBox.setHTML(UniformDim.categoriesMessagesAudio[Integer.valueOf(
						souvenir.getCategory().substring(3))]);
			}
			appellationBox.setText(souvenir.getAppellation());
			descriptionBox.setHTML(souvenir.getDescription());
			dateSouvenirBox.setHTML(souvenir.getDate().toGMTString());
		//	blobKeyBox.setText(souvenir.getBlobKey());

			photoAreaBox.clear();
			
			messageAudio.setSrcMp3("/amen/getMessageAudioUploaded?blob-key="
					+ souvenir.getBlobKey());
			messageAudio.setSrcogg("/amen/getMessageAudioUploaded?blob-key="
					+ souvenir.getBlobKeyogg());
			messageAudio.setSrcwav("/amen/getMessageAudioUploaded?blob-key="
					+ souvenir.getBlobKeywav());

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
			} else {
				ServiceAmenPicasa.getPhotos(refPicasa, photoAreaBox);

			}

			if ((refYoutube == null || refYoutube == "" || refYoutube.isEmpty())) {
				// Window.alert("aucune video");
			} else {

				// SimplePanel panel = new SimplePanel(); // create panel to
				// hold
				// // the player

				if (refYoutube.contains(";")) {

					String[] strarray = refYoutube.split(";");

					for (String str : strarray) {

						if (!str.isEmpty())
							photoAreaBox.add(ServiceYoutube
									.getYoutubePanel(str));
					}

				} else {

					photoAreaBox
							.add(ServiceYoutube.getYoutubePanel(refYoutube));
				}
			}

			// load old comments
			commentsPanel = new CommentsPanel(souvenir.getId());
			photoAreaBox.add(commentsPanel);
			// commentsPanel.loadComments(souvenir.getId());

			CommentSubmitForm commentSubmitForm = new CommentSubmitForm(
					souvenirInfo, commentsPanel);
			photoAreaBox.add(commentSubmitForm);
		}

	}

	public void setDisplay(CellList<MessageAudio> actuList) {
		this.souvenirList = actuList;
	}

}

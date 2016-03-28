package fr.nations.amen.client.acces;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.AccueilPrivateForm;
import fr.nations.amen.client.AuthentificationInfos;
import fr.nations.amen.client.acces.actu.celllist.ActualitesManager;
import fr.nations.amen.client.eglise.visionprivate.celllist.MenuEgliseManager;
import fr.nations.amen.client.forumeglise.celllist.ForumEgliseManager;
import fr.nations.amen.client.louanges.celllist.LouangesManager;
import fr.nations.amen.client.messagesaudio.celllist.MessagesAudioManager;
import fr.nations.amen.client.mvp.activities.AuthenticateActivity;
import fr.nations.amen.client.souvenirs.celllist.SouvenirsManager;
import fr.nations.amen.client.temoignages.celllist.TemoignagesManager;

public class ChoiceViewPanel extends Composite {
	
	
//	public static enum Choix {
//        ACTUMANAGER("Gestion des Actualités"), SOUVENIRMANAGER("Gestion des Souvenirs"),
//        FORUMPASTEURMANAGER("Gestion du Forum du Pasteur"), ANIMATIONMANAGER("Gestion des Animations");
//        private String value;
//
//        private Choix(String value) {
//                this.value = value;
//        }
//	}
	
//	@UiField
//	Button btnCellActu;

	@UiField
	Button btnCellTemoignages;
	
	@UiField	
	Button btnCellSouvenirs;
	
	@UiField
	Button btnMenuEglise;
	
	@UiField
	Button btnForumEglise;
	
//	@UiField
//	Button btnMessagesAudio;
	
	@UiField
	Button btnAccueil;
	
	// @UiField
	// Button btnVideoSearch;

	// @UiField
	// Button btnForumSearch;

	// @UiField
	// Button btnLouangeSearch;

	// @UiField
	// Button btnPhotoMembres;

	private AuthenticateActivity authPresenterImpl;

//	private ActualitesManager managerActu;

	private AuthentificationInfos authentication;

	private SouvenirsManager managerSouvenir;
	
	private TemoignagesManager managerTemoignages;

	private MessagesAudioManager managerMessageAudio;

	private LouangesManager managerLouanges;
	
	private MenuEgliseManager managerMenuEglise;

	private AccueilPrivateForm accueilForm;
	
	private ForumEgliseManager managerForumEglise;


	private static ChoiceViewPanelUiBinder uiBinder = GWT.create(ChoiceViewPanelUiBinder.class);

	interface ChoiceViewPanelUiBinder extends UiBinder<Widget, ChoiceViewPanel> {
	}
	
	public ChoiceViewPanel(AuthentificationInfos authentication) {
		this.authentication=authentication;
		initWidget(uiBinder.createAndBindUi(this));
	}

//	@UiHandler("btnCellActu")
//	void handleCellActuButtonClick(ClickEvent e) {
//		
//		if (managerActu ==null) managerActu = new ActualitesManager();
//		
//		authPresenterImpl.getAuthenticateView().setCell(managerActu, authentication);
//		//addActu();
//	}

	@UiHandler("btnCellSouvenirs")
	void handleCellSouvenirsButtonClick(ClickEvent e) {
			if (managerSouvenir ==null) managerSouvenir = new SouvenirsManager();
		
		authPresenterImpl.getAuthenticateView().setCell(managerSouvenir, authentication);
	}
	
	@UiHandler("btnForumEglise")
	void handleCellForumEgliseButtonClick(ClickEvent e) {
			if (managerForumEglise ==null) managerForumEglise = new ForumEgliseManager();
		
		authPresenterImpl.getAuthenticateView().setCell(managerForumEglise, authentication);
	}
	
	@UiHandler("btnCellTemoignages")
	void handleCellTemoignagesButtonClick(ClickEvent e) {
	if (managerTemoignages ==null) managerTemoignages = new TemoignagesManager();
		
		authPresenterImpl.getAuthenticateView().setCell(managerTemoignages, authentication);
	}
	
	@UiHandler("btnMenuEglise")
	void handleCellCellMenuEgliseButtonClick(ClickEvent e) {
if (managerMenuEglise ==null) managerMenuEglise = new MenuEgliseManager();
		
		authPresenterImpl.getAuthenticateView().setCell(managerMenuEglise, authentication);

	}	
//	
//	@UiHandler("btnMessagesAudio")
//	void handleCellCellAnimationButtonClick(ClickEvent e) {
//		if (managerMessageAudio ==null) managerMessageAudio = new MessagesAudioManager();
//		
//		authPresenterImpl.getAuthenticateView().setCell(managerMessageAudio, authentication);
//	}
	
	@UiHandler("btnLouanges")
	void handleCellVideoSearchButtonClick(ClickEvent e) {
if (managerLouanges ==null) managerLouanges = new LouangesManager();
		
		authPresenterImpl.getAuthenticateView().setCell(managerLouanges, authentication);
	}
	
	@UiHandler("btnAccueil")
	void handleAccueilButtonClick(ClickEvent e) {

if (accueilForm ==null) accueilForm = new AccueilPrivateForm();
		
		authPresenterImpl.getAuthenticateView().setCell(accueilForm, authentication);
	}
//	
//	@UiHandler("btnForumSearch")
//	void handleCellForumSearchButtonClick(ClickEvent e) {
//		Window.alert("A venir");
//	}

//	@UiHandler("btnLouangeSearch")
//	void handleCellLouangeSearchButtonClick(ClickEvent e) {
//		Window.alert("A venir");
//	}
	
//	@UiHandler("btnPhotoMembres")
//	void handlePhotoMembresButtonClick(ClickEvent e) {
//		Window.alert("A venir");
//	}
	
	public void setPresenter(AuthenticateActivity authenticateActivity) {
		this.authPresenterImpl = authenticateActivity;
		
	}	
	
//	public Widget getInitPanel() {
//		
//		
//		
//		
//		return null;
//	}
	
	

}

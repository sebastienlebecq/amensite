package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.AuthentificationInfos;
import fr.nations.amen.client.acces.actu.celllist.ActualitesManager;

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
	
	@UiField
	Button btnCellActu;
	
	@UiField	
	Button btnCellSouvenirs;
	
	@UiField
	Button btnCellForumPasteur;
	
//	@UiField
//	Button btnAnimation;

	private AuthenticatePresenterImpl authPresenterImpl;

	private ActualitesManager managerActu;

	private AuthentificationInfos authentication;

	private static ChoiceViewPanelUiBinder uiBinder = GWT.create(ChoiceViewPanelUiBinder.class);

	interface ChoiceViewPanelUiBinder extends UiBinder<Widget, ChoiceViewPanel> {
	}
	
	public ChoiceViewPanel(AuthentificationInfos authentication) {
		this.authentication=authentication;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("btnCellActu")
	void handleCellActuButtonClick(ClickEvent e) {
		
		if (managerActu ==null) managerActu = new ActualitesManager();
		
		authPresenterImpl.getAuthenticateView().setCell(managerActu, authentication);
		//addActu();
	}

	@UiHandler("btnCellSouvenirs")
	void handleCellSouvenirsButtonClick(ClickEvent e) {
		Window.alert("A venir");
	}
	
	@UiHandler("btnCellForumPasteur")
	void handleCellCellForumPasteurButtonClick(ClickEvent e) {
		Window.alert("A venir");
	}	
	
//	@UiHandler("btnAnimation")
//	void handleCellCellAnimationButtonClick(ClickEvent e) {
//		Window.alert("A venir");
//	}

	public void setPresenter(AuthenticatePresenterImpl authenticatePresenterImpl) {
		this.authPresenterImpl = authenticatePresenterImpl;
		
	}	
	
//	public Widget getInitPanel() {
//		
//		
//		
//		
//		return null;
//	}
	
	

}

package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.AccueilPrivateForm;
import fr.nations.amen.client.AuthentificationInfos;
import fr.nations.amen.client.acces.ChoiceViewPanel;
import fr.nations.amen.client.acces.actu.celllist.ActualitesManager;
import fr.nations.amen.client.eglise.visionprivate.celllist.MenuEgliseManager;
import fr.nations.amen.client.forumeglise.celllist.ForumEgliseManager;
import fr.nations.amen.client.louanges.celllist.LouangesManager;
import fr.nations.amen.client.messagesaudio.celllist.MessagesAudioManager;
import fr.nations.amen.client.mvp.presenters.AuthenticatePresenter;
import fr.nations.amen.client.souvenirs.celllist.SouvenirsManager;
import fr.nations.amen.client.temoignages.celllist.TemoignagesManager;

public interface AuthenticateView extends IsWidget{

	void setPresenter(AuthenticatePresenter authenticatePresenterImpl);

	void setCell(LouangesManager managerLouanges, AuthentificationInfos authentication);

	void setCell(AuthentificationInfos authentication);

	void setChoice(ChoiceViewPanel choiceView, AuthentificationInfos authentication);

	void setCell(ActualitesManager managerActu,
			AuthentificationInfos authentication);

	void setCell(TemoignagesManager managerTemoignages,
			AuthentificationInfos authentication);

	void setCell(MessagesAudioManager managerMessagesAudio,
			AuthentificationInfos authentication);

	void setCell(SouvenirsManager managerSouvenir,
			AuthentificationInfos authentication);

	void setCell(MenuEgliseManager managerMenuEglise,
			AuthentificationInfos authentication);

	void setCell(AccueilPrivateForm accueilForm, 
			AuthentificationInfos authentication);

	void setCell(ForumEgliseManager managerForumEglise,
			AuthentificationInfos authentication);

}

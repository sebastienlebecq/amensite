package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.messagesaudiopublic.celllist.CellListMessagesAudioManager;
import fr.nations.amen.client.mvp.presenters.MessagesAudioPresenter;


public interface MessagesAudioView extends IsWidget{
	void setPresenter(MessagesAudioPresenter messagesAudioPresenter);

	void setCell(CellListMessagesAudioManager scroll);
}

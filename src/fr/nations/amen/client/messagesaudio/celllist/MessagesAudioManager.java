package fr.nations.amen.client.messagesaudio.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MessagesAudioManager extends Composite 
{
	
	public MessagesAudioManager() {
			
			initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@UiField 
	CellListMessagesAudioManager cellListSouvenirManager;
	


	interface MessagesAudioManagerUiBinder extends UiBinder<Widget, MessagesAudioManager> {}
	
	private static MessagesAudioManagerUiBinder uiBinder = GWT.create(MessagesAudioManagerUiBinder.class);



}

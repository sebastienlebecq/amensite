package fr.nations.amen.client.messagesaudiopublic.celllist.celltypes.custom;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;

import fr.nations.amen.client.souvenirs.cellwidget.common.CalendarFactory;
import fr.nations.amen.shared.messagesaudio.MessageAudio;



public class MessageAudioCellWithUiBinder extends AbstractCell<MessageAudio> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String appellation, String description, String date);
		void onBrowserEvent(MessageAudioCellWithUiBinder cell, NativeEvent event, Element parent, MessageAudio value, 
				            Context context, ValueUpdater<MessageAudio> valueUpdaterer);
	}

	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);

	public MessageAudioCellWithUiBinder() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			MessageAudio value, SafeHtmlBuilder sb) {
		if (value != null)
			renderer.render(sb, value.getAppellation(), value.getDescription(),
					CalendarFactory.getDisplayFormatter().format(value.getDate()));
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, MessageAudio value,
	    NativeEvent event, ValueUpdater<MessageAudio> updater) {
	  renderer.onBrowserEvent(this, event, parent, value, context, updater);
	}
	
	@UiHandler("theCell")
	public void handleClick(ClickEvent event, Element parent, MessageAudio value, 
			                Context context, ValueUpdater<MessageAudio> valueUpdater){
    	//Window.alert("You clicked the cell with Index: " + context.getIndex());
	}
	
//	@UiHandler("privacy")
//	public void handlePrivacy(ClickEvent event, Element parent, Actu value, Context context, ValueUpdater<Actu> valueUpdater){
//		if (valueUpdater!=null)valueUpdater.update(value);
//	}
}

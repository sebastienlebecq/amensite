package fr.nations.amen.client.forumpasteur.celltypes.custom;

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
import com.google.gwt.user.client.Window;
import fr.nations.amen.client.forumpasteur.cellwidget.common.CalendarFactory;

import fr.nations.amen.shared.forumpasteur.Messages;


public class PhotoCellWithUiBinder extends AbstractCell<Messages> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String title, String img, String date);
		void onBrowserEvent(PhotoCellWithUiBinder cell, NativeEvent event, Element parent, Messages value, 
				            Context context, ValueUpdater<Messages> valueUpdaterer);
	}

	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);

	public PhotoCellWithUiBinder() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			Messages value, SafeHtmlBuilder sb) {
		if (value != null)
			renderer.render(sb, value.getTitle(), value.getLargeUrl(),
					CalendarFactory.getDisplayFormatter().format(value.getDate()));
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, Messages value,
	    NativeEvent event, ValueUpdater<Messages> updater) {
	  renderer.onBrowserEvent(this, event, parent, value, context, updater);
	}
	
	@UiHandler("theCell")
	public void handleClick(ClickEvent event, Element parent, Messages value, 
			                Context context, ValueUpdater<Messages> valueUpdater){
    	Window.alert("You clicked the cell with Index: " + context.getIndex());
	}
	
	@UiHandler("privacy")
	public void handlePrivacy(ClickEvent event, Element parent, Messages value, Context context, ValueUpdater<Messages> valueUpdater){
		if (valueUpdater!=null)valueUpdater.update(value);
	}
}

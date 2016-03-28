package fr.nations.amen.client.eglise.visionprivate.celllist.celltypes.custom;

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
import fr.nations.amen.shared.eglise.MenuEglise;


public class SouvenirCellWithUiBinder extends AbstractCell<MenuEglise> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String appellation, String description, String date);
		void onBrowserEvent(SouvenirCellWithUiBinder cell, NativeEvent event, Element parent, MenuEglise value, 
				            Context context, ValueUpdater<MenuEglise> valueUpdaterer);
	}

	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);

	public SouvenirCellWithUiBinder() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			MenuEglise value, SafeHtmlBuilder sb) {
		if (value != null)
			renderer.render(sb, value.getAppellation(), value.getDescription(),
					CalendarFactory.getDisplayFormatter().format(value.getDate()));
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, MenuEglise value,
	    NativeEvent event, ValueUpdater<MenuEglise> updater) {
	  renderer.onBrowserEvent(this, event, parent, value, context, updater);
	}
	
	@UiHandler("theCell")
	public void handleClick(ClickEvent event, Element parent, MenuEglise value, 
			                Context context, ValueUpdater<MenuEglise> valueUpdater){
    	//Window.alert("You clicked the cell with Index: " + context.getIndex());
	}
	
//	@UiHandler("privacy")
//	public void handlePrivacy(ClickEvent event, Element parent, Actu value, Context context, ValueUpdater<Actu> valueUpdater){
//		if (valueUpdater!=null)valueUpdater.update(value);
//	}
}

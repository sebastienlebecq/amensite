package fr.nations.amen.client.temoignagespublic.celllist.celltypes.custom;

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

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.temoignages.cellwidget.common.CalendarFactory;
import fr.nations.amen.shared.temoignages.Temoignage;


public class TemoignageCellWithUiBinder extends AbstractCell<Temoignage> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String appellation, String description, String date);
		void onBrowserEvent(TemoignageCellWithUiBinder cell, NativeEvent event, Element parent, Temoignage value, 
				            Context context, ValueUpdater<Temoignage> valueUpdaterer);
	}

	private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);
	private int indexSelected=UniformDim.OUTOFLIST;;

	public TemoignageCellWithUiBinder() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			Temoignage value, SafeHtmlBuilder sb) {
		if (value != null)
			renderer.render(sb, value.getAppellation(), value.getDescription(),
					CalendarFactory.getDisplayFormatter().format(value.getDate()));
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, Temoignage value,
	    NativeEvent event, ValueUpdater<Temoignage> updater) {
		indexSelected  = context.getIndex();
	  renderer.onBrowserEvent(this, event, parent, value, context, updater);
	}
	
	@UiHandler("theCell")
	public void handleClick(ClickEvent event, Element parent, Temoignage value, 
			                Context context, ValueUpdater<Temoignage> valueUpdater){
    	//Window.alert("You clicked the cell with Index: " + context.getIndex());
	}

	public void setIndexSelected(int outoflist) {
		indexSelected = outoflist;
		
	}
	
//	@UiHandler("privacy")
//	public void handlePrivacy(ClickEvent event, Element parent, Actu value, Context context, ValueUpdater<Actu> valueUpdater){
//		if (valueUpdater!=null)valueUpdater.update(value);
//	}
}

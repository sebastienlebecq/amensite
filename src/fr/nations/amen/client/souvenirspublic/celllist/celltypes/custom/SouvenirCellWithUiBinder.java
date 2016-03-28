package fr.nations.amen.client.souvenirspublic.celllist.celltypes.custom;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.souvenirs.cellwidget.common.CalendarFactory;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.souvenirs.Souvenir;


public class SouvenirCellWithUiBinder extends AbstractCell<Souvenir> {
	
	private int indexSelected=UniformDim.OUTOFLIST;

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String appellation, String description, String date, String img);
		void onBrowserEvent(SouvenirCellWithUiBinder cell, NativeEvent event, Element parent, Souvenir value, 
				            Context context, ValueUpdater<Souvenir> valueUpdaterer);
	}


	//private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);

	public SouvenirCellWithUiBinder() {
		super(BrowserEvents.CLICK);
	}
	
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			Souvenir value, SafeHtmlBuilder sb) {
		String srcImg = "";

		if (value != null) {

			if (value.getThubnailUrl() == null || value.getThubnailUrl().equals("")) {
				srcImg = "/images/sigleAmen.png";
			} else {
				srcImg = "/amen/getSouveniruploadImg?blob-key="
						+ value.getThubnailUrl();
			}

			int idex = context.getIndex();
			
			if (idex == indexSelected){
				//Window.alert("You clicked the cell with Index: " + idex);
				sb.appendHtmlConstant("<div id='containerCellBlue' align='center'><table>");

			} else {
			 sb.appendHtmlConstant("<div id='containerCell' align='center'><table>");
			}
		      sb.appendHtmlConstant("<tr><td>");
		      sb.appendHtmlConstant("<img src='"+srcImg+"' style='height: 100px;border-color:black;border=0;' />");
		      sb.appendHtmlConstant("</td></tr>");

		      sb.appendHtmlConstant("<tr><td align='left'>"+"<FONT size='3'><b>");
		      sb.appendEscaped(value.getAppellation());
		      sb.appendHtmlConstant("</b></FONT>"+"</td></tr>");
		      sb.appendHtmlConstant("</table></div>");
		}
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, Souvenir value,
	    NativeEvent event, ValueUpdater<Souvenir> updater) {
		
		//Window.alert("You clicked the cell with Index: " + context.getIndex());
		
		indexSelected  = context.getIndex();
		
	 // this.onBrowserEvent(this, event, parent, value, context, updater);
	}

	public void setIndexSelected(int outoflist) {
		
		indexSelected = outoflist;
	}

//	@Override
//	public void render(com.google.gwt.cell.client.Cell.Context context,
//			Souvenir value, SafeHtmlBuilder sb) {
//		if (value != null)
//			renderer.render(sb, value.getAppellation(), value.getDescription(),
//					CalendarFactory.getDisplayFormatter().format(value.getDate()));
//	}
//	
//	@Override
//	public void onBrowserEvent(Context context, Element parent, Souvenir value,
//	    NativeEvent event, ValueUpdater<Souvenir> updater) {
//	  renderer.onBrowserEvent(this, event, parent, value, context, updater);
//	}
	
//	@UiHandler("theCell")
//	public void handleClick(ClickEvent event, Element parent, Souvenir value, 
//			                Context context, ValueUpdater<Souvenir> valueUpdater){
//    	//Window.alert("You clicked the cell with Index: " + context.getIndex());
//	}
//	
//	@UiHandler("privacy")
//	public void handlePrivacy(ClickEvent event, Element parent, Actu value, Context context, ValueUpdater<Actu> valueUpdater){
//		if (valueUpdater!=null)valueUpdater.update(value);
//	}
}

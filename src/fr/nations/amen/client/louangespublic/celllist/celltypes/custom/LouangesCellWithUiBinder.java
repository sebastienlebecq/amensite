package fr.nations.amen.client.louangespublic.celllist.celltypes.custom;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowScrollListener;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.shared.louanges.Louange;



public class LouangesCellWithUiBinder extends AbstractCell<Louange> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String appellation, String description, String date, String numero, String img);
		void onBrowserEvent(LouangesCellWithUiBinder cell, NativeEvent event, Element parent, Louange value, 
				            Context context, ValueUpdater<Louange> valueUpdaterer);
	}

	private int indexSelected=UniformDim.OUTOFLIST;

	//private static MyUiRenderer renderer = GWT.create(MyUiRenderer.class);

	public LouangesCellWithUiBinder() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			Louange value, SafeHtmlBuilder sb) {
		String srcImg = "";
		
		if (value != null) {

			if (value.getThubnailUrl() == null || value.getThubnailUrl().equals("")) {
				
				srcImg = "/images/sigleAmen.png";
			} else {
				srcImg = "/amen/getlouangeuploadImg?blob-key="
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
		      sb.appendHtmlConstant("<img src='"+srcImg+"' style='height: 100px;border-color:black;border=10;' />");
		      sb.appendHtmlConstant("</td></tr>");

		      sb.appendHtmlConstant("<tr><td align='left'>"+"<FONT size='3'><b>");
		      sb.appendEscaped(value.getNumero());
		      sb.appendHtmlConstant(".");
		      sb.appendEscaped(value.getSujet());
		      sb.appendHtmlConstant("</b></FONT>"+"</td></tr>");
		      sb.appendHtmlConstant("</table></div>");
		}
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, Louange value,
	    NativeEvent event, ValueUpdater<Louange> updater) {
		
		indexSelected  = context.getIndex();
	}

	public int getIndexSelected() {
		return indexSelected;
	}

	public void setIndexSelected(int indexSelected) {
		this.indexSelected = indexSelected;
	}
	
//	@UiHandler("theCell")
//	public void handleClick(ClickEvent event, Element parent, Louange value, 
//			                Context context, ValueUpdater<Louange> valueUpdater){
//		
//		
//		
//    	Window.alert("You clicked the cell with Index: " + context.getIndex());
//	}
	
//	@UiHandler("privacy")
//	public void handlePrivacy(ClickEvent event, Element parent, Actu value, Context context, ValueUpdater<Actu> valueUpdater){
//		if (valueUpdater!=null)valueUpdater.update(value);
//	}
}

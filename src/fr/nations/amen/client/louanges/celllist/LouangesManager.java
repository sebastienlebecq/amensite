package fr.nations.amen.client.louanges.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class LouangesManager extends Composite 
{
	
	public LouangesManager() {
			
			initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@UiField 
	CellListLouangesManager cellListSouvenirManager;
	


	interface LouangesManagerUiBinder extends UiBinder<Widget, LouangesManager> {}
	
	private static LouangesManagerUiBinder uiBinder = GWT.create(LouangesManagerUiBinder.class);



}

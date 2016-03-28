package fr.nations.amen.client.temoignages.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TemoignagesManager extends Composite 
{
	
	public TemoignagesManager() {
			
			initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@UiField 
	CellListTemoignagesManager cellListTemoignagesManager;
	


	interface TemoignagesManagerUiBinder extends UiBinder<Widget, TemoignagesManager> {}
	
	private static TemoignagesManagerUiBinder uiBinder = GWT.create(TemoignagesManagerUiBinder.class);



}

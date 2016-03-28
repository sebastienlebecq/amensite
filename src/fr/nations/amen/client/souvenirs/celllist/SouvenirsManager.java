package fr.nations.amen.client.souvenirs.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SouvenirsManager extends Composite 
{
	
	public SouvenirsManager() {
			
			initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@UiField 
	CellListSouvenirManager cellListSouvenirManager;
	


	interface SouvenirsManagerUiBinder extends UiBinder<Widget, SouvenirsManager> {}
	
	private static SouvenirsManagerUiBinder uiBinder = GWT.create(SouvenirsManagerUiBinder.class);



}

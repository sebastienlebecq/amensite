package fr.nations.amen.client.forumeglise.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ForumEgliseManager extends Composite 
{
	
	public ForumEgliseManager() {
			
			initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@UiField 
	CellListForumEgliseManager cellListSouvenirManager;
	


	interface SouvenirsManagerUiBinder extends UiBinder<Widget, ForumEgliseManager> {}
	
	private static SouvenirsManagerUiBinder uiBinder = GWT.create(SouvenirsManagerUiBinder.class);



}

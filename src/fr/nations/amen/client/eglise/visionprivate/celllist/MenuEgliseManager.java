package fr.nations.amen.client.eglise.visionprivate.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MenuEgliseManager extends Composite 
{
	
	public MenuEgliseManager() {
			
			initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@UiField 
	CellListMenuEgliseManager cellListSouvenirManager;
	


	interface MenuEgliseManagerUiBinder extends UiBinder<Widget, MenuEgliseManager> {}
	
	private static MenuEgliseManagerUiBinder uiBinder = GWT.create(MenuEgliseManagerUiBinder.class);



}

package fr.nations.amen.client.acces.actu.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.nations.amen.client.acces.LoginInfo;
import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;
import fr.nations.amen.client.mvp.views.SouvenirsView;

public class ActualitesManager extends Composite 
{
	
	public ActualitesManager() {
			
			initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@UiField 
	CellListActuManager cellListActuManager;
	


	interface ActualitesManagerUiBinder extends UiBinder<Widget, ActualitesManager> {}
	
	private static ActualitesManagerUiBinder uiBinder = GWT.create(ActualitesManagerUiBinder.class);



}

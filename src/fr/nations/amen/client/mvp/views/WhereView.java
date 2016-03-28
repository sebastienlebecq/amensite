package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.mvp.presenters.WherePresenter;
import fr.nations.amen.client.ounoustrouver.TransitDirectionsServiceMapWidget;

public interface WhereView  extends IsWidget{

	void setPresenter(WherePresenter presenter);
	
	void setCell(Composite  myTabLayoutPanel);
}

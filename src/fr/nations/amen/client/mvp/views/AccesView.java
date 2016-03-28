package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.mvp.presenters.AccesPresenter;

public interface AccesView extends IsWidget{

	void setCell(ScrollPanel  scroll);

	void setPresenter(AccesPresenter accesPresenter);
	
}

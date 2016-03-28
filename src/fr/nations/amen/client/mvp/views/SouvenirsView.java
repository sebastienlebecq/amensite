package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.souvenirspublic.celllist.CellListSouvenirManager;
import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;

public interface SouvenirsView extends IsWidget{

	void setCell(CellListSouvenirManager  scroll);

	void setPresenter(SouvenirsPresenter accesPresenter);

	SouvenirsPresenter getPresenter();

	//void waiting();

	
}

package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.mvp.presenters.SouvenirsPresenter;
import fr.nations.amen.client.mvp.presenters.TemoignagesPresenter;
import fr.nations.amen.client.temoignagespublic.celllist.CellListTemoignagesManager;

public interface TemoignagesView extends IsWidget{

	void setPresenter(TemoignagesPresenter temoignagesPresenterImpl);

	void setCell(CellListTemoignagesManager scroll);

	TemoignagesPresenter getPresenter();

}

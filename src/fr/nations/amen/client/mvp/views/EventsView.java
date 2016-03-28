package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.actupublic.celllist.CellListConsult;
import fr.nations.amen.client.mvp.presenters.EventsPresenter;

public interface EventsView  extends IsWidget{

	void setPresenter(EventsPresenter presenter);

	void setCell(CellListConsult cellListExample);

}

package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.forumeglisepublic.celllist.CellListForumEgliseManager;
import fr.nations.amen.client.mvp.presenters.ForumEglisePresenter;

public interface ForumEgliseView extends IsWidget{

	void setCell(CellListForumEgliseManager  scroll);

	void setPresenter(ForumEglisePresenter accesPresenter);

	ForumEglisePresenter getPresenter();

	//void waiting();

	
}

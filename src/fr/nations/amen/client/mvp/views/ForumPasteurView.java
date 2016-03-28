package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.forumpasteur.celltree.CellTreeExample;
import fr.nations.amen.client.mvp.presenters.ForumPasteurPresenter;

public interface ForumPasteurView extends IsWidget {
	  void setPresenter(ForumPasteurPresenter presenter);

	void setCell(CellTreeExample cellTreeExample);
}

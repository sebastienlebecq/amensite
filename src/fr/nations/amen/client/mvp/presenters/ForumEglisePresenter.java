package fr.nations.amen.client.mvp.presenters;

import com.google.gwt.view.client.Range;

import fr.nations.amen.client.forumeglisepublic.celllist.CellListForumEgliseManager;
import fr.nations.amen.shared.forumeglise.ForumEglise;

public interface ForumEglisePresenter extends Presenter {

	void forumSelected(ForumEglise current,
			CellListForumEgliseManager cellListSouvenirManager, Range range, String category);
}

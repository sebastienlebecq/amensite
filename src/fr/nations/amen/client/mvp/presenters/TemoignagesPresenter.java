package fr.nations.amen.client.mvp.presenters;

import com.google.gwt.view.client.Range;

import fr.nations.amen.client.temoignagespublic.celllist.CellListTemoignagesManager;
import fr.nations.amen.shared.temoignages.Temoignage;

public interface TemoignagesPresenter extends Presenter {

	void temoignageSelected(Temoignage current,
			CellListTemoignagesManager cellListTemoignageManager, Range range,
			String category);

}

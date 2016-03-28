package fr.nations.amen.client.mvp.presenters;

import com.google.gwt.view.client.Range;

import fr.nations.amen.client.souvenirspublic.celllist.CellListSouvenirManager;
import fr.nations.amen.shared.souvenirs.Souvenir;


public interface SouvenirsPresenter extends Presenter {

	void souvenirSelected(Souvenir current,
			CellListSouvenirManager cellListSouvenirManager, Range range, String category);

}

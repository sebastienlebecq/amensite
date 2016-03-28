package fr.nations.amen.client.mvp.presenters;

import com.google.gwt.view.client.Range;

import fr.nations.amen.client.louangespublic.celllist.CellListLouangesManager;
import fr.nations.amen.shared.louanges.Louange;

public interface LouangesPresenter extends Presenter {
	
	void louangeSelected(Louange current,
			CellListLouangesManager cellListSouvenirManager, Range range, String category);


}

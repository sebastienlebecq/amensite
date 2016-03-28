package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.louangespublic.celllist.CellListLouangesManager;
import fr.nations.amen.client.mvp.presenters.LouangesPresenter;

public interface LouangesView extends IsWidget{

	void setPresenter(LouangesPresenter presenter);

	void setCell(CellListLouangesManager cellListVideo);

	LouangesPresenter getPresenter();
	}

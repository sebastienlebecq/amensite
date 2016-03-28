package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import fr.nations.amen.client.mvp.presenters.HistoriquePresenter;


public interface HistoriqueView extends IsWidget{

	//void setCell(MyTabLayoutPanel myTabLayoutPanel);
	void setCell(ScrollPanel  scroll);

	void setPresenter(HistoriquePresenter historiquePresenter);

}

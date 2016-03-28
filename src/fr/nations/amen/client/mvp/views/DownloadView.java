package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;

import fr.nations.amen.client.mvp.presenters.DownloadPresenter;

public interface DownloadView  extends IsWidget{

	void setPresenter(DownloadPresenter presenter);
	
	void setCell(Composite  myDownloadPanel);


}

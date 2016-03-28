package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.mvp.presenters.WelcomePresenter;

public interface WelcomeView extends IsWidget{
	void setPresenter(WelcomePresenter presenter);

	//void setCell(ScrollPanel scroll);
}

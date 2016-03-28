package fr.nations.amen.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.mvp.activities.EgliseActivity;
import fr.nations.amen.client.mvp.presenters.EglisePresenter;
import fr.nations.amen.client.mvp.presenters.VisionPresenter;


public interface EgliseView  extends IsWidget{

	void setPresenter(EglisePresenter presenter);

	void setCell(ScrollPanel scroll);
	
}

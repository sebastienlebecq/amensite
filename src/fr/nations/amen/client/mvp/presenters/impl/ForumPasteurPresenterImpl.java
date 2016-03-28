package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import fr.nations.amen.client.forumpasteur.celltree.CellTreeExample;

import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.presenters.ForumPasteurPresenter;
import fr.nations.amen.client.mvp.views.ForumPasteurView;

public class ForumPasteurPresenterImpl implements ForumPasteurPresenter {
	private final ForumPasteurView forumPasteurView;
	
	CellTreeExample cellTreeExample;

	public ForumPasteurPresenterImpl(ForumPasteurView forumPasteurView) {
		this.forumPasteurView = forumPasteurView;

		this.onshowForumPasteur();
		bind();
	}

	private void onshowForumPasteur() {
	
//		GWT.runAsync(new RunAsyncCallback(){
//			@Override
//			public void onFailure(Throwable reason) {
//			}
//
//			@Override
//			public void onSuccess() {
		    	if (cellTreeExample==null) cellTreeExample = new CellTreeExample();
		    	
		    	forumPasteurView.setCell(cellTreeExample);
		    	History.newItem(Tokens.FORUMPASTEUR);
		        //setWidgetAsExample(cellTreeExample);				
//			}
//    	});
		
	}

	public void bind() {
		forumPasteurView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(forumPasteurView.asWidget());
	}

//	public void onshowForumPasteurButtonClicked() {
//		History.newItem(Tokens.FORUMPASTEUR);
//	}
	
	
	
}

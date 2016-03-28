package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.historique.TabPanelExample2;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.presenters.HistoriquePresenter;
import fr.nations.amen.client.mvp.views.HistoriqueView;

public class HistoriquePresenterImpl implements HistoriquePresenter {
	private final HistoriqueView HistoriqueView;
	
	//CellListExamples cellListExample;
	private TabPanelExample2 myTabLayoutPanel;

	public HistoriquePresenterImpl(HistoriqueView eventsView) {
		this.HistoriqueView = eventsView;

		this.onshowHistorique();
		bind();
	}

	private void onshowHistorique() {
	
//		GWT.runAsync(new RunAsyncCallback(){
//			
//
//			@Override
//			public void onFailure(Throwable reason) {
//			}
//
//			@Override
//			public void onSuccess() {
//				//if (cellListExample==null) cellListExample = new CellListExamples();
//				
				if(myTabLayoutPanel==null)  myTabLayoutPanel = new TabPanelExample2();
		    	
				//myTabLayoutPanel.
				ScrollPanel scroll = new ScrollPanel(myTabLayoutPanel);
				
		    	HistoriqueView.setCell(scroll);
		    	//HistoriqueView.setCell(cellListExample);
		    	History.newItem(Tokens.HISTORIQUE);
		        //setWidgetAsExample(cellTreeExample);				
//			}
//    	});
		
	}

	public void bind() {
		HistoriqueView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(HistoriqueView.asWidget());
	}


}

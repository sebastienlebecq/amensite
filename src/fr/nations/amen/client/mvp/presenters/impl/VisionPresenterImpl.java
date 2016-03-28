package fr.nations.amen.client.mvp.presenters.impl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ScrollPanel;

import fr.nations.amen.client.eglise.visionpublic.MenuEgliseVision;
import fr.nations.amen.client.mvp.Tokens;
import fr.nations.amen.client.mvp.presenters.VisionPresenter;
import fr.nations.amen.client.mvp.views.EgliseView;

public class VisionPresenterImpl implements VisionPresenter {
	private final EgliseView visionView;
	
	//CellListExamples cellListExample;
	private MenuEgliseVision myTabLayoutPanel;

	private String longId;

	private String item;

	public VisionPresenterImpl(EgliseView visionView, String longId, String item) {
		this.visionView = visionView;
		this.longId = longId;
		this.item = item;
		this.onshowVision();
		bind();
	}

	private void onshowVision() {
		if(myTabLayoutPanel==null)  myTabLayoutPanel = new MenuEgliseVision(null);
		//if(myTabLayoutPanel==null)  myTabLayoutPanel = new MenuEgliseVision(this.longId);
    	
		//myTabLayoutPanel.
		ScrollPanel scroll = new ScrollPanel(myTabLayoutPanel);
		
    	visionView.setCell(scroll);
    	//HistoriqueView.setCell(cellListExample);
    	History.newItem(item);
		
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(visionView.asWidget());

	}

	@Override
	public void bind() {
	//	visionView.setPresenter(this);

	}

}

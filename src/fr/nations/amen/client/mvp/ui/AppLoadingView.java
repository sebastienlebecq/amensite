package fr.nations.amen.client.mvp.ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
 
public final class AppLoadingView extends PopupPanel 
{
    private final FlowPanel container = new FlowPanel();

    private static AppLoadingView mInstance = null;
    
    public static AppLoadingView getInstance()
    {
      if(mInstance == null)
      {
        mInstance = new AppLoadingView();
      }
      return mInstance;
    }
    
    private AppLoadingView()
    {        
        final Image ajaxImage = new Image("/images/wait1.gif");
        final Grid grid = new Grid(1, 2);  
        grid.setWidget(0, 0, ajaxImage);
        grid.setText(0, 1, "Loading...");    
        this.container.add(grid);
        add(this.container);       
    }

    @Override
    public Widget asWidget()
    {
        return this;
    }

    
    public void stop()
    {
        hide();
    }

    
    public void start()
    {
        center();
        show();
    }

    
    public void showWidget()
    {
        start();
    }
}
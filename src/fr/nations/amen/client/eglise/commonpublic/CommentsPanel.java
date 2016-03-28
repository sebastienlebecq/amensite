package fr.nations.amen.client.eglise.commonpublic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import fr.nations.amen.shared.eglise.Commentaire;
import fr.nations.amen.shared.eglise.MenuEgliseService;
import fr.nations.amen.shared.eglise.MenuEgliseServiceAsync;

/**
 * Shows comments in a vertical order.
 */
public class CommentsPanel extends Composite {
	
	MenuEgliseServiceAsync souvenirService = GWT
			.create(MenuEgliseService.class);

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yy hh:mm:ss :");

	private VerticalPanel mainPanel = new VerticalPanel();

	private MyDataProvider dataProvider;

	private static CellList<Commentaire> cellList;

	private static String idSouvenir;
	
	static class CommentsCell extends AbstractCell<Commentaire> {

		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yy hh:mm:ss :");

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context,
				Commentaire value, SafeHtmlBuilder sb) {
			if (value != null) {
				String dateShow = fmt.format(new Date(value.getHireDate()));

				sb.appendHtmlConstant("<table  width='450'><tr><td WIDTH=120>");
				sb.appendEscapedLines(dateShow);
				sb.appendHtmlConstant("</td><td style='padding: 0px; background: " +
						"#E0EFFF;border: 1px solid #FFF;'>- ");
				sb.appendEscapedLines(value.getComment());
				sb.appendHtmlConstant("</td></tr></table>");

			}
		}
	}

	public CommentsPanel(String idSouvenir) {
		CommentsPanel.idSouvenir = idSouvenir;
		mainPanel.setWidth("100%");

		// Create a CellList.
		cellList = new CellList<Commentaire>(new CommentsCell());
		cellList.setPageSize(5);

		// Create a data provider.
		dataProvider = new MyDataProvider();

		// Add the cellList to the dataProvider.
		dataProvider.addDataDisplay(cellList);

		// Create paging controls.
		SimplePager pager = new SimplePager();
		pager.setPageSize(3);
		pager.setRangeLimited(false);
		pager.setDisplay(cellList);

		VerticalPanel vp = new VerticalPanel();
		vp.add(cellList);
		vp.add(pager);
		vp.setBorderWidth(2);
		mainPanel.add(vp);

		initWidget(mainPanel);
	}

	public static class MyDataProvider extends AsyncDataProvider<Commentaire> {

		MenuEgliseServiceAsync souvenirService = GWT
				.create(MenuEgliseService.class);
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yy hh:mm:ss :");

		/**
		 * {@link #onRangeChanged(HasData)} is called when the table requests a
		 * new range of data. You can push data back to the displays using
		 * {@link #updateRowData(int, List)}.
		 */
		@Override
		protected void onRangeChanged(HasData<Commentaire> display) {
			// Get the new range.
			final Range range = display.getVisibleRange();

			final int start = range.getStart();
			int length = range.getLength();
			final List<Commentaire> newData = new ArrayList<Commentaire>();

			souvenirService.getComments(idSouvenir, start, length,
					new AsyncCallback<List<Commentaire>>() {
						public void onSuccess(List<Commentaire> result) {

							for (Commentaire com : result) {
								newData.add(com);
							}

							// Push the data to the displays. AsyncDataProvider
							// will only update
							// displays that are within range of the data.
							updateRowData(start, newData);
							cellList.redraw();
						}

						public void onFailure(Throwable caught) {
							// TODO: Show an error message.
						}
					});
		}

	}
	/**
	 * Shows the given comments.
	 */
	public void setComments(List<Commentaire> result) {
		dataProvider.updateRowData(0, result);
		cellList.redraw();
	}
	
	 public void loadComments(String id) {
		 // souvenir.getAppellation() = Appellation;
		  souvenirService.getComments(id, new AsyncCallback<List<Commentaire>>() {
		      public void onSuccess(List<Commentaire> result) {
		        setComments(result);
		      }

		      public void onFailure(Throwable caught) {
		        // TODO: Show an error message.
		      }
		    });
		  }
}

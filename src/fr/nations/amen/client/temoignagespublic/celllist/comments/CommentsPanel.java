/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package fr.nations.amen.client.temoignagespublic.celllist.comments;

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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.shared.temoignages.TemoignagesService;
import fr.nations.amen.shared.temoignages.TemoignagesServiceAsync;
import fr.nations.amen.shared.temoignages.Commentaire;

/**
 * Shows comments in a vertical order.
 */
public class CommentsPanel extends Composite {

	TemoignagesServiceAsync souvenirService = GWT
			.create(TemoignagesService.class);

	static class CommentsCell extends AbstractCell<Commentaire> {
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yy hh:mm:ss");

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context,
				Commentaire value, SafeHtmlBuilder sb) {
			if (value != null) {

				String strDivId = "";
				if (value.getSignataire() != null) {// if value auteur

					if (value.getSignataire().toUpperCase()
							.equals(UniformDim.Auteur.INTERNAUTE.toString())) {// ifauteur
						strDivId = "<div id='containerBlue' align='left'>- ";
					} else if (value.getSignataire().toUpperCase()
							.equals(UniformDim.Auteur.EGLISE.toString())) {
						strDivId = "<div id='containerGreen' align='right'>- ";
					} else {

						strDivId = "<div id='containerBlue' align='left'>- ";
					}// end ifauteur

				} else {
					strDivId = "<div id='containerBlue' align='left'>- ";
				}// end if value auteur

				String dateShow = fmt.format(new Date(value.getHireDate()));

				sb.appendHtmlConstant("<table  width='600'><tr>");
				// sb.appendEscapedLines(dateShow);
				sb.appendHtmlConstant("<td width='120'>"
						+ value.getSignataire().toUpperCase()
						+ ":</td><td style='padding: 0px;"
						+ "border: 0px solid #FFF;'>" + strDivId);
				sb.appendEscapedLines(value.getComment());
				sb.appendHtmlConstant("</div></td><td width='180'>" + dateShow
						+ "</td></tr></table>");

				// <div id="container" align="center">

			}
		}
//		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yy hh:mm:ss :");
//
//		@Override
//		public void render(com.google.gwt.cell.client.Cell.Context context,
//				Commentaire value, SafeHtmlBuilder sb) {
//			if (value != null) {
//				String dateShow = fmt.format(new Date(value.getHireDate()));
//
//				sb.appendHtmlConstant("<table  width='636'><tr><td WIDTH=120>");
//				sb.appendEscapedLines(dateShow);
//				sb.appendHtmlConstant("</td><td style='padding: 0px; background: " +
//						"#E0EFFF;border: 1px solid #FFF;'>- ");
//				sb.appendEscapedLines(value.getComment());
//				sb.appendHtmlConstant("</td></tr></table>");
//
//			}
//		}
	}

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yy hh:mm:ss :");

	private VerticalPanel mainPanel = new VerticalPanel();

	private MyDataProvider dataProvider;

	private CellList<Commentaire> cellList;

	private static String idSouvenir;

	public CommentsPanel(String idSouvenir) {
		CommentsPanel.idSouvenir = idSouvenir;
		mainPanel.setWidth("100%");
		mainPanel.setStyleName("ctr");

		// Create a CellList.
		cellList = new CellList<Commentaire>(new CommentsCell());
		cellList.setPageSize(UniformDim.SIZE_DIALOG);
		// Create a data provider.
		dataProvider = new MyDataProvider();

		// Add the cellList to the dataProvider.
		dataProvider.addDataDisplay(cellList);

		// Create paging controls.
		SimplePager pager = new SimplePager();
		pager.setPageSize(UniformDim.SIZE_DIALOG);
		pager.setRangeLimited(true);
		pager.setDisplay(cellList);
		pager.setStyleName("ctr");
		
		VerticalPanel vp = new VerticalPanel();

		vp.add(cellList);
		vp.add(pager);
		//vp.setBorderWidth(2);

		mainPanel.add(vp);
		// mainPanel.add(cellList);
		// mainPanel.add(pager);

		initWidget(mainPanel);
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

	public static class MyDataProvider extends AsyncDataProvider<Commentaire> {

		TemoignagesServiceAsync temoignagesService = GWT
				.create(TemoignagesService.class);
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

			temoignagesService.getComments(idSouvenir, start, length,
					new AsyncCallback<List<Commentaire>>() {
						public void onSuccess(List<Commentaire> result) {

							for (Commentaire com : result) {

								String dateShow = fmt.format(new Date(com
										.getHireDate()));

								// newData.add(dateShow + " - " +
								// com.getComment());
								newData.add(com);
							}

							// Push the data to the displays. AsyncDataProvider
							// will only update
							// displays that are within range of the data.
							updateRowData(start, newData);
							// cellList.redraw();
						}

						public void onFailure(Throwable caught) {
							// TODO: Show an error message.
						}
					});
		}

	}
}

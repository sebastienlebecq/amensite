package fr.nations.amen.client.compolesplus;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.mvp.ClientFactory;
import fr.nations.amen.client.mvp.activities.LouangeDetailsActivity;
import fr.nations.amen.client.mvp.places.LouangeDetailsPlace;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;
import fr.nations.amen.shared.souvenirs.Souvenir;

public class ListeLouangespEcoutes extends Composite {

	LouangesServiceAsync louangeService = GWT.create(LouangesService.class);

	private ClientFactory clientFactory = GWT.create(ClientFactory.class);

	public ListeLouangespEcoutes() {
		super();
		// Create a CellTable.
		final CellTable<Louange> table = new CellTable<Louange>();
		// Display 3 rows in one page
		table.setPageSize(UniformDim.SIZE_ARRAY_FIRST_PAGE);

		// Add a text column to show the name.
		TextColumn<Louange> nameColumn = new TextColumn<Louange>() {
			@Override
			public String getValue(Louange object) {
				String indexStr = object.getCategory().substring(3);

				return UniformDim.categoriesLouanges[Integer.valueOf(indexStr)];
			}
		};
		table.addColumn(nameColumn, "Categorie");

		// Add a date column to show the birthday.
		TextColumn<Louange> catColumn = new TextColumn<Louange>() {
			@Override
			public String getValue(Louange object) {
				return object.getSujet();
			}
		};
		table.addColumn(catColumn, "Titre");

		// Add a text column to show the address.
		TextColumn<Louange> addressColumn = new TextColumn<Louange>() {
			@Override
			public String getValue(Louange object) {
				if (object.getNbPagesVues() != null) {
					return object.getNbPagesVues().toString();
				} else {
					return "null";
				}
			}
		};
		table.addColumn(addressColumn, "Nb de Visites");

		//
		Column<Louange, String> detailsColumn = new Column<Louange, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Louange object) {
				return "Go!!";
			}
		};
		table.addColumn(detailsColumn, "Voir");

		detailsColumn.setFieldUpdater(new FieldUpdater<Louange, String>() {
			@Override
			public void update(int index, Louange object, String value) {

				// Window.alert("index:"+index+", value:"+value+", objet categorie:"+object.getCategory());
				LouangeDetailsActivity.initLouange();

				if (object.getIndexInCells() == null) {

					Window.alert("la mise à jour des index n'a pas été effectuée : sélectionnez chaque article!!");
				} else {
					goTo(new LouangeDetailsPlace(object.getId() + "&"
							+ object.getIndexInCells() + "&"
							+ object.getCategory()));
				}

			}
		});

		final AsyncDataProvider<Louange> provider = new AsyncDataProvider<Louange>() {
			@Override
			protected void onRangeChanged(HasData<Louange> display) {
				final int start = display.getVisibleRange().getStart();
				int length = display.getVisibleRange().getLength();
				AsyncCallback<List<Louange>> callback = new AsyncCallback<List<Louange>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(List<Louange> result) {
						updateRowData(start, result);
					}
				};
				// The remote service that should be implemented
				louangeService.fetchPage(start, start
						+ UniformDim.SIZE_ARRAY_FIRST_PAGE, callback);
			}
		};
		provider.addDataDisplay(table);
		// provider.updateRowCount(table.getVisibleRange().getLength(), true);

		// requête asynchrone pour déterminer le nombre de louanges

		louangeService.getCountMax(new AsyncCallback<Integer>() {

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			public void onSuccess(Integer countMax) {
				
				provider.updateRowCount(countMax, true);

			}
		});

		// provider.updateRowCount(30, true);

		SimplePager pager = new SimplePager();
		pager.setDisplay(table);

		TitleListLouanges tlaudio = new TitleListLouanges();

		VerticalPanel vp = new VerticalPanel();
		vp.add(tlaudio);
		vp.add(table);
		vp.add(pager);

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(vp);
		decoratorPanel.setStyleName("tableLesPlus");

		initWidget(decoratorPanel);

		// // Associate an async data provider to the table
		// AsyncDataProvider<Contact> provider = new
		// AsyncDataProvider<Contact>() {
		// @Override
		// protected void onRangeChanged(HasData<Contact> display) {
		// final int start = display.getVisibleRange().getStart();
		// int length = display.getVisibleRange().getLength();
		// AsyncCallback<List<Contact>> callback = new
		// AsyncCallback<List<Contact>>() {
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert(caught.getMessage());
		// }
		// @Override
		// public void onSuccess(List<Contact> result) {
		// updateRowData(start, result);
		// }
		// };
		// // The remote service that should be implemented
		// remoteService.fetchPage(start, length, callback);
		// }
		// };
	}

	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}

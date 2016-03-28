package fr.nations.amen.client.messagesaudiopublic.celllist;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.PageSizePager;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fr.nations.amen.client.UniformDim;
import fr.nations.amen.client.messagesaudiopublic.celllist.celltypes.custom.MessageAudioCellWithUiBinder;
import fr.nations.amen.client.messagesaudiopublic.celllist.dataproviders.AllDataAsyncDataProvider;
import fr.nations.amen.shared.louanges.Louange;
import fr.nations.amen.shared.louanges.LouangesService;
import fr.nations.amen.shared.louanges.LouangesServiceAsync;
import fr.nations.amen.shared.messagesaudio.MessageAudio;
import fr.nations.amen.shared.messagesaudio.MessagesAudioService;
import fr.nations.amen.shared.messagesaudio.MessagesAudioServiceAsync;

/**
 * GWT in Action 2nd Edition . example of a CellList widget.
 * 
 * Shows how to use a CellList widget using custom built PhotoDetails cells, as
 * well as application of 2 basic pagers.
 * 
 * You can alter page size by varying the PAGE_SIZE variable
 * 
 * 
 */
public class CellListMessagesAudioManager extends Composite {

	interface CellTypesUiBinder extends
			UiBinder<Widget, CellListMessagesAudioManager> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);

	AsyncDataProvider<MessageAudio> dataProviderAsync;

	//final int PAGE_SIZE = 6;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;
	@UiField
	ListBox categories;

	// Display Cells
	@UiField(provided = true)
	CellList<MessageAudio> souvenirCells;
	@UiField
	MessageAudioManagerForm souvenirManagerForm;
	
	MessagesAudioServiceAsync souvenirService = GWT.create(MessagesAudioService.class);

	public CellListMessagesAudioManager() {

		souvenirCells = new CellList<MessageAudio>(
				new MessageAudioCellWithUiBinder());
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(souvenirCells);
		handleUpdates();
		
		for (String cat : UniformDim.categoriesMessagesAudio){
			categories.addItem(cat);
		}
		
		createWithAsyncDataProvider();

		souvenirCells
				.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		souvenirCells.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
		
		categories.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				dataProviderAsync.removeDataDisplay(souvenirCells);
				souvenirCells.redraw();

				dataProviderAsync = new CatDataAsyncDataProvider("cat"+categories.getSelectedIndex());
				dataProviderAsync.addDataDisplay(souvenirCells);
				dataProviderAsync.updateRowCount(10, true);

				souvenirManagerForm.setDisplay(souvenirCells);
				souvenirCells.redraw();
			}

		});
	}

	private void applyOptions() {
		souvenirCells.setPageSize(UniformDim.PAGE_PUBLIC_SIZE);
	}

	private void createWithAsyncDataProvider() {
		dataProviderAsync = new AllDataAsyncDataProvider();
		dataProviderAsync.addDataDisplay(souvenirCells);
		dataProviderAsync.updateRowCount(10, true);
		
		

		// souvenirManagerForm.setDataProvider(dataProviderAsync);
		souvenirManagerForm.setDisplay(souvenirCells);

	}

	private void handleUpdates() {
		souvenirCells.setValueUpdater(new ValueUpdater<MessageAudio>() {
			@Override
			public void update(MessageAudio value) {
				Window.alert("Handling update on photo: "
						+ value.getDescription());
				dataProviderAsync.removeDataDisplay(souvenirCells);
				souvenirCells.redraw();
			}
		});
	}

	private void setUpSelectionModel() {
		final SingleSelectionModel<MessageAudio> selectionModel = new SingleSelectionModel<MessageAudio>();
		souvenirCells.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						// if (selectionModel.getSelectedSet().size() > 5) {
						// Window.alert("Cannot select more than 5 items");
						// selectionModel.setSelected(selectionModel
						// .getSelectedSet().iterator().next(), false);
						// }
						//dataProviderAsync.removeDataDisplay(souvenirCells);
						//souvenirCells.redraw();
						souvenirManagerForm.setSouvenir(selectionModel
								.getSelectedObject());
						
						 AsyncCallback<MessageAudio> callback  = new AsyncCallback<MessageAudio>() {
								public void onFailure(Throwable error) {
									//do something, when fail
								}

								public void onSuccess(MessageAudio result) {
									//when successful, do something, about UI
									
									//Window.alert("Sois beni Heureux Internaute, " +
									//		"tu es le "+result.getNbPagesVues()+"eme visiteur de cette page!");

								}

							};
						
						souvenirService.incrementNbPagesVues(selectionModel.getSelectedObject().getId(), callback);
					}
				});
	}
}

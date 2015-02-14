package com.webapp.client.userinterface.mainpage;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.webapp.client.services.MySQLService;
import com.webapp.client.services.MySQLServiceAsync;
import com.webapp.client.userinterface.ResourceWidget;
import com.webapp.shared.Results;

public class MainPanel extends ResourceWidget {

	private static MainPanelUiBinder uiBinder = GWT
			.create(MainPanelUiBinder.class);

	interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
	}

	MySQLServiceAsync MySQL = (MySQLServiceAsync) GWT
			.create(MySQLService.class);

	private Integer read;
	private Integer write;
	private Results[] readResults;
	private Results[] writeResults;
	private Integer readCount = 0;
	private Integer writeCount = 0;

	public MainPanel(String read, String write, String file) {
		this.read = Integer.valueOf(read);
		this.write = Integer.valueOf(write);
		readResults = new Results[this.read];
		writeResults = new Results[this.write];

		initWidget(uiBinder.createAndBindUi(this));

		String status = "Pass";
		String message = "This is a generic message";

		header.setText("Status: " + status);

		Date date = new Date();
		String text = date + ": Status is " + status
				+ ". MySQL (read, write) is (" + read + ", " + write
				+ "). Message is '" + message + "'";
		results.setText(text);
		pathInformation.setText("Information appended to '" + file + "'");

		saveStats();
	}

	private void saveStats() {
		Results results = null;
		for (int i = 0; i < read; i++) {
			results = new Results(i, System.currentTimeMillis());
			readResults[readCount] = results;
			MySQL.read(results, readCallback);
			readCount++;
		}
		for (int i = 0; i < write; i++) {
			MySQL.write(writeCallback);
		}
	}

	private AsyncCallback<Results> readCallback = new AsyncCallback<Results>() {

		@Override
		public void onSuccess(Results result) {
			result.setStopTime(System.currentTimeMillis());
			readResults[result.getIndex()] = result;
			System.out.println("Time it took: " + result.getDuration());
			header.setText("The test was succesful? "
					+ result.getIsSuccessful());
			results.setText(result.getMessage());
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}
	};

	private AsyncCallback<Results> writeCallback = new AsyncCallback<Results>() {

		@Override
		public void onSuccess(Results result) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

	};

	@UiField
	Label header;
	@UiField
	Label results;
	@UiField
	Label pathInformation;
}

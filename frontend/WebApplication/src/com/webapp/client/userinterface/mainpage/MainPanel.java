package com.webapp.client.userinterface.mainpage;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.webapp.client.services.MySQLService;
import com.webapp.client.services.MySQLServiceAsync;
import com.webapp.client.userinterface.ResourceWidget;
import com.webapp.client.userinterface.resources.Resources;
import com.webapp.shared.Constants;
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
	private Integer update;
	private Results[] readResults;
	private Results[] writeResults;
	private Results[] updateResults;
	private Integer readCount = 0;
	private Integer writeCount = 0;
	private Integer updateCount = 0;
	private static String WIDTH = "95%";

	public MainPanel(String read, String write, String file, String update) {
		this.read = Integer.valueOf(read);
		this.write = Integer.valueOf(write);
		this.update = Integer.valueOf(update);
		readResults = new Results[this.read];
		writeResults = new Results[this.write];
		updateResults = new Results[this.update];

		initWidget(uiBinder.createAndBindUi(this));

		resultsDisplay.setSpacing(10);
		resultsDisplay.setWidth(WIDTH);
		ipDisplay.setSpacing(5);
		statusDisplay.setSpacing(5);
		topBar.setWidth(WIDTH);
		topBar.setCellHorizontalAlignment(ipDisplay,
				HasHorizontalAlignment.ALIGN_RIGHT);
		topBar.setCellHorizontalAlignment(statusDisplay,
				HasHorizontalAlignment.ALIGN_LEFT);
		resultsDisplay.setCellHorizontalAlignment(readDisplay,
				HasHorizontalAlignment.ALIGN_LEFT);
		resultsDisplay.setCellHorizontalAlignment(writeDisplay,
				HasHorizontalAlignment.ALIGN_CENTER);
		resultsDisplay.setCellHorizontalAlignment(updateDisplay,
				HasHorizontalAlignment.ALIGN_RIGHT);

		MySQL.getIP(ipCallback);
		version.setText(Constants.VERSION);

		String status = "Pass (Test, no actually work done)";
		String message = "This is a generic message";

		header.setText(status);

		Date date = new Date();
		String text = date + ": Status is " + status
				+ ". MySQL (read, write) is (" + read + ", " + write
				+ "). Message is '" + message + "'";
		results.setText(text);
		// pathInformation.setText("Information appended to '" + file + "'");

		saveStats();
	}

	private AsyncCallback<String> ipCallback = new AsyncCallback<String>() {

		@Override
		public void onSuccess(String result) {
			ip.setText(result);
		}

		@Override
		public void onFailure(Throwable caught) {
			ip.setText("Failed..");
		}
	};

	private void saveStats() {
		Results results = null;
		for (int i = 0; i < read; i++) {
			results = new Results(i, System.currentTimeMillis());
			readResults[readCount] = results;
			MySQL.read(results, readCallback);
			readCount++;
		}
		// TODO: add in
		/*
		 * for (int i = 0; i < write; i++) { MySQL.write(writeCallback); }
		 */
		for (int i = 0; i < update; i++) {
			results = new Results(i, System.currentTimeMillis());
			updateResults[updateCount] = results;
			MySQL.update(results, updateCallback);
			updateCount++;
		}
	}

	private void display() {
		readDisplay.clear();
		writeDisplay.clear();
		updateDisplay.clear();

		int i = 1;
		Label l = null;

		// read
		if (readResults.length == 0) {
			l = new Label("No Information");
			l.setStyleName(Resources.INSTANCE.mainpage().resultLineEmpty());
			readDisplay.add(l);
		} else {
			for (Results r : readResults) {
				l = new Label("Test " + i + ". " + r.format());
				l.setStyleName(Resources.INSTANCE.mainpage().resultLine());
				readDisplay.add(l);
				i++;
			}
		}

		// write
		i = 1;
		if (writeResults.length == 0) {
			l = new Label("No Information");
			l.setStyleName(Resources.INSTANCE.mainpage().resultLineEmpty());
			writeDisplay.add(l);
		} else {
			for (Results r : writeResults) {
				l = new Label("Test " + i + ". " + r.format());
				l.setStyleName(Resources.INSTANCE.mainpage().resultLine());
				writeDisplay.add(l);
				i++;
			}
		}

		// update
		i = 1;
		if (updateResults.length == 0) {
			l = new Label("No Information");
			l.setStyleName(Resources.INSTANCE.mainpage().resultLineEmpty());
			updateDisplay.add(l);
		} else {
			for (Results r : updateResults) {
				l = new Label("Test " + i + ". " + r.format());
				l.setStyleName(Resources.INSTANCE.mainpage().resultLine());
				updateDisplay.add(l);
				i++;
			}
		}
	}

	private AsyncCallback<Results> updateCallback = new AsyncCallback<Results>() {

		@Override
		public void onSuccess(Results result) {
			result.setStopTime(System.currentTimeMillis());
			updateResults[result.getIndex()] = result;
			display();

			String text = (result.getIsSuccessful()) ? "Pass (Update)"
					: "Fail (Update)";
			header.setText(text);
			results.setText(result.getMessage());
		}

		@Override
		public void onFailure(Throwable caught) {
			header.setText("Failure (writeCallback)");
			results.setText(caught.getCause() + ":\t" + caught);
		}
	};

	private AsyncCallback<Results> readCallback = new AsyncCallback<Results>() {

		@Override
		public void onSuccess(Results result) {
			result.setStopTime(System.currentTimeMillis());
			readResults[result.getIndex()] = result;
			display();

			String text = (result.getIsSuccessful()) ? "Pass (Read)"
					: "Fail (Read)";
			header.setText(text);
			results.setText(result.getMessage());
		}

		@Override
		public void onFailure(Throwable caught) {
			header.setText("Failure (readCallback)");
			results.setText(caught.getCause() + ":\t" + caught);
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
	Label ip;
	@UiField
	Label pathInformation;
	@UiField
	Label version;
	@UiField
	VerticalPanel readDisplay;
	@UiField
	VerticalPanel writeDisplay;
	@UiField
	VerticalPanel updateDisplay;
	@UiField
	HorizontalPanel ipDisplay;
	@UiField
	HorizontalPanel statusDisplay;
	@UiField
	HorizontalPanel resultsDisplay;
	@UiField
	HorizontalPanel topBar;
	@UiField
	VerticalPanel writePanel;
	@UiField
	VerticalPanel readPanel;
	@UiField
	VerticalPanel updatePanel;
}

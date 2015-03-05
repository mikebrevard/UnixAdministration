package com.webapp.client.userinterface.mainpage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private String filename;

	public MainPanel(String read, String write, String filename, String update) {
		this.filename = filename;
		this.read = Integer.valueOf(read);
		this.write = Integer.valueOf(write);
		this.update = Integer.valueOf(update);
		readResults = new Results[this.read];
		writeResults = new Results[this.write];
		updateResults = new Results[this.update];

		initWidget(uiBinder.createAndBindUi(this));

		// formatting
		resultsDisplay.setSpacing(10);
		resultsDisplay.setWidth(WIDTH);
		ipDisplay.setSpacing(5);
		statusDisplay.setSpacing(5);
		topBar.setWidth(WIDTH);
		topBar.setCellHorizontalAlignment(ipDisplay,
				HasHorizontalAlignment.ALIGN_RIGHT);
		topBar.setCellHorizontalAlignment(statusDisplay,
				HasHorizontalAlignment.ALIGN_LEFT);
		resultsDisplay.setWidth(WIDTH);
		resultsDisplay.setCellHorizontalAlignment(readDisplay,
				HasHorizontalAlignment.ALIGN_LEFT);
		resultsDisplay.setCellHorizontalAlignment(writeDisplay,
				HasHorizontalAlignment.ALIGN_CENTER);
		resultsDisplay.setCellHorizontalAlignment(updateDisplay,
				HasHorizontalAlignment.ALIGN_RIGHT);

		// get ip address
		MySQL.getIP(ipCallback);

		// display version
		version.setText(Constants.VERSION);

		// run the stats
		saveStats();
	}

	// IP Address
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

		// TODO: sequential
		
		// read
		for (int i = 0; i < read; i++) {
			results = new Results(i, System.currentTimeMillis(), Constants.READ);
			readResults[readCount] = results;
			MySQL.read(results, readCallback);
			readCount++;
		}

		// write
		for (int i = 0; i < write; i++) {
			results = new Results(i, System.currentTimeMillis(),
					Constants.WRITE);
			writeResults[writeCount] = results;
			MySQL.write(results, writeCallback);
			writeCount++;
		}

		// update
		for (int i = 0; i < update; i++) {
			results = new Results(i, System.currentTimeMillis(),
					Constants.UPDATE);
			updateResults[updateCount] = results;
			MySQL.update(results, updateCallback);
			updateCount++;
		}
	}

	private Boolean isLogReady() {
		for (Results r : readResults) {
			if (r == null || !r.isComplete())
				return false;
		}
		for (Results r : writeResults) {
			if (r == null || !r.isComplete())
				return false;
		}
		for (Results r : updateResults) {
			if (r == null || !r.isComplete())
				return false;
		}
		return true;
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

		if (isLogReady()) {
			List<Results> allResults = new ArrayList<Results>();
			addAll(allResults, readResults);
			addAll(allResults, writeResults);
			addAll(allResults, updateResults);

			Date d = new Date();
			MySQL.saveResults(filename, d.toString(), allResults, saveCallback);
		}
	}

	private void addAll(List<Results> allResults, Results[] set) {
		for (Results r : set)
			allResults.add(r);
	}

	// callback from saving file on server
	private AsyncCallback<Void> saveCallback = new AsyncCallback<Void>() {

		@Override
		public void onSuccess(Void result) {
		}

		@Override
		public void onFailure(Throwable caught) {
		}
	};

	private AsyncCallback<Results> updateCallback = new AsyncCallback<Results>() {

		@Override
		public void onSuccess(Results result) {
			result.setStopTime(System.currentTimeMillis());
			updateResults[result.getIndex()] = result;
			display();

			String text = (result.getIsSuccessful()) ? "Pass" : "Fail";
			header.setText(text);
			results.setText(result.getMessage());
		}

		@Override
		public void onFailure(Throwable caught) {
			header.setText("Failure (updateCallback)");
			results.setText(caught.getCause() + ":\t" + caught);
		}
	};

	private AsyncCallback<Results> readCallback = new AsyncCallback<Results>() {

		@Override
		public void onSuccess(Results result) {
			result.setStopTime(System.currentTimeMillis());
			readResults[result.getIndex()] = result;
			display();

			String text = (result.getIsSuccessful()) ? "Pass" : "Fail";
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
			result.setStopTime(System.currentTimeMillis());
			writeResults[result.getIndex()] = result;
			display();

			String text = (result.getIsSuccessful()) ? "Pass" : "Fail";
			header.setText(text);
			results.setText(result.getMessage());
		}

		@Override
		public void onFailure(Throwable caught) {
			header.setText("Failure (writeCallback)");
			results.setText(caught.getCause() + ":\t" + caught);
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

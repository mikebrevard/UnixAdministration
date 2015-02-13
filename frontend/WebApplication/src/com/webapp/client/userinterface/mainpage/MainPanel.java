package com.webapp.client.userinterface.mainpage;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.webapp.client.userinterface.ResourceWidget;

public class MainPanel extends ResourceWidget {

	private static MainPanelUiBinder uiBinder = GWT
			.create(MainPanelUiBinder.class);

	interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
	}

	public MainPanel(String read, String write, String file) {
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
		// TODO:
	}

	@UiField
	Label header;
	@UiField
	Label results;
	@UiField
	Label pathInformation;
}

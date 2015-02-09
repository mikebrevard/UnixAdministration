package com.webapp.client.userinterface.mainpage;

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

	public MainPanel(String server, String login) {
		initWidget(uiBinder.createAndBindUi(this));

		header.setText("Connected to " + login + "@" + server);
	}

	@UiField
	Label header;
}

package com.webapp.client.userinterface.introduction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.webapp.client.userinterface.ResourceWidget;
import com.webapp.client.userinterface.mainpage.MainPanel;
import com.webapp.client.userinterface.utilities.Toolkit;

public class IntroductionPanel extends ResourceWidget {

	private static IntroductionPanelUiBinder uiBinder = GWT
			.create(IntroductionPanelUiBinder.class);

	interface IntroductionPanelUiBinder extends
			UiBinder<Widget, IntroductionPanel> {
	}

	public IntroductionPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		main.setCellHorizontalAlignment(submit,
				HasHorizontalAlignment.ALIGN_RIGHT);
		main.setSpacing(20);

		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Information\nServer: " + server.getText()
						+ "\nLogin: " + login.getText()
						+ "\t\nPassword: ...nope...");
				Toolkit.loadWidget(new MainPanel(server.getText(), login
						.getText()));
			}
		});
	}

	@UiField
	VerticalPanel main;
	@UiField
	TextBox server;
	@UiField
	TextBox login;
	@UiField
	PasswordTextBox password;
	@UiField
	Button submit;
}

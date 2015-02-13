package com.webapp.client.userinterface.introduction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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

	public IntroductionPanel(String read, String write, String file) {
		initWidget(uiBinder.createAndBindUi(this));

		if (read != null)
			reads.setText(read);

		if (write != null)
			writes.setText(write);

		if (file != null)
			filePath.setText(file);

		main.setCellHorizontalAlignment(submit,
				HasHorizontalAlignment.ALIGN_RIGHT);
		main.setSpacing(20);

		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				execute();
			}
		});
	}

	private void execute() {
		Toolkit.loadWidget(new MainPanel(reads.getText(), writes.getText(),
				filePath.getText()));
	}

	@UiField
	VerticalPanel main;
	@UiField
	TextBox reads;
	@UiField
	TextBox writes;
	@UiField
	Button submit;
	@UiField
	TextBox filePath;
}

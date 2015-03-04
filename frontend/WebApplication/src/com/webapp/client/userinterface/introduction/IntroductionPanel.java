package com.webapp.client.userinterface.introduction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.webapp.client.userinterface.ResourceWidget;
import com.webapp.client.userinterface.utilities.Toolkit;
import com.webapp.shared.Constants;

public class IntroductionPanel extends ResourceWidget {

	private static IntroductionPanelUiBinder uiBinder = GWT
			.create(IntroductionPanelUiBinder.class);

	interface IntroductionPanelUiBinder extends
			UiBinder<Widget, IntroductionPanel> {
	}

	public IntroductionPanel(String read, String write, String file,
			String update) {
		initWidget(uiBinder.createAndBindUi(this));
		
		Toolkit.loadWidgetResults(new HTMLPanel("<p>Introduction Page</p>"));
		
		version.setText(Constants.VERSION);
		reads.setText(read);
		writes.setText(write);
		updates.setText(update);
		reads.getElement().setId(Constants.READ);
		writes.getElement().setId(Constants.WRITE);
		updates.getElement().setId(Constants.UPDATE);
		filePath.getElement().setId("file");
		submit.getElement().setId("submit");

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
		Window.open(
				"http://127.0.0.1:8087/CS183WebApplication/?read="
						+ reads.getText() + "&write=" + writes.getText()
						+ "&update=" + updates.getText(), "_self", null);
		// Toolkit.loadWidget(new MainPanel(reads.getText(), writes.getText(),
		// filePath.getText(), updates.getText()));
	}

	@UiField
	VerticalPanel main;
	@UiField
	TextBox reads;
	@UiField
	TextBox writes;
	@UiField
	TextBox updates;
	@UiField
	Button submit;
	@UiField
	TextBox filePath;
	@UiField
	Label version;
}

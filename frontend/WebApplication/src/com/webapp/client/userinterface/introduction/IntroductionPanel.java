package com.webapp.client.userinterface.introduction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.webapp.client.userinterface.ResourceWidget;
import com.webapp.client.userinterface.mainpage.MainPanel;
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
		version.setText(Constants.VERSION);
		reads.setText(read);
		writes.setText(write);
		updates.setText(update);

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
		//
		// // Simple messages
		// logger.trace("A message at trace level.");
		// logger.debug("A message at debug level.");
		// logger.info("A message at info level.");
		// logger.warn("A message at warn level.");
		//
		// // Logging exceptions
		// try {
		// Integer.parseInt("Hallo");
		// } catch (NumberFormatException e) {
		// logger.error("Parsing the number failed", e);
		// }
		//
		// // nested diagnostic context
		// NDC.push("ndc1");
		// NDC.push("ndc2");
		// logger.info("Test for the NDC.");
		// NDC.clear();
		//
		// // mapped diagnostic context
		// MDC.put("key1", "value1");
		// MDC.put("key2", "value2");
		// logger.info("Test for the MDC.");
		// MDC.clear();
	}

	private void execute() {
		Toolkit.loadWidget(new MainPanel(reads.getText(), writes.getText(),
				filePath.getText(), updates.getText()));
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

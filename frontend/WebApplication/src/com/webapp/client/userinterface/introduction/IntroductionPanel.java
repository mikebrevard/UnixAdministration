package com.webapp.client.userinterface.introduction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.webapp.client.userinterface.ResourceWidget;

public class IntroductionPanel extends ResourceWidget {

	private static IntroductionPanelUiBinder uiBinder = GWT
			.create(IntroductionPanelUiBinder.class);

	interface IntroductionPanelUiBinder extends
			UiBinder<Widget, IntroductionPanel> {
	}

	public IntroductionPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}

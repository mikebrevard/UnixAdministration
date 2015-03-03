package com.webapp.client.userinterface.utilities;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Toolkit {

	public static void loadWidget(Widget w) {
		RootPanel.get("main").clear();
		RootPanel.get("main").add(w);
	}

	public static void loadWidgetResults(HTMLPanel w) {
		RootPanel.get("results").clear();
		RootPanel.get("results").add(w);
	}

}

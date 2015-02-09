package com.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.webapp.client.userinterface.introduction.IntroductionPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebApplication implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get("main").add(new IntroductionPanel());
	}
}

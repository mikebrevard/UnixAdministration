package com.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.webapp.client.userinterface.introduction.IntroductionPanel;
import com.webapp.client.userinterface.utilities.Toolkit;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebApplication implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Toolkit.loadWidget(new IntroductionPanel());
	}
}

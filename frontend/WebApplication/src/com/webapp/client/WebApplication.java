package com.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.webapp.client.userinterface.introduction.IntroductionPanel;
import com.webapp.client.userinterface.mainpage.MainPanel;
import com.webapp.client.userinterface.utilities.Toolkit;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebApplication implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// TODO:
		// 1. Add in MySQL saving
		// 2. Add in Appending to file (with date)

		// example
		// 127.0.0.1:8888/WebApplication.html?gwt.codesvr=127.0.0.1:9997&read=6&write=4
		String read = Window.Location.getParameter("read");
		String write = Window.Location.getParameter("write");
		String file = Window.Location.getParameter("file");
		String auto = Window.Location.getParameter("auto");

		if (auto != null && auto.equals("off"))
			Toolkit.loadWidget(new IntroductionPanel(read, write, file));
		else
			Toolkit.loadWidget(new MainPanel(read, write, file));
	}
}

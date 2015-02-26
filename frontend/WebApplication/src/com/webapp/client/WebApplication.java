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
		// 2. Add in Appending to file (with date)

		// example
		// http://127.0.0.1:8087/CS183WebApplication/?read=2&write=0
		String read = Window.Location.getParameter("read");
		String write = Window.Location.getParameter("write");
		String update = Window.Location.getParameter("update");
		String file = Window.Location.getParameter("file");
		String auto = Window.Location.getParameter("auto");

		if (read == null)
			read = "1";
		if (write == null)
			write = "0";
		if (update == null)
			update = "0";

		if (auto != null && auto.equals("off"))
			Toolkit.loadWidget(new IntroductionPanel(read, write, file, update));
		else
			Toolkit.loadWidget(new MainPanel(read, write, file, update));
	}
}

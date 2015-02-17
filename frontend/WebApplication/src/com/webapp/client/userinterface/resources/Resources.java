package com.webapp.client.userinterface.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {
	public static final Resources INSTANCE = GWT.create(Resources.class);

	@Source("css/introduction.css")
	introduction introduction();

	@Source("css/mainpage.css")
	mainpage mainpage();

	public interface introduction extends CssResource {
		String header();

		String main();

		String boxes();

		String label();
		
		String version();
	}

	public interface mainpage extends CssResource {
		String header();

		String main();

		String results();
		
		String version();
	}
}
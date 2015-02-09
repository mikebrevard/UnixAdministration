package com.webapp.client.userinterface.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {
	public static final Resources INSTANCE = GWT.create(Resources.class);

	@Source("css/introduction.css")
	introduction introduction();

	public interface introduction extends CssResource {
		String header();
	}
}
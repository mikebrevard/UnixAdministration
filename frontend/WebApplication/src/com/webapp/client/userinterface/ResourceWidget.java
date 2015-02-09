package com.webapp.client.userinterface;

import com.google.gwt.user.client.ui.Composite;
import com.webapp.client.userinterface.resources.Resources;

public class ResourceWidget extends Composite {
	static {
		Resources.INSTANCE.introduction().ensureInjected();
	}
}
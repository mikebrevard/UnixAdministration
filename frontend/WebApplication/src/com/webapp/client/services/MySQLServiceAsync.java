package com.webapp.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.webapp.shared.Results;

public interface MySQLServiceAsync {
	void read(Results result, AsyncCallback<Results> callback);

	void write(AsyncCallback<Results> callback);

	void update(Results result, AsyncCallback<Results> callback);

	void getIP(AsyncCallback<String> callback);

	void saveResults(String filename, String date, List<Results> results,
			AsyncCallback<Void> callback);
}

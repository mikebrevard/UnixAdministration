package com.webapp.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.webapp.shared.Results;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("MySQLService")
public interface MySQLService extends RemoteService {
	Results read(Results results);

	Results write();

	Results update(Results results);

	String getIP();
}

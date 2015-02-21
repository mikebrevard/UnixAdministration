package com.webapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webapp.client.services.MySQLService;
import com.webapp.shared.Results;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MySQLServiceImpl extends RemoteServiceServlet implements
		MySQLService {
	private Connection connection = null;
	private final static String TABLE = "basicTable";

	private void initConnection() {

		String url = "jdbc:mysql://127.0.0.1:8093/";
		String db = "UnixTestDB";
		String driver = "com.mysql.jdbc.Driver";
		String user = "unixadmin";
		String pass = "unixadmin";

		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("CONNECTION: ");
			System.out.println("\t" + url + db);
			System.out.println("\t" + user);
			System.out.println("\t" + pass);
			connection = DriverManager.getConnection(url + db, user, pass);
		} catch (SQLException e) {
			System.err.println("Mysql Connection Error: " + e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	@Override
	public Results read(Results results) {

		try {
			if (connection == null)
				initConnection();
		} catch (Exception e) {
			results.setIsSuccessful(false);
			results.setMessage("MySQL (read connection): "
					+ e.getLocalizedMessage());
			return results;
		}

		// String query = "SELECT * FROM " + TABLE;

		try {
			// Statement select = connection.createStatement();
			// ResultSet result = select.executeQuery(query);
			// // while (result.next()) {
			// // String s = result.getString(0);
			// // System.out.println("First column: " + s);
			// // }
			// select.close();
			// result.close();
			connection.close();
		} catch (SQLException e) {
			results.setIsSuccessful(false);
			results.setMessage("MySQL (read): " + e.getLocalizedMessage());
			return results;
		}

		results.setIsSuccessful(true);
		results.setMessage("Made it to the end!");
		return results;
	}

	@Override
	public Results write() {
		// TODO: randomize between INSERT and UPDATE

		return null;
	}

}

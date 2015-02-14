package com.webapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webapp.client.services.MySQLService;
import com.webapp.shared.Results;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MySQLServiceImpl extends RemoteServiceServlet implements
		MySQLService {
	private Connection connection;
	private final static String TABLE = "basicTable";

	private void initConnection() {

		String url = "jdbc:mysql://192.168.50.2:3306/";
		String db = "hostdb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String pass = "";

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

			connection = DriverManager.getConnection(url + db, user, pass);
		} catch (SQLException e) {
			System.err.println("Mysql Connection Error: ");
			e.printStackTrace();
		}

	}

	@Override
	public Results read(Results results) {

		if (connection == null)
			initConnection();

		String query = "SELECT * FROM " + TABLE;

		try {
			Statement select = connection.createStatement();
			ResultSet result = select.executeQuery(query);
			while (result.next()) {
				String s = result.getString(0);
				System.out.println("First column: " + s);
			}
			select.close();
			result.close();
			connection.close();
		} catch (SQLException e) {
			results.setIsSuccessful(false);
			results.setMessage(e.getLocalizedMessage());
			System.err.println("Mysql Statement Error: " + query);
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

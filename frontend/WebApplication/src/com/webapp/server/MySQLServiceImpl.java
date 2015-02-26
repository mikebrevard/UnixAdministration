package com.webapp.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webapp.client.services.MySQLService;
import com.webapp.shared.Constants;
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

		String url = "jdbc:mysql://192.168.50.3:3306/";
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
			connection = DriverManager.getConnection(url + db, user, pass);
		} catch (SQLException e) {
			System.err.println("Mysql Connection Error: "
					+ e.getLocalizedMessage());
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

		String query = "SELECT * FROM " + TABLE;

		try {
			Statement select = connection.createStatement();
			ResultSet result = select.executeQuery(query);
			while (result.next()) {
				String s = result.getString(1);
				// TODO: think if I want to do anything with this
			}
			select.close();
			result.close();

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
	public Results update(Results results) {
		// TODO:
		// try {
		// if (connection == null)
		// initConnection();
		// } catch (Exception e) {
		// results.setIsSuccessful(false);
		// results.setMessage("MySQL (updates connection): "
		// + e.getLocalizedMessage());
		// return results;
		// }
		//
		// String query = "SELECT * FROM " + TABLE;
		//
		// try {
		// Statement select = connection.createStatement();
		// ResultSet result = select.executeQuery(query);
		// while (result.next()) {
		// String s = result.getString(1);
		// // TODO: think if I want to do anything with this
		// }
		// select.close();
		// result.close();
		//
		// connection.close();
		// } catch (SQLException e) {
		// results.setIsSuccessful(false);
		// results.setMessage("MySQL (read): " + e.getLocalizedMessage());
		// return results;
		// } finally {
		//
		// }

		results.setIsSuccessful(true);
		results.setMessage("Made it to the end!");
		return results;
	}

	@Override
	public Results write() {
		// TODO: randomize between INSERT and UPDATE

		return null;
	}

	@Override
	public String getIP() {
		return getThreadLocalRequest().getLocalAddr();
	}

	@Override
	public void saveResults(String filename, String date, List<Results> results) {
		Long totalTime = 0L;
		Integer numReads = 0, numWrites = 0, numUpdates = 0;
		for (Results r : results) {
			totalTime += r.getDuration();
			if (r.getTestType().equals(Constants.READ))
				numReads++;
			else if (r.getTestType().equals(Constants.WRITE))
				numWrites++;
			else if (r.getTestType().equals(Constants.UPDATE))
				numUpdates++;
		}

		String data = date + "; Duration=" + totalTime + "ms; Number of Reads="
				+ numReads + "; Number of Writes=" + numWrites
				+ "; Number of Updates=" + numUpdates;

		if (filename == null || filename.isEmpty())
			filename = "unnamedtest";

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("TestFiles/" + filename
					+ ".txt", true));
			bw.write(data);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally { // always close the file
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					// just ignore it
				}
		}
	}
}

package com.webapp.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.tomcat.jdbc.pool.DataSource;

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
	private final static String TABLE = "basicTable";
	private static DataSource datasource = null;

	@Override
	public void init() throws ServletException {
		super.init();
		datasource = (DataSource) getServletContext()
				.getAttribute("datasource");
	}

	@Override
	public Results read(Results results) {
		Statement select = null;
		ResultSet result = null;
		Connection connection = null;

		String query = "SELECT * FROM " + TABLE + " WHERE id="
				+ UserSimulator.getRowNumber();
		try {
			connection = datasource.getConnection();
			select = connection.createStatement();
			result = select.executeQuery(query);
			while (result.next()) {
				// String s = result.getString(1);
				// TODO: think if I want to do anything with this
			}
		} catch (SQLException e) {
			results.setIsSuccessful(false);
			results.setMessage("MySQL (read): " + e.getLocalizedMessage());
			return results;
		} finally {
			DataManagementServiceImpl.close(result, select, connection);
		}

		results.setIsSuccessful(true);
		return results;
	}

	@Override
	public Results update(Results results) {
		Statement select = null;
		ResultSet result = null;
		Connection connection = null;

		String sql = "UPDATE " + TABLE + " SET " + UserSimulator.randomUpdate()
				+ " WHERE id=" + UserSimulator.getRowNumber();
		try {
			connection = datasource.getConnection();
			select = connection.createStatement();
			select.executeUpdate(sql);
		} catch (SQLException e) {
			results.setIsSuccessful(false);
			results.setMessage("MySQL (update): " + e.getLocalizedMessage());
			return results;
		} finally {
			DataManagementServiceImpl.close(result, select, connection);
		}

		results.setIsSuccessful(true);
		return results;
	}

	@Override
	public Results write(Results results) {
		Statement select = null;
		ResultSet result = null;
		Connection connection = null;

		String sql = "INSERT INTO " + TABLE + " " + UserSimulator.randomWrite();
		try {
			connection = datasource.getConnection();
			select = connection.createStatement();
			select.executeUpdate(sql);
		} catch (SQLException e) {
			results.setIsSuccessful(false);
			results.setMessage("MySQL (write): " + e.getLocalizedMessage());
			return results;
		} finally {
			DataManagementServiceImpl.close(result, select, connection);
		}

		results.setIsSuccessful(true);
		return results;
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
			bw = new BufferedWriter(new FileWriter(filename + ".txt", true));
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

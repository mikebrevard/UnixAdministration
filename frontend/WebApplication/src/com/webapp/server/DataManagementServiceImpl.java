package com.webapp.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webapp.client.services.DataManagementService;

public class DataManagementServiceImpl extends RemoteServiceServlet implements
		ServletContextListener, DataManagementService {

	private static final long serialVersionUID = -2086940622042259313L;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://192.168.50.3:3306/UnixTestDB");
		p.setUsername("unixadmin");
		p.setPassword("unixadmin");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setJmxEnabled(true);
		p.setTestWhileIdle(true);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(true);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(1000);
		p.setInitialSize(10);
		p.setMaxWait(30000);
		p.setRemoveAbandonedTimeout(300);
		p.setMinEvictableIdleTimeMillis(60000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementCache");
		DataSource datasource = new DataSource();
		datasource.setPoolProperties(p);
		event.getServletContext().setAttribute("datasource", datasource);
	}

	public static void close(ResultSet rs, Statement ps, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {

		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
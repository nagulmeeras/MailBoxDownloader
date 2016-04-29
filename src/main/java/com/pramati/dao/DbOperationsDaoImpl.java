package com.pramati.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.pramati.bean.DataBean;
import com.pramati.exceptions.NullContentFoundException;

public class DbOperationsDaoImpl implements OperationsDao {
	/*
	 * @attribute SQL connection object
	 */
	public Connection connection = null;
	final static Logger logger = Logger.getLogger(DbOperationsDaoImpl.class);

	public DbOperationsDaoImpl(Properties properties) throws ClassNotFoundException, SQLException , NullPointerException{
		try {
			String driverClassName = properties.getProperty("Mysql.jdbc.driver");
			String username = properties.getProperty("Mysql.jdbc.username");
			String password = properties.getProperty("Mysql.jdbc.password");
			String connectionUrl = properties.getProperty("Mysql.jdbc.url");

			Class.forName(driverClassName);
			connection = DriverManager.getConnection(connectionUrl, username, password);
		} catch (NullPointerException npe) {
			throw new NullPointerException("Properties should not be empty");
		}

	}

	public void closeConnection() throws SQLException {
		if (connection != null)
			connection.close();
	}

	public void saveData(DataBean bean, String fileName) throws Exception {
		try {
			String insertQuery = "INSERT INTO " + "mailbox.mailarchive(year , month , content , mailbox_type )"
					+ "VALUES(? , ? , ? , ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, bean.getYear());
			preparedStatement.setInt(2, bean.getMonth());
			preparedStatement.setBytes(3, bean.getContent());
			preparedStatement.setString(4, bean.getMailbox_type());
			int isSucces = preparedStatement.executeUpdate();
			if (isSucces > 0)
				logger.info("Data insertion is successful");
			else
				logger.info("Unable to insert data...");
		} catch (NullPointerException npe) {
			throw new NullContentFoundException("Bean object is null");
		} finally {
			closeConnection();
		}
	}

}

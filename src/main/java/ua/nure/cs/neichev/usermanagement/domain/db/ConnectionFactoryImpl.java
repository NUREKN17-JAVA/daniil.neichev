package ua.nure.cs.neichev.usermanagement.domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private String driver;
	private String url;
	private String user;
	private String password;
	
	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public ConnectionFactoryImpl(Properties properties) {
		user = properties.getProperty("connection.user");
		password = properties.getProperty("connection.password");
		url = properties.getProperty("connection.url");
		driver = properties.getProperty("connection.driver");
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		// TODO Auto-generated method stub
		//String url = "jdbc:hsqldb:file:db/usermanagement";
		//String user = "sa";
		//String password = "";
		//String driver = "org.hsqldb.jdbcDriver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseException(e);
		}
	}

}

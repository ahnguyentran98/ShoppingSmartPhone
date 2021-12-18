package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

	// create variables for jdbc connection to database
	private static String DB_URL = "jdbc:sqlserver://AHNGUYENTRAN:1433;" + "databaseName=ShoppingDB;"
			+ "integratedSecurity=true";
	private static String USER_NAME = "AHNGUYENTRAN\\ahnguyentran";
	private static String PASSWORD = "";

	public Connection getConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
	}
}

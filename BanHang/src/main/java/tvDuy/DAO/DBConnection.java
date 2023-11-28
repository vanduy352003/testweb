package tvDuy.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private final String serverName = "HP\\SQLEXPRESS";
	private final String dbName = "BTWEB";
	private final String instance = "";// MSSQLSERVER LEAVE THIS ONE

	private final String userID = "sa";
	private final String password = "123456789@d!";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + instance + ";databaseName=" + dbName;
		
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ";databaseName=" + dbName;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		return DriverManager.getConnection(url, userID, password);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new DBConnection().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

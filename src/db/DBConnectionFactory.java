package db;

import db.mongodb.MongoDBConnection;
import db.mysql.MySQLConnection;

public class DBConnectionFactory {
	private static final String DEFAULT_DB = "mysql";
	//private static final String DEFAULT_DB = "mongodb";
	
	public static DBConnection getDBConnection(String db) {// create dbconnection based on given db type
		switch(db) {
			case "mysql":
				return MySQLConnection.getInstance();
				//return new MySQLConnection();
			case "mongodb":
				return MongoDBConnection.getInstance();
			default: 
				throw new IllegalArgumentException("Invalid db" + db);
		}
	}
	public static DBConnection getDBConnection() {
		return getDBConnection(DEFAULT_DB);
	}
}

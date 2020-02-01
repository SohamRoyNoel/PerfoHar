package com.proHar.perfoMeasure.main.databaseModules;

public class AzureDataMigrationCredentials {
	
	public static String hostName = "perfo";
	public static String database = "Performances";
	public static String user = "Candy@perfo";
	public static String password = "Noelle1234";
	public static String driver = "org.mariadb.jdbc.Driver";
	public static String connectionString = "jdbc:sqlserver://"+hostName+".database.windows.net:1433;database="+database+";user="+user+";password="+password+";encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

}

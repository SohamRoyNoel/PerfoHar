package com.proHar.perfoMeasure.main.databaseModules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.proHar.perfoMeasure.main.ValueParser;

public class AzureDataMigrationUtils {

	public void AzureDatabaseManagerAgent() throws SQLException, ClassNotFoundException {

		String ifNavigationDataTableExists = "SELECT * FROM information_schema.tables WHERE table_schema = 'dbo' AND table_name = 'Navigation';";
		String createNavigationTable = "CREATE TABLE Navigation (id INTEGER IDENTITY(1,1) PRIMARY KEY, Appname VARCHAR(100), BaseURL VARCHAR(500),Unloadevent VARCHAR(30),Redirectevent VARCHAR(30), AppCache VARCHAR(30),TTFB VARCHAR(30),Processing VARCHAR(30),Dom_Interactive VARCHAR(30), Dom_Complete VARCHAR(30),Content_load VARCHAR(30),Page_load VARCHAR(30), DateTimes VARCHAR(50));";
		String insertIntoNavigationTable = "insert into Navigation (Appname,BaseURL,Unloadevent,Redirectevent,AppCache,TTFB,Processing,Dom_Interactive,Dom_Complete,Content_load,Page_load, DateTimes) values (?,?,?,?,?,?,?,?,?,?,?,?);";


		String ifResourcesDataTableExists = "SELECT * FROM information_schema.tables WHERE table_schema = 'dbo' AND table_name = 'Resources';";
		String createResourceTable = "CREATE TABLE Resources (id INTEGER IDENTITY(1,1) PRIMARY KEY, Appname VARCHAR(100), BaseURL VARCHAR(500),ElementName VARCHAR(500),Duration VARCHAR(30), Dates VARCHAR(50));";
		String insertIntoresourceTable = "insert into Resources (Appname,BaseURL,ElementName,Duration,Dates) values (?,?,?,?,?)";

		String[] navtemp;
		String[] restemp;

		PreparedStatement ps;
		ResultSet rs;

		// check that the driver is installed
		try{
			Class.forName(AzureDataMigrationCredentials.driver);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("MariaDB JDBC driver NOT detected in library path.", e);
		}

		Connection connection = null;

		// Initialize connection object
		try {
			String url = String.format(AzureDataMigrationCredentials.connectionString);
			// get connection
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new SQLException("Failed to create connection to database.", e);
		}
		if (connection != null) { 
			System.out.println("Successfully created connection to database.");

			// Perform some SQL queries over the connection.
			try {
				// Drop previous table of same name if one exists.
				Statement statement = connection.createStatement();

				// Create if No Navigation Table is found
				ps = connection.prepareStatement(ifNavigationDataTableExists);
				rs = ps.executeQuery();
				if (rs.wasNull()) {
					statement.execute(createNavigationTable);
				}

				// Create If No resource table is found
				ps = connection.prepareStatement(ifResourcesDataTableExists);
				rs = ps.executeQuery();
				if (rs.wasNull()) {
					statement.execute(createResourceTable);
				}

				// get All of the Listed Values
				List<String> navigationValues = ValueParser.navHolder;
				List<String> resourceValues = ValueParser.resHolder;

				// Insert some data into table.
				int nRowsInserted = 0;
				
				// Navigation Values
				for (String nav : navigationValues) {
					PreparedStatement preparedStatement = connection.prepareStatement(insertIntoNavigationTable);
					navtemp = nav.split(",");
					preparedStatement.setString(1, navtemp[0]);
					preparedStatement.setString(2, navtemp[1]);
					preparedStatement.setString(3, navtemp[2]);
					preparedStatement.setString(4, navtemp[3]);
					preparedStatement.setString(5, navtemp[4]);
					preparedStatement.setString(6, navtemp[5]);
					preparedStatement.setString(7, navtemp[6]);
					preparedStatement.setString(8, navtemp[7]);
					preparedStatement.setString(9, navtemp[8]);
					preparedStatement.setString(10, navtemp[9]);
					preparedStatement.setString(11, navtemp[10]);
					preparedStatement.setString(12, navtemp[11]);

					nRowsInserted += preparedStatement.executeUpdate();
				}
				System.out.println(String.format("Navigation Data Inserted"));
				
				// Resource Values
				for (String nav : resourceValues) {
					PreparedStatement preparedStatement = connection.prepareStatement(insertIntoresourceTable);
					restemp = nav.split(",");
					preparedStatement.setString(1, restemp[0]);
					preparedStatement.setString(2, restemp[1]);
					preparedStatement.setString(3, restemp[2]);
					preparedStatement.setString(4, restemp[3]);
					preparedStatement.setString(5, restemp[4]);

					nRowsInserted += preparedStatement.executeUpdate();
				}
				System.out.println(String.format("Resource Data Inserted"));	

			}
			catch (SQLException e) {
				throw new SQLException("Encountered an error when executing given sql statement.", e);
			}		
		}
		else {
			System.out.println("Failed to create connection to database.");
		}
		System.out.println("Execution finished.");

	}

}

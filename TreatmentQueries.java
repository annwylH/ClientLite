package ClientLite;
/*This class manages database operations for the Treatment class*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TreatmentQueries {
	
	// variables for connecting to database
			private static final String URL = "jdbc:mysql://localhost:3306/clientlite?verifyServerCertificate=false&useSSL=true";
			private static final String USERNAME = "root";
			private static final String PASSWORD = "";//insert your password here
			
			private Connection connection = null;
			private PreparedStatement countTreatments = null;
			private PreparedStatement insertTreatment = null;
			private PreparedStatement deleteTreatments = null;
			
			public TreatmentQueries()
			{
				try
				{
					connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Starts a connection to the database
					insertTreatment = connection.prepareStatement("insert into treatments (location, date, clients_id) values (?,?,?) ");//Prepares the insert query to add new client
					countTreatments = connection.prepareStatement("select * from treatments where location=? ");//Prepares the select query that gets all clients per location
					//MySQL and Java DB currently do not support the DISTINCT SQL data type so the uniqueness in countTreatments is achieved in the ClientLite.generateClientReport method
					deleteTreatments = connection.prepareStatement("delete from treatments");//deletes all treatments
				}
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
					System.exit(1);
				}
			}
			
			public int addTreatment(String location, String date, int clientsId)//used and tested
			{
				int rowsAdded = 0;
				try
				{
					
					// Setting the values for the question marks '?' in the prepared statement
					insertTreatment.setString(1, location);
					insertTreatment.setString(2, date);
					insertTreatment.setInt(3, clientsId);
								
					// result will contain the amount of added rows
					rowsAdded = insertTreatment.executeUpdate(); 
				}
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
				} return rowsAdded;//returns number of rows added for info dialog
			}//end method addClient
			
			public ArrayList<Treatment> getAllTreatments(String location)//used and tested
			{
				ArrayList<Treatment> results = null;
				ResultSet resultSet = null;
				
				try
				{
					countTreatments.setString(1, location);
					resultSet = countTreatments.executeQuery();
					results = new ArrayList<Treatment>();
				
					while(resultSet.next()) // for each row returned by the select query...
					{
						// Initialize a new Treatment object with the row's data. Add the Treatment object to the results ArrayList
						results.add(new Treatment(
							resultSet.getInt("clients_id"), // get the value associated to the client_id column
							resultSet.getString("location"), // get the value associated to the location column
							resultSet.getString("date"))); // get the value associated to the date column
					}
				} // end try
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
				}
				finally
				{
					try
					{
						resultSet.close();
					}
					catch (SQLException sqlException)
					{
						sqlException.printStackTrace();
					}
				} // end finally
				
				return results;
			} // end method getAllTreatments()
			
			public int deleteAllTreatments() //used and tested
			{
				int rowsDeleted = 0;
				try
				{
					rowsDeleted = deleteTreatments.executeUpdate();
				}
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
				} return rowsDeleted;
				
			}
			
			
			
			
}



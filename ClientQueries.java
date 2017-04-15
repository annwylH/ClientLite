package ClientLite;
//This class manages database operations for the Client class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;


public class ClientQueries {
	// variables for connecting to database
		private static final String URL = "jdbc:mysql://localhost:3306/clientlite?verifyServerCertificate=false&useSSL=true";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "KrPl1o7q69";
		
		private Connection connection = null;
		private PreparedStatement insertClient = null;
		private PreparedStatement selectClient = null;
		private PreparedStatement updateClientNumber = null;
		private PreparedStatement updateClientName = null;
		private PreparedStatement deleteClient = null;
		
		public ClientQueries()
		{
			try
			{
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Starts a connection to the database
				insertClient = connection.prepareStatement("insert into clients (fname, lname, telnum) values (?,?,?) ");//Prepares the insert query to add new client
				selectClient = connection.prepareStatement("select * from clients where fname=? and lname=?");//Prepares the select query to find info on a client
				updateClientNumber = connection.prepareStatement("update clients set telnum=? where fname=? and lname=?");//Prepares the update query to change number
				updateClientName = connection.prepareStatement("update clients set lname=? where fname=? and lname=?");//Prepares the update query to change last name
				deleteClient = connection.prepareStatement("delete from clients where fname=? and lname=?");//Prepares the delete query to remove info
				
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
				System.exit(1);
			}
		}
		
		public int addClient(String fname, String lname, String telnum)//used and tested
		{
			int rowsAdded = 0;
			try
			{
				
				// Setting the values for the question marks '?' in the prepared statement
				insertClient.setString(1, fname);
				insertClient.setString(2, lname);
				insertClient.setString(3, telnum);
							
				// result will contain the amount of added rows
				rowsAdded = insertClient.executeUpdate();
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			return rowsAdded;//returns number of rows added for message dialog
		}//end method addClient
		
		public ArrayList<Client> findInfo(String fname, String lname)// used and tested 
		{
			
			ArrayList<Client> results = null;
			ResultSet resultSet = null;
			
			try
			{
				selectClient.setString(1, fname);
				selectClient.setString(2,  lname);
				resultSet = selectClient.executeQuery(); 
				results = new ArrayList<Client>();
			
				while(resultSet.next()) // for each row returned by the select query...
				{
					// Initialize a new Client object with the row's data. Add the Car object to the results ArrayList
					results.add(new Client(
						resultSet.getInt("id"), // get the value associated to the id column
						resultSet.getString("fname"), // get the value associated to the fname column
						resultSet.getString("lname"), // get the value associated to the lname column
						resultSet.getString("telnum"))); // get the value associated to the telnum column
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
		} // end method findInfo
		
		public int changeNumber(String newnum, String fname, String lname)//used and tested
		{
			int rowsUpdated = 0;
			try
			{
				
				// Setting the values for the question marks '?' in the prepared statement
				updateClientNumber.setString(1, newnum);
				updateClientNumber.setString(2, fname);
				updateClientNumber.setString(3, lname);
							
				// result will contain the amount of added rows
				rowsUpdated = updateClientNumber.executeUpdate(); 
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			} return rowsUpdated;//returns number of rows updated for message dialog
		}//end method changeNumber
		
		public int changeName(String newname, String fname, String lname)//used and tested
		{
			int rowsUpdated = 0;
			try
			{
				
				// Setting the values for the question marks '?' in the prepared statement
				updateClientName.setString(1, newname);
				updateClientName.setString(2, fname);
				updateClientName.setString(3, lname);
							
				// result will contain the amount of added rows
				rowsUpdated = updateClientName.executeUpdate(); 
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			} return rowsUpdated;//returns the number of rows updated for message dialog	
		}//end method changeNumber
		
		public int deleteInfo(String fname, String lname)//used and tested
		{
			int rowsDeleted = 0;
			try
			{
				
				// Setting the values for the question marks '?' in the prepared statement
				deleteClient.setString(1, fname);
				deleteClient.setString(2, lname);
							
				// result will contain the amount of added rows
				rowsDeleted = deleteClient.executeUpdate(); 
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			} return rowsDeleted;//returns the number of rows deleted for message dialog	
		}//end method deleteInfo
		

}

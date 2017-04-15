# ClientLite

Description
Learning project for a lightweight client database for the use of e.g. massage therapists. Background and user stories can be found in the CL_UserStories.txt file.

Installation
Set up your MySQL server. Run the createClientLiteDatabase.sql query in e.g. MySQL Workbench to create the databse and insert some data into the tables. Download the java files. Insert your MySQL server password into ClientQueries.java on line 16 and TreatmentQueries.java on line 15. Run the ClientLite.java file.

Usage
In the main GUI you will find a table for query results. The different functions are behind buttons. 
Add client allows you to add a new client to the database with a first name, last name and telephone number. Client id will be auto-incremented.
Add session allows you to adda new treatment session to the database with a location, date and client id.
Find client allows you to search for a client in the database using first name and last name and will display the info in a table. Use this to find the client id for adding a new treatment.
Edit number allows you to change a client's phone number.
Edit name allows you to change the last name of a client.
Get clients/location allows you to get the number of different, distinct clients per location.
Get sessions/location allows you to get the number of treatments per location.
Reset treatments allows you to delete all treatments at the end of the year.


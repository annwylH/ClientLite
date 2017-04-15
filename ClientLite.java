package ClientLite;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ClientLite extends JFrame{
	
	private ClientQueries clientQueries;
	private TreatmentQueries treatmentQueries;
	private ArrayList<Client> myClients;
	private Client currentClient;
	private ArrayList<Treatment> myTreatments;
	private Treatment currentTreatment;
	private ArrayList<String> justDates = new ArrayList<String>();
	
	JTable resultsTable; 
	JButton btnAddClient;
	JButton btnAddSession;
	JButton btnFindInfo;
	JButton btnUpdateNumber;
	JButton btnUpdateName;
	JButton btnDeleteClient;
	JButton createClientReport;
	JButton createSessionReport;
	JButton resetTreatments;
	
	public ClientLite(){
		super("ClientLite app");
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));

		clientQueries = new ClientQueries(); // Initializes a clientQueries object to handle database operations in this session
		treatmentQueries = new TreatmentQueries(); // Initializes a treatmentQueries object to handle database operations in this session
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0,0,500,400); 
		setLocationRelativeTo(null); 
		
		JLabel lblWelcome = new JLabel("Welcome to edit your client database!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 13));
		lblWelcome.setBounds(100, 10, 300, 14);
		getContentPane().add(lblWelcome);

		resultsTable = new JTable();
		resultsTable.setRowSelectionAllowed(false);
		resultsTable.setModel(new DefaultTableModel(
			new Object[]{"Id","First name", "Last name", "Phone no"}, 4
		));
		resultsTable.setBounds(100, 75, 300, 64);
		getContentPane().add(resultsTable);
		
		btnAddClient = new JButton("Add client");
		btnAddClient.setBounds(90, 35, 100, 23);
		getContentPane().add(btnAddClient);
		
		btnAddSession = new JButton("Add session");
		btnAddSession.setBounds(197, 35, 105, 23);
		getContentPane().add(btnAddSession);
		
		btnFindInfo = new JButton("Find client");
		btnFindInfo.setBounds(310, 35, 100, 23);
		getContentPane().add(btnFindInfo);
		
		btnUpdateNumber = new JButton("Edit number");
		btnUpdateNumber.setBounds(90, 150, 105, 23);
		getContentPane().add(btnUpdateNumber);
		
		btnUpdateName = new JButton("Edit name");
		btnUpdateName.setBounds(305, 150, 105, 23);
		getContentPane().add(btnUpdateName);
		
		btnDeleteClient = new JButton("DELETE");
		btnDeleteClient.setBounds(200, 175, 100, 23);
		getContentPane().add(btnDeleteClient);
		
		createClientReport = new JButton("Get clients/location");
		createClientReport.setBounds(30, 300, 200, 23);
		getContentPane().add(createClientReport);
		
		createSessionReport = new JButton("Get sessions/location");
		createSessionReport.setBounds(30, 330, 200, 23);
		getContentPane().add(createSessionReport);
		
		resetTreatments = new JButton("RESET TREATMENTS");
		resetTreatments.setBounds(250, 330, 200, 23);
		getContentPane().add(resetTreatments);
		
		
		MyEventHandler commandHandler = new MyEventHandler();
		btnAddClient.addActionListener(commandHandler);
		btnAddSession.addActionListener(commandHandler);
		btnFindInfo.addActionListener(commandHandler);
		btnUpdateNumber.addActionListener(commandHandler);
		btnUpdateName.addActionListener(commandHandler);
		btnDeleteClient.addActionListener(commandHandler);
		createClientReport.addActionListener(commandHandler);
		createSessionReport.addActionListener(commandHandler);
		resetTreatments.addActionListener(commandHandler);
		
	}
	
	private class MyEventHandler implements ActionListener
	{
		public void actionPerformed (ActionEvent myEvent)
		{
			if (myEvent.getSource() == btnAddClient){
					addNewClient();
					}
			if (myEvent.getSource() == btnAddSession){
				addNewSession();
				}
			if (myEvent.getSource() == btnFindInfo){
				getClientInfo();
				}
			if (myEvent.getSource() == btnUpdateNumber){
				changeClientNumber();
				}
			if (myEvent.getSource() == btnUpdateName){
				changeClientName();
				}
			if (myEvent.getSource() == btnDeleteClient){
				deleteClientInfo();
				}
			if (myEvent.getSource() == createClientReport){
				generateClientReport();
				}
			if (myEvent.getSource() == createSessionReport){
				generateSessionReport();
				}
			if (myEvent.getSource() == resetTreatments){
				deleteAllTreatments();
				}
			}
		}
	
	private void addNewClient(){//adds new Client to database
		int noOf = 0;
		JTextField fnameField = new JTextField(10);
	    JTextField lnameField = new JTextField(10);
	    JTextField numberField = new JTextField(10);
 
	    JPanel myPanel = new JPanel();
	    
	    myPanel.add(new JLabel("First name:"));
	    myPanel.add(fnameField);
	    
	    myPanel.add(new JLabel("Last name:"));
	    myPanel.add(lnameField);

	    myPanel.add(new JLabel("Phone number:"));
	    myPanel.add(numberField);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter a new client", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION) {
	    	
	    	String fname = fnameField.getText();
	    	String lname = lnameField.getText();
	    	String number = numberField.getText();
	    	noOf = clientQueries.addClient(fname, lname, number);
	    	JOptionPane.showMessageDialog(null, noOf + " client(s) added to the database", "Info", JOptionPane.INFORMATION_MESSAGE);
	    }
	}//end of addNewClient method
	
	private void addNewSession(){//adds new treatment session to database
		int noOf = 0;
		JTextField locationField = new JTextField(10);
	    JTextField dateField = new JTextField(10);
	    JTextField idField = new JTextField(10);
 
	    JPanel myPanel = new JPanel();
	    
	    myPanel.add(new JLabel("Location:"));
	    myPanel.add(locationField);
	    
	    myPanel.add(new JLabel("Date (yyyy-mm-dd):"));
	    myPanel.add(dateField);

	    myPanel.add(new JLabel("Client id:"));
	    myPanel.add(idField);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter a new treatment session", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION) {
	    	
	    	String location = locationField.getText();
	    	String date = dateField.getText();
	    	String idClient = idField.getText();
	    	int idClientNum = Integer.parseInt(idClient); 
	    	noOf = treatmentQueries.addTreatment(location, date, idClientNum);
	    	JOptionPane.showMessageDialog(null, noOf + " session(s) added to the database", "Info", JOptionPane.INFORMATION_MESSAGE);
	    }
	}// end of addNewSession method
	
	private void getClientInfo() {//gets specified Clients and adds them to the table
		JTextField fnameField = new JTextField(10);
		JTextField lnameField = new JTextField(10);
		
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("First name:"));
		myPanel.add(fnameField);
		myPanel.add(new JLabel("Last name:"));
		myPanel.add(lnameField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Find client(s) with the parameters given", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION){
	    	
	    	String namef = fnameField.getText();
	    	String namel = lnameField.getText();
	    	myClients = clientQueries.findInfo(namef, namel); // Calling the ClientQueries method that returns a list containing specified clients
	    	
	    	for (int row=0; row<myClients.size(); row++){ //myClients.size() returns the amount of items in the allCars list
			
			currentClient = myClients.get(row); // get a Client from the ArrayList myClients
			
			resultsTable.setValueAt(currentClient.getClientid(), row, 0);  
			resultsTable.setValueAt(currentClient.getFname(), row, 1);  
			resultsTable.setValueAt(currentClient.getLname(), row, 2);
			resultsTable.setValueAt(currentClient.getTelnum(), row, 3);
	    	}
	    }
	}// end of getClientInfo method
	
	private void deleteClientInfo() {//deletes a specified client
		int noOf = 0;
		JTextField fnameField = new JTextField(10);
		JTextField lnameField = new JTextField(10);
		
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("First name:"));
		myPanel.add(fnameField);
		myPanel.add(new JLabel("Last name:"));
		myPanel.add(lnameField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Delete this client from the database", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			
			String fname = fnameField.getText();
	    	String lname = lnameField.getText(); 
	    	noOf = clientQueries.deleteInfo(fname, lname);
	    	JOptionPane.showMessageDialog(null, noOf + " client(s) deleted from the database", "Info", JOptionPane.INFORMATION_MESSAGE);
	    }
	}//end of deleteClientInfo method
	
	private void changeClientNumber() {//changes phone number of a specified client
		int noOf = 0;
		JTextField fnameField = new JTextField(10);
		JTextField lnameField = new JTextField(10);
		JTextField numberField = new JTextField(10);
		
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("First name"));
		myPanel.add(fnameField);
		myPanel.add(new JLabel("Last name"));
		myPanel.add(lnameField);
		myPanel.add(new JLabel("New phone no:"));
		myPanel.add(numberField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Change client's phone number", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
	    	
	    	String fname = fnameField.getText();
	    	String lname = lnameField.getText();
	    	String number = numberField.getText();
	    	noOf = clientQueries.changeNumber(number, fname, lname);
	    	JOptionPane.showMessageDialog(null, "Number changed for " + noOf + " client(s)", "Info", JOptionPane.INFORMATION_MESSAGE);
	    }
	}// end of changeCLientNumber method
	
	private void changeClientName() {//changes last name of specified client
		int noOf = 0;
		JTextField fnameField = new JTextField(10);
		JTextField lnameField = new JTextField(10);
		JTextField newNameField = new JTextField(10);
		
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("First name"));
		myPanel.add(fnameField);
		myPanel.add(new JLabel("Last name"));
		myPanel.add(lnameField);
		myPanel.add(new JLabel("New last name:"));
		myPanel.add(newNameField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Change client's last name", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
	    	
	    	String fname = fnameField.getText();
	    	String lname = lnameField.getText();
	    	String newname = newNameField.getText();
	    	noOf = clientQueries.changeName(newname, fname, lname);
	    	JOptionPane.showMessageDialog(null, "Name changed for " + noOf + " client(s)", "Info", JOptionPane.INFORMATION_MESSAGE);
	    }
	}//end of changeClientName method
	
	
	private void generateClientReport() {// gets number of distinct clients at specified location
		int amount = 0;
		JTextField locationField = new JTextField(30);
		
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("Location"));
		myPanel.add(locationField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Get amount of different clients for location", JOptionPane.OK_CANCEL_OPTION);
		
			if (result == JOptionPane.OK_OPTION){
	    	
	    	String location = locationField.getText();
	    	myTreatments = treatmentQueries.getAllTreatments(location); // Calling the ClientQueries method that returns a list containing all clients per location
	    	
	    	for(int i=0; i< myTreatments.size(); i++) {
	    		
	    	
	    		currentTreatment = (myTreatments).get(i); // get a Client from the ArrayList myClients
	    		int jDate = currentTreatment.getClientId();
	    		String juDate = Integer.toString(jDate);
	    		justDates.add(i, juDate);
	    		}
	    	
	    	
	    	final Set<String> set = new HashSet<String>(justDates);
	    	amount = set.size();
	    	
	    	
	    	
	    	JOptionPane.showMessageDialog(null, "There were " + amount + " different clients at " + location, "Info", JOptionPane.INFORMATION_MESSAGE);
	    	
	    	
	    }
		
	}// end of generateClientReport method
	
	private void generateSessionReport() {//gets number of all treatments at specified location
		
		int amount = 0;
		JTextField locationField = new JTextField(30);
		
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("Location"));
		myPanel.add(locationField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Get amount of treatments for location", JOptionPane.OK_CANCEL_OPTION);
		
			if (result == JOptionPane.OK_OPTION){
				
	    	String location = locationField.getText();
	    	myTreatments = treatmentQueries.getAllTreatments(location); // Calling the ClientQueries method that returns a list containing all clients per location
	    	
	    	amount = myTreatments.size();
	    	JOptionPane.showMessageDialog(null, "There were " + amount + " treatments " + location, "Info", JOptionPane.INFORMATION_MESSAGE);
	    	
	    	
	    }
		
	}// end of generateSessionReport method
	
	
	private void deleteAllTreatments() {//deletes all treatments
		
		int amount = 0;
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("All treatments will be deleted from the Database. If you haven't generated your reports yet, press Cancel and go back to do that first."));
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Reset treatments", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			
			amount = treatmentQueries.deleteAllTreatments();
			JOptionPane.showMessageDialog(null, amount + " rows were deleted", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
	}// end of deleteAllTreatments method

	public static void main(String[] args) {
		ClientLite frame = new ClientLite();
		frame.setVisible(true);
		
	}

}

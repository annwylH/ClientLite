package ClientLite;

public class Client {
	
	private String fname;
	private String lname;
	private String telnum;
	private int clientId;
	
	Client (int aClientId, String aFname, String aLname, String aTelnum )
	{
		this.fname = aFname;
		this.lname = aLname;
		this.telnum = aTelnum;
		this.clientId = aClientId;
	}
	
//these are used in Clientlite.getClientInfo()
	public String getFname()
	{
		return this.fname;
	}
		
	public String getLname()
	{
		return this.lname;
	}
		
	public String getTelnum()
	{
		return this.telnum;
	}
		
	public int getClientid()
	{
		return this.clientId;
	}	

}

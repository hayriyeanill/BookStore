package book;
import javax.faces.bean.*;



@ManagedBean
public class SetupDb {
	private String status;
		
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String create(){
		if(DataAccess.createDb())
			status = "Database created.";
		else
			status = "Unable to create database.";
		
		return null; //re-display page
	}
	
	public String delete(){
		if(DataAccess.deleteBooks())
			status = "Database contents have been deleted.";
		else
			status = "Unable to delete database content.";
		
		return null; //re-display page
	}
	
	
	
}

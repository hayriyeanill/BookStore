package book;

public class Users {
	private String fullName;
	private String email1;
	private String password;
	
	public Users(String fullName, String password, String email1) {
		super();
		this.fullName = fullName;
		this.password = password;
		this.email1 = email1;
	
	}
    
	public Users() {
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}

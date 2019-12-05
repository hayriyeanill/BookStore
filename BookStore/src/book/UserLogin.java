package book;

import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;



@ManagedBean

public class UserLogin {
 private String email;
 private String password;
 private String message;

 

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

//Test123test
	
	  public String Login() { 
		  FacesContext fc = FacesContext.getCurrentInstance();
	  ResourceBundle rb = fc.getApplication().getResourceBundle(fc, "msgs");
	  if(this.email.equals("hayriye.anil@gmail.com") && this.password.equals("Test123test") ||
			  this.email.equals("halil.savas@gmail.com") && this.password.equals("123Test123") ) {
		  return "AdminHome"; 
	  }
	  if(DataAccess.LoginCheck(email, password)) {
		  return "UserHome"; 
		  }else { 
			  message = rb.getString("loginMessage"); //this.message ="Email or Password incorrect or unregistered."; 
			  } return null; 
			  
	  }
	 
		

 public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "UserLogin";
}



 
}

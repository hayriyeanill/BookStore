package book;


import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class FormUsers extends Users {
	
	private String email2;
	private String password2;
	private String message;
	
	// Getters and setters
	
	public String getUsers(){
		return DataAccess.viewUserData();
	}
	
	public List<Users> getUserList(){
		return DataAccess.UserList();
	}
	
	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	


	public void checkEmail(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException {
		// Check whether the e-mail address has been used before.
		String email = (String)value;
		if(DataAccess.isEmailSavedBefore(email)) {
			String messageText = "E-mail address is already registered.";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(errorMessage);
		}
	}

	public String saveForm(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle rb = fc.getApplication().getResourceBundle(fc, "msgs");
		
				if (DataAccess.saveUserData(getFullName(), getPassword(), getEmail1()))
					message = rb.getString("WelcomeMessage"); //this.message = "Hi " + fullName.toString() + " Your registration was successfully completed";	
				else
					message = rb.getString("ErrorMessage"); //this.message = "Sorry. Unable to completed registration. Please try again.";
			
				return null;	
	}
	
	public String updateUsers() {
		DataAccess.updateUserData(getFullName(), getPassword(), getEmail1());
		return "AdminHome";
	}
	
	public String deleteUsers(){
		DataAccess.deleteUser(getEmail1());
		return "AdminHome";
		//page_number, first_edition_year, language, category, publisher, price

		}
	
	public String RemoveUsers(String email1) {
		Users u = DataAccess.findUser(email1);
		this.setFullName(u.getFullName());
		this.setPassword(u.getPassword());
		this.setEmail1(u.getEmail1());
		return "RemoveUser";
	}
	

	public String editUsers(String email1) {
		Users u = DataAccess.findUser(email1);
		this.setFullName(u.getFullName());
		this.setPassword(u.getPassword());
		this.setEmail1(u.getEmail1());
		
		return "UpdateUser";
	}
	
	
	
}
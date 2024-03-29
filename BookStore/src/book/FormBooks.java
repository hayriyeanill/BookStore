 package book;

import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import book.DataAccess;




@ManagedBean
public class FormBooks extends Books  {
   
   private String status;
   public String selectedbook;
 

   
public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getSelectedbook() {
	return selectedbook;
}

public void setSelectedbook(String selectedbook) {
	this.selectedbook = selectedbook;
}

public String getBooks(){
	return DataAccess.viewData();
}

public List<Books> getBooksList(){
	return DataAccess.BookList();
}

public List<Books> getSelectedBooks(){
	return DataAccess.selectBook();
}

public String formPage() {
	return "form";
}

public String listPage() {
	return "list";
}

public String save(){
	FacesContext fc = FacesContext.getCurrentInstance();
	ResourceBundle rb = fc.getApplication().getResourceBundle(fc, "msgs");
	
	if (getBname().isEmpty() || getAuthor().isEmpty() || getPage_number().isEmpty() || 
			getFirst_edition_year().isEmpty() || getLanguage().isEmpty() || getCategory().isEmpty() ||
			getPublisher().isEmpty() || getPrice().isEmpty()){
		status = rb.getString("validationError"); // "Please fill all of the form fields.";
	}
	else{
		if(DataAccess.saveData(getBname(), getAuthor(), getPage_number(), getFirst_edition_year(), getLanguage(),getCategory(),getPublisher(),getPrice() ))
			status = rb.getString("success"); //status = "Thank you!";
		else
			status = rb.getString("unknownError"); //status = "An error occurred while processing your request.";
	}
	
	return null; //re-display page
}

public String updateBooks() {
	DataAccess.updateBookData(getAuthor(), getPage_number(), getFirst_edition_year(), getLanguage(), getCategory(), getPublisher(), getPrice(),getBname());
	return "AdminHome";
}

public String editBooks(String bname) {
	Books u = DataAccess.findBooks(bname);
	this.setBname(u.getBname());
	this.setAuthor(u.getAuthor());
	this.setPage_number(u.getPage_number());
	this.setFirst_edition_year(u.getFirst_edition_year());
	this.setLanguage(u.getLanguage());
	this.setCategory(u.getCategory());
	this.setPublisher(u.getPublisher());
	this.setPrice(u.getPrice());
	
	return "UpdateBook";
}

public String deleteBooks(){
	DataAccess.deleteData(getBname(),getAuthor());
	return "AdminHome";
	//page_number, first_edition_year, language, category, publisher, price

	}



	public String RemoveBooks(String bname) {
		Books u = DataAccess.findBooks(bname);
		this.setBname(u.getBname());
		this.setAuthor(u.getAuthor());
		this.setPage_number(u.getPage_number());
		this.setFirst_edition_year(u.getFirst_edition_year());
		this.setLanguage(u.getLanguage());
		this.setCategory(u.getCategory());
		this.setPublisher(u.getPublisher());
		this.setPrice(u.getPrice());	
		return "RemoveBook" ;
	}
	
	public boolean isBookSelected(){
		if ((selectedbook == null) || (selectedbook.equals("-"))){
			status = "";
			return false;
		}
		else{
			return true;
		}
	}
	
	

}

	


   
   


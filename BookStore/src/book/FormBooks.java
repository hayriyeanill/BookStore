package book;
/*import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;*/

//import java.util.List;

import javax.faces.bean.ManagedBean;


import book.DataAccess;
//import book.Users;


@ManagedBean
public class FormBooks {

private String bname;
   private String author;
   private String page_number;
   private String first_edition_year;
   private String language;
   private String category;
   private String publisher;
   private String price;
   private String status;
  
   
public String getBname() {
	return bname;
}
public void setBname(String bname) {
	this.bname = bname;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPage_number() {
	return page_number;
}
public void setPage_number(String page_number) {
	this.page_number = page_number;
}
public String getFirst_edition_year() {
	return first_edition_year;
}
public void setFirst_edition_year(String first_edition_year) {
	this.first_edition_year = first_edition_year;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getBooks(){
	return DataAccess.viewData();
		
}

public String formPage() {
	return "form";
}

public String listPage() {
	return "list";
}



public String save(){
	if (bname.isEmpty() || author.isEmpty() || page_number.isEmpty() || 
		first_edition_year.isEmpty() || language.isEmpty() || category.isEmpty() ||
		publisher.isEmpty() || price.isEmpty()){
		status = "Please fill all of the form fields.";
	}
	else{
		if(DataAccess.saveData(bname, author, page_number, first_edition_year,language,category,publisher,price ))
			status = "Thank you!";
		else
			status = "An error occurred while processing your request.";
	}
	
	return null; //re-display page
}

	/*public String deleteUser() {
		DataAccess.deleteUser(getUsername());
		return "users";
	}*/

	public String deleteBooks(){
	DataAccess.deleteData(bname, author);
	return "BookList";
	//page_number, first_edition_year, language, category, publisher, price

	}

	public String GotoRemove() {
		return "RemoveBook" ;
	}
	
	/*public List<Users> getUserList(){
		return DataAccess.userList();
	}*/
}

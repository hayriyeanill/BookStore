package book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class Purchase extends Books {
  private String fullName;
  private String CreditCardNumber;
  private Date yearMonth;
  private String csv;
  private String purchaseAddress;
  private String message;
 
  
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getCreditCardNumber() {
	return CreditCardNumber;
}
public void setCreditCardNumber(String creditCardNumber) {
	CreditCardNumber = creditCardNumber;
}

public Date getYearMonth() {
	return yearMonth;
}
public void setYearMonth(Date yearMonth) {
	this.yearMonth = yearMonth;
}
public String getCsv() {
	return csv;
}
public void setCsv(String csv) {
	this.csv = csv;
}

public String getPurchaseAddress() {
	return purchaseAddress;
}
public void setPurchaseAddress(String purchaseAddress) {
	this.purchaseAddress = purchaseAddress;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}


public String getFormattedYearMonth() {
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
	return dateFormat.format(yearMonth);
}

  
public String BuyForm() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ResourceBundle rb = fc.getApplication().getResourceBundle(fc, "msgs");
	
	if(fullName.isEmpty() || CreditCardNumber.isEmpty() || getFormattedYearMonth().isEmpty() ||
			csv.isEmpty() || purchaseAddress.isEmpty()) {
		message= rb.getString("validationError"); 
	}
	else {
		message = rb.getString("success");
	}
	return null;
}

public String AddToBasket() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ResourceBundle rb = fc.getApplication().getResourceBundle(fc, "msgs");
	message = rb.getString("basketMessage");
	
	 
	return "ProductBasket"; //ProductBasket.xhtml
		
}

public String MyBooks() {
	 return "BookListForClient";
}
public String AddMore() {
	 return "UserHome";
}

}

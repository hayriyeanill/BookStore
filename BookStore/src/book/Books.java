package book;

public class Books {

	private String bname;
	   private String author;
	   private String page_number;
	   private String first_edition_year;
	   private String language;
	   private String category;
	   private String publisher;
	   private String price;
	   
	
	public Books(String bname, String author, String price) {
		super();
		this.bname = bname;
		this.author = author;
		this.price = price;
	}

	public Books(String bname) {
		this.bname = bname;
	}

	
	public Books() {
		
	}
	
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
	
	public String toString() {
		return this.bname;
	}
	   
	   
}

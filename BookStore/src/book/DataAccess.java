package book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataAccess {

	private static Connection getConnection() throws Exception{
		Class.forName(DbSettings.driver);
		String dbUrl = DbSettings.protocol + DbSettings.dbName + ";create=true";
		return DriverManager.getConnection(dbUrl, DbSettings.username, DbSettings.password);
	}
	
	public static boolean createDb() {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
	
			String sql = "CREATE TABLE Books (id INT NOT NULL PRIMARY KEY "
					   + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					   + "bname VARCHAR(50), author VARCHAR(50), page_number INTEGER,"
					   + "first_edition_year INTEGER, language VARCHAR(20), category VARCHAR(20), "
					   + "publisher VARCHAR(20), price DOUBLE )";
			
			String sql2 = "CREATE TABLE Users (user_id INT NOT NULL PRIMARY KEY " 
				  +	"GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
				  +	"fullname VARCHAR(25), email VARCHAR(50), password VARCHAR(50))";
					  
			statement.execute(sql);
			
			statement.execute(sql2);
	
			connection.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteBooks() {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = "DELETE FROM Books";

			statement.executeUpdate(sql);
	
			connection.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * public static boolean deleteUsers() { try { Connection connection =
	 * getConnection(); Statement statement = connection.createStatement(); String
	 * sql = "DELETE FROM Users";
	 * 
	 * statement.executeUpdate(sql);
	 * 
	 * connection.close(); return true;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return false; } }
	 */
	
	
	public static boolean saveData(String bname, String author, String page_number, String first_edition_year, String language, String category, String publisher, String price ){
		try{
			Connection connection = getConnection();
			String sql= "INSERT INTO Books(bname, author, page_number,first_edition_year,"
					+ "language,category,publisher,price) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, bname);
			statement.setString(2, author);
			statement.setString(3, page_number);
			statement.setString(4, first_edition_year);
			statement.setString(5, language);
			statement.setString(6, category);
			statement.setString(7, publisher);
			statement.setString(8, price);
			
			statement.executeUpdate(); 
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String viewData(){
		String result = "";
		
		try{
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Books";
	
			
			ResultSet rs = statement.executeQuery(query);		
			
			
			while(rs.next())
				result += String.format("(%s - %s, %s) <br/> ", rs.getString("author"), rs.getString("bname"), rs.getString("price"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean saveUserData(String fullName, String password, String email){
		try{
			Connection connection = getConnection();
			String sql = "INSERT INTO Users(fullname, password, email) VALUES(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, fullName);
			statement.setString(2, password);
			statement.setString(3,  email);
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String viewUserData(){
		String result = "";
			
		try{
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT fullname, email FROM Users";
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				result += String.format("%s - %s<br/><br/>", rs.getString("fullname"),
						                                        rs.getString("email"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean deleteData(String bname,String author) {
		try{
		Connection connection = getConnection();
		String sql = "DELETE FROM Books WHERE bname=? and author=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, bname);
		statement.setString(2, author);
		/*
		 * statement.setString(2, author); statement.setString(3, page_number);
		 * statement.setString(4, first_edition_year); statement.setString(5, language);
		 * statement.setString(6, category); statement.setString(7, publisher);
		 * statement.setString(8, price);
		 */
		statement.executeUpdate();
		connection.close();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
}
	
	public static boolean deleteUser(String email){
		try{
			Connection connection = getConnection();
			String sql = "DELETE FROM Users WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public static boolean isEmailSavedBefore(String email){
		boolean result = true;
		
		try{
			Connection connection = getConnection();
			String sql = "SELECT * FROM Users where email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) // E-mail found (already registered for another user).
				result = true;
			else
				result = false;
			
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean LoginCheck(String email, String password){
		boolean result = true;
		
		try{
			Connection connection = getConnection();
			String sql = "SELECT * FROM Users where email=? and password=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) 
				result = true;
			else
				result = false;
			
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<Books> BookList(){
		List<Books> result = new ArrayList<Books>();
		
		try{
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM Books";
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
				result.add(new Books(rs.getString("bname"), rs.getString("author"), rs.getString("price")));
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updateBookData(String author, String page_number, String first_edition_year, String language, String category,String publisher,String price, String bname ){
		try{
			Connection connection = getConnection();
			String sql = "UPDATE Books SET author=?, page_number=?, first_edition_year=?, language=?,"
					+ "category=?, publisher=?,price=? WHERE bname=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,author );
			statement.setString(2, page_number);
			statement.setString(3,  first_edition_year);
			statement.setString(4, language);
			statement.setString(5, category);
			statement.setString(6,  publisher);
			statement.setString(7, price);
			statement.setString(8, bname );
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Books findBooks(String bname){
		Books result = null;
		try{
			Connection connection = getConnection();
			String sql = "SELECT * FROM Books WHERE bname=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, bname);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = new Books(rs.getString("bname"), rs.getString("author"), rs.getString("price"));
			connection.close();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Users> UserList(){
		List<Users> result = new ArrayList<Users>();
		
		try{
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM Users";
			
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
				result.add(new Users(rs.getString("fullname"), rs.getString("password"), rs.getString("email")));
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updateUserData(String fullName, String password, String email1){
		try{
			Connection connection = getConnection();
			String sql = "UPDATE Users SET fullname=?, password=? WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, fullName);
			statement.setString(2, password);
			statement.setString(3,  email1);
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Users findUser(String email){
		Users result = null;
		try{
			Connection connection = getConnection();
			String sql = "SELECT * FROM Users WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = new Users(rs.getString("fullname"),  rs.getString("password"), rs.getString("email"));
			connection.close();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static List<Books> selectBook(){
		List<Books> result = new ArrayList<Books>();
		
		try{
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT bname, price FROM Books";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) 
				result.add(new Books(rs.getString("bname"), rs.getString("price")));
			connection.close();
			}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	
	
}


	




	

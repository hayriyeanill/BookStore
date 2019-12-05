package book;
import java.sql.*;
//import java.util.Date;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

//import book.Users;


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
			//Create a table named tblEmail
			String sql = "CREATE TABLE Books (id INT NOT NULL PRIMARY KEY "
					   + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					   + "bname VARCHAR(50), author VARCHAR(50), page_number INTEGER,"
					   + "first_edition_year INTEGER, language VARCHAR(20), category VARCHAR(20), "
					   + "publisher VARCHAR(20), price DOUBLE )";
					  
			/*
			 * String sql2 = "CREATE TABLE Users ((id INT NOT NULL PRIMARY KEY" +
			 * "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			 * "username VARCHAR(50), password VARCHAR(50))";
			 */
			statement.execute(sql);
		//	statement.execute(sql2);
			connection.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteAll(String bname) {
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
	
//, String author, String page_number, String first_edition_year, String language, String category, String publisher, String price ){
	
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
	
	public static boolean deleteUser(String username){
		try{
			Connection connection = getConnection();
			String sql = "DELETE FROM Users WHERE username=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static String viewData(){
		//ArrayList<String> result = new ArrayList<String>();
		String result = "";
		
		try{
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Books";
			System.out.println(query.toString());
			ResultSet rs = statement.executeQuery(query);			
			while(rs.next())
				result += String.format ("(%s - %s, %s) <br/> ", rs.getString("author"), rs.getString("bname"), rs.getString("price"));
			    
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	/*
	 * public static List<Users> userList(){ List<Users> result = new
	 * ArrayList<Users>();
	 * 
	 * try{ Connection connection = getConnection(); Statement statement =
	 * connection.createStatement(); String sql = "SELECT * FROM tblUser";
	 * 
	 * ResultSet rs = statement.executeQuery(sql); while(rs.next()) result.add(new
	 * Users(rs.getString("username"), rs.getString("password")));
	 * connection.close(); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return result; }
	 */
}
	

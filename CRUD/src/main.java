import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcURL = "jdbc:mysql://localhost:3306/sampledb";
		String username = "root";	
		String password = "Root";	
		
		do {
		
		try
        {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            
                System.out.println("Connected to the database");
                
                Scanner in = new Scanner(System.in); 
        		System.out.println("Please Select, Add, Delete, Update, View or Exit");
        		String input = in.nextLine();
        		input.toLowerCase();
        		if(input.equals("add")) {
        			 String sql = "INSERT INTO users (username, fullname, email)" 
                     		+ "VALUES (?, ?, ?)";
                    
                     PreparedStatement statement = connection.prepareStatement(sql);
                     
                     System.out.println("Please Enter Your Username");
                     input = in.nextLine();
                     statement.setString(1,input);
                     System.out.println("Please Enter Your Full Name");
                     input = in.nextLine();
                     statement.setString(2,input);
                     System.out.println("Please Enter Your Email");
                     input = in.nextLine();
                     statement.setString(3,input);
                     
                     int rows = statement.executeUpdate();
                    
                     if (rows > 0) {
                     	System.out.println("Successful add");
                     }
                     
                     
                     
        		}else if(input.equals("view")){
        		
        			System.out.println("view entered");
        			
        			String sql = "SELECT * FROM users";
        			Statement statement = connection.createStatement();
        			ResultSet result = statement.executeQuery(sql);
        			
        			while(result.next()) {
        				String userN = result.getString(2);
        				String fullN = result.getString(3);
        				String emailN = result.getString(4);
        				System.out.println("UserName: "+ userN + " Fullname: " + fullN +" Email: " + emailN);
        			}
        			
        		}else if(input.equals("update")){
        			System.out.println("Select an identifier type i.e username");
        			String feildSearch = in.nextLine();
        			System.out.println("Select a feild to update i.e email");
        			String feildUpdate = in.nextLine();
        			System.out.println("input your updated info, i.e your new email");
        			String newData = in.nextLine();
        			System.out.println("Enter your identifier ");
        			String searchData = in.nextLine();
 
        			String sql = "UPDATE users SET " + feildUpdate + " =?" + " WHERE " + feildSearch + " =?";
        			PreparedStatement statement = connection.prepareStatement(sql);
        			statement.setString(1, newData);
        			statement.setString(2, searchData);
        			
        			 int rows = statement.executeUpdate();
                     
                     if (rows > 0) {
                     	System.out.println("Successful updated!");
                     }
        			
        			
        		}else if(input.equals("delete")){
        			System.out.println("Please Enter The UserName of a Record you Would Like to Delete");
        			String userN = in.nextLine();
        			String sql = "DELETE FROM users WHERE username=?";
        			PreparedStatement statement = connection.prepareStatement(sql);
        			statement.setString(1, userN);
        			
        			 int rows = statement.executeUpdate();
                     
                     if (rows > 0) {
                     	System.out.println("Successful deleted");
                     }
        			
        			
        		}else if(input.equals("exit")){
        			System.out.println("Exiting system");
        			in.close();
        			break;
        		}else {
        			System.out.println("incorrect input!");
        		}
        		
        	
                connection.close();
            
        }
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
	
		}while(true);
		
		
	}

}

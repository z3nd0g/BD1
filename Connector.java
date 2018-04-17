import java.sql.*;

public class Connector {
    static final String DB_URL = "jdbc:mysql://localhost:3306/films";
    static final String USER = "root";
    static final String PASS = "";
    Connection conn = null;
    Statement stmt = null;  
    
    public Connector(){

        try{
           //Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //Open a connection
           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);

        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }
    }  
}

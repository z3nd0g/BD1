import java.io.*;
import java.sql.*;   

public class Query {

    public String text_file = "";

    
    public void execute_update(Connection c, String query) {
        try {
            System.out.println(query);
            Statement s = c.createStatement();
            s.executeUpdate(query);
            System.out.println("*************************query********************************");

            s.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }

    }

    
    public void execute_query(Connection c, String query) {
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query);
            System.out.println("*************************query********************************");

            rs.close();
            s.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void show_PICTURE(Connection c) {
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PICTURE;");
            System.out.println("*************************query********************************");

            FileWriter filewriter = new FileWriter("File3.txt");
            BufferedWriter writer = new BufferedWriter(filewriter);

            while(rs.next()){
               //by column name
               writer.write(Integer.toString(rs.getInt("PICTURE_ID")) + ", ");
               writer.write(Integer.toString(rs.getInt("PG_RATING")) + ", ");
               writer.write(rs.getString("ORIGINAL_LANGUAGE") + ", ");
               writer.write(rs.getString("TITLE") + ", ");
               writer.write(Integer.toString(rs.getInt("PRODUCTION_YEAR")) + ", ");
               writer.write(Integer.toString(rs.getInt("WORLDWIDE_GROSS")) + ", ");
               writer.write(Integer.toString(rs.getInt("BUDGET")) + ", ");
               writer.write(Integer.toString(rs.getInt("DIRECTOR_PERSON_PERSON_ID")) + ", ");
               writer.newLine();
             }
            writer.close();

            BufferedReader br = new BufferedReader(new FileReader("File3.txt"));

                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                text_file = sb.toString();

            //Clean-up environment
            br.close();
            rs.close();
            s.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            } 
    }


public void show_PERSON(Connection c) {
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PERSON;");
            System.out.println("*************************query********************************");

            FileWriter filewriter = new FileWriter("File3.txt");
            BufferedWriter writer = new BufferedWriter(filewriter);

            while(rs.next()){
               //Retrieve by column name
               writer.write(Integer.toString(rs.getInt("PERSON_ID")) + ", ");
               writer.write(rs.getString("FIRST_NAME") + ", ");
               writer.write(rs.getString("LAST_NAME") + ", ");
               writer.newLine();
 
            }
            writer.close();

            BufferedReader br = new BufferedReader(new FileReader("File3.txt"));

                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                text_file = sb.toString();

            //Clean-up environment
            br.close();
            rs.close();
            s.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            } 
    }
}


import java.io.*;
import java.sql.*;  
import java.util.ArrayList; 
import java.util.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class Query {

    private String text_file = null;
    public int number_of_columns = 0;  

    public String get_text_file() {return text_file;}    
    

    // USED FOR INSERT, UPDATE, DELETE QUERIES
    public void execute_update(Connection c, String query) {
        try {
            System.out.println(query);
            Statement s = c.createStatement();
            s.executeUpdate(query);
            System.out.println("*************************query********************************");
            s.close();
        }catch (SQLException e) {
                e.printStackTrace();
        }catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    // EXECUTES A QUERY, STORES RESULT IN A FILE AND DISPLAYS IT IN THE TEXTAREA, USED FOR TRANSACTIONS, SELECT QUERIES, TESTING ETC.
    public void execute_query(Connection c, String query) {
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query);
            System.out.println("*************************query********************************");
            
            ResultSetMetaData rsmd = rs.getMetaData();
            number_of_columns = rsmd.getColumnCount();

            FileWriter filewriter = new FileWriter("File3.txt");
            BufferedWriter writer = new BufferedWriter(filewriter);

            while (rs.next()) {         
                for(int i = 1 ; i <= number_of_columns; i++){
                    writer.write(rs.getString(i) + ", ");
                }
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
            s.close();

        }catch (SQLException e) {
                e.printStackTrace();
        }catch(IOException e) {
                e.printStackTrace();
        } 
    }

    
    // DISPLAYS TABLE PICTURE IN A NEW WINDOW    
    public void show_PICTURE(Connection c, String sql) {
        try {
            Stage stage = new Stage();

            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery(sql);
            System.out.println("*************************query********************************");

            final ObservableList<Picture> pictures = FXCollections.observableArrayList();  
                     
            while(rs.next()){
                Picture pic = new Picture();
                
                pic.setID(rs.getInt("PICTURE_ID"));
                pic.setPGRating(rs.getInt("PG_RATING"));
                pic.setLanguage(rs.getString("ORIGINAL_LANGUAGE"));
                pic.setTitle(rs.getString("TITLE"));
                pic.setProductionYear(rs.getInt("PRODUCTION_YEAR"));
                pic.setWorldwideGross(rs.getInt("WORLDWIDE_GROSS"));
                pic.setBudget(rs.getInt("BUDGET"));

                pictures.add(pic);
          
             }

            s.close();

            TableView table = new TableView();       
            table.setPrefHeight(280);
            table.setPrefWidth(800);

            table.setEditable(true);
       
            TableColumn col1 = new TableColumn("ID");
            col1.setCellValueFactory(new PropertyValueFactory<Picture,Integer>("ID"));
            
            TableColumn col2 = new TableColumn("PG Rating");
            col2.setCellValueFactory(new PropertyValueFactory<Picture,Integer>("PGRating"));

            TableColumn col3 = new TableColumn("Language");
            col3.setCellValueFactory(new PropertyValueFactory<Picture,String>("Language"));

            TableColumn col4 = new TableColumn("Title");      
            col4.setCellValueFactory(new PropertyValueFactory<Picture,String>("Title"));

            TableColumn col5 = new TableColumn("Production year");      
            col5.setCellValueFactory(new PropertyValueFactory<Picture,String>("ProductionYear"));

            TableColumn col6 = new TableColumn("Worldwide gross");      
            col6.setCellValueFactory(new PropertyValueFactory<Picture,String>("WorldwideGross"));

            TableColumn col7 = new TableColumn("Budget");      
            col7.setCellValueFactory(new PropertyValueFactory<Picture,String>("Budget"));
            
            table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);

            table.setItems(pictures);

            final VBox vbox = new VBox();
            vbox.getChildren().addAll(table);
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.show();

            } catch (SQLException e) {
                e.printStackTrace();
            } 
    }
    
    // DISPLAYS TABLE PERSON IN A NEW WINDOW
    public void show_persons(Connection c, String sql) {
        try {
            Stage stage = new Stage();

            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery(sql);
            System.out.println("*************************query********************************");

            final ObservableList<Person> persons = FXCollections.observableArrayList(); 
            
            while(rs.next()){
                Person pers = new Person();
                
                pers.setID(rs.getInt("PERSON_ID"));
                pers.setFirstName(rs.getString("FIRST_NAME"));
                pers.setLastName(rs.getString("LAST_NAME"));
                pers.setGender(rs.getString("GENDER"));
                //pers.setBirthDate(rs.getDate("BIRTH_DATE"));
                pers.setBirthPlace(rs.getString("BIRTH_PLACE"));
   
                persons.add(pers);
            }

            s.close();

            TableView table = new TableView();

            table.setEditable(true);
       
            TableColumn col1 = new TableColumn("ID");
            col1.setCellValueFactory(new PropertyValueFactory<Picture,Integer>("ID"));
            
            TableColumn col2 = new TableColumn("First name");
            col2.setCellValueFactory(new PropertyValueFactory<Picture,Integer>("FirstName"));

            TableColumn col3 = new TableColumn("Last name");
            col3.setCellValueFactory(new PropertyValueFactory<Picture,String>("LastName"));

            TableColumn col4 = new TableColumn("Gender");      
            col4.setCellValueFactory(new PropertyValueFactory<Picture,String>("Gender"));

            //TableColumn col5 = new TableColumn("Birth date");      
            //col5.setCellValueFactory(new PropertyValueFactory<Picture,String>("BirthDate"));

            TableColumn col6 = new TableColumn("Birth place");      
            col6.setCellValueFactory(new PropertyValueFactory<Picture,String>("BirthPlace"));
            
            table.getColumns().addAll(col1, col2, col3, col4, col6);

            table.setItems(persons);

            final VBox vbox = new VBox();
            vbox.getChildren().addAll(table);
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.show();

            } catch (SQLException e) {
                e.printStackTrace();
            } 
    }

    // DISPLAYS TABLE AWARD IN A NEW WINDOW
    public void show_awards(Connection c, String sql) {
        try {
            Stage stage = new Stage();

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            System.out.println("*************************query********************************");

            final ObservableList<Award> awards = FXCollections.observableArrayList(); 
            
            while(rs.next()){
                Award awa = new Award();
                
                awa.setAwardID(rs.getInt("AWARD_ID"));
                awa.setName(rs.getString("NAME"));
   
                awards.add(awa);
            }

            s.close();

            TableView table = new TableView();
            table.setEditable(true);
       
            TableColumn col1 = new TableColumn("ID");
            col1.setCellValueFactory(new PropertyValueFactory<Award,Integer>("AwardID"));
            
            TableColumn col2 = new TableColumn("Name");
            col2.setCellValueFactory(new PropertyValueFactory<Award,String>("Name"));
            
            table.getColumns().addAll(col1, col2);

            table.setItems(awards);

            final VBox vbox = new VBox();
            //vbox.setSpacing(5);
            //vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(table);
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.show();

            } catch (SQLException e) {
                e.printStackTrace();
            } 
    }
}


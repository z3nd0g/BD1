import javafx.application.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import java.sql.*;
import java.io.*;
import javafx.collections.FXCollections;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Connector c = new Connector();
        Query q = new Query();
        String transaction1 = "INSERT INTO PICTURE VALUES (16, 13, 'ENGLISH', 'TERMINATOR 5', 2018, 1190515681, 200000000, TRUE, 3);";
        String transaction2 = "INSERT INTO STARS_IN VALUES (11, 1666, 'Sarah');";     
        String Transaction_query = "SELECT PERSON.PERSON_ID, PERSON.FIRST_NAME, PERSON.LAST_NAME, STARS_IN.CHARACTER, PICTURE.TITLE FROM PERSON, STARS_IN, PICTURE WHERE PERSON.PERSON_ID = STARS_IN.ACTOR_PERSON_PERSON_ID AND STARS_IN.PICTURE_PICTURE_ID = PICTURE.PICTURE_ID";   
    
        Text t1 = new Text(10, 20, "Show table: ");  
        t1.setFont(Font.font ("Verdana", 16));

        Text t2 = new Text(150, 20, "Add record: ");  
        t2.setFont(Font.font ("Verdana", 16));

        Text t3 = new Text(1120, 20, "Delete record: ");  
        t3.setFont(Font.font ("Verdana", 16));

        Text t4 = new Text(10, 340, "Result: ");  
        t4.setFont(Font.font ("Verdana", 16));

        TextArea textArea = new TextArea();
        textArea.setLayoutX(10);
        textArea.setLayoutY(360);        
        textArea.setPrefHeight(280);
        textArea.setPrefWidth(800); 

        TextArea trans1 = new TextArea(transaction1);
        trans1.setWrapText(true);
        trans1.setLayoutX(850);
        trans1.setLayoutY(360);        
        trans1.setPrefHeight(40);
        trans1.setPrefWidth(500);

        TextArea trans2 = new TextArea(transaction2);
        trans2.setWrapText(true);
        trans2.setLayoutX(850);
        trans2.setLayoutY(420);        
        trans2.setPrefHeight(40);
        trans2.setPrefWidth(500);

        TextArea textArea_query = new TextArea(Transaction_query);
        textArea_query.setWrapText(true);
        textArea_query.setLayoutX(850);
        textArea_query.setLayoutY(520);        
        textArea_query.setPrefHeight(120);
        textArea_query.setPrefWidth(500);

        Button btn_picture = new Button("PICTURE");
        btn_picture.setLayoutX(10);
        btn_picture.setLayoutY(40);        

        btn_picture.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            //q.show_PICTURE(c.conn);
            String sql = "SELECT * FROM PICTURE";
            q.execute_query(c.conn, sql);
            textArea.setText(q.text_file);         
            }         
        });

        Button btn_person = new Button("PERSON");
        btn_person.setLayoutX(10);
        btn_person.setLayoutY(80);

        btn_person.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //q.show_PERSON(c.conn);
                String sql = "SELECT * FROM PERSON";
                q.execute_query(c.conn, sql);
                textArea.setText(q.text_file);
            }
        });
     
          

        Button btn_award = new Button("AWARD");
        btn_award.setLayoutX(10);
        btn_award.setLayoutY(160);

        btn_award.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String sql = "SELECT * FROM AWARD";
                q.execute_query(c.conn, sql);
                textArea.setText(q.text_file); 
            }
        });

        Button btn_director = new Button("DIRECTOR");
        btn_director.setLayoutX(10);
        btn_director.setLayoutY(120);

        btn_director.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //q.show_PERSON(c.conn);
                String sql = "SELECT * FROM PERSON, DIRECTOR WHERE PERSON_ID = PERSON_PERSON_ID";
                q.execute_query(c.conn, sql);
                textArea.setText(q.text_file);
            }
        });

        Button btn_clear = new Button("Clear Text Area");
        btn_clear.setLayoutX(10);
        btn_clear.setLayoutY(660);


     
        btn_clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.clear();
            }
        });

        Button btn_transaction = new Button("Transaction");
        btn_transaction.setLayoutX(850);
        btn_transaction.setLayoutY(480);

       btn_transaction.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try{
                   c.conn.setAutoCommit(false);
                   Statement stmt = c.conn.createStatement();

                   String sql = trans1.getText();
                   stmt.executeUpdate(sql);  

                   sql = trans2.getText();
                   stmt.executeUpdate(sql);               
                   // no error.
                   c.conn.commit();
                   textArea.setText("Transaction executed."); 
                   stmt.close();                   
                   c.conn.setAutoCommit(true);
                }catch(SQLException se){
                   // error
                   try{
                      c.conn.rollback();
                      textArea.setText("Transaction rolled back");
                   }
                   catch(SQLException se2){
                }
                }

            }
        });

        Button btn_query = new Button("Execute Query");
        btn_query.setLayoutX(850);
        btn_query.setLayoutY(660);


        btn_query.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String text_field_query = textArea_query.getText();
                q.execute_query(c.conn, text_field_query);
                //textArea.setText("Query executed."); 
                textArea.setText(q.text_file);
            }
        });
      

        ChoiceBox cb1 = new ChoiceBox(FXCollections.observableArrayList("SELECT", "INSERT INTO"));
        ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList("ALL", "FIRST_NAME", "LAST_NAME"));
        ChoiceBox cb3 = new ChoiceBox(FXCollections.observableArrayList("FROM"));
        ChoiceBox cb4 = new ChoiceBox(FXCollections.observableArrayList("PICTURE", "PERSON", "AWARD"));
        ChoiceBox cb6 = new ChoiceBox(FXCollections.observableArrayList("PICTURE", "PERSON", "AWARD"));
        ChoiceBox cb7 = new ChoiceBox(FXCollections.observableArrayList("WHERE"));
        ChoiceBox cb8 = new ChoiceBox(FXCollections.observableArrayList("PERSON_ID", "PERSON_PERSON_ID"));
        ChoiceBox cb9 = new ChoiceBox(FXCollections.observableArrayList("=", ">", "<"));
        ChoiceBox cb10 = new ChoiceBox(FXCollections.observableArrayList("PERSON_ID", "PERSON_PERSON_ID"));

        TextField picture_id = new TextField ();
            picture_id.setPromptText("PICTURE_ID");
            picture_id.setPrefColumnCount(6);
        TextField rating = new TextField ();
            rating.setPromptText("PG_RATING");
            rating.setPrefColumnCount(4);
        TextField language = new TextField ();
            language.setPromptText("LANGUAGE");
            language.setPrefColumnCount(7);
        TextField title = new TextField ();
            title.setPromptText("TITLE");
            title.setPrefColumnCount(7);
        TextField year = new TextField ();
            year.setPromptText("YEAR");
            year.setPrefColumnCount(4);
        TextField gross = new TextField ();
            gross.setPromptText("GROSS");
            gross.setPrefColumnCount(7);
        TextField budget = new TextField ();
            budget.setPromptText("BUDGET");
            budget.setPrefColumnCount(7);
        TextField color = new TextField ();
            color.setPromptText("COLOR");
            color.setPrefColumnCount(4);
        TextField director_id = new TextField ();
            director_id.setPromptText("DIRECTOR_ID");
            director_id.setPrefColumnCount(7);
        
        Button add = new Button("Add");

       add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {                       
                String query_add = "INSERT INTO PICTURE VALUES(" + picture_id.getText() + ", " + rating.getText() + ", '" + language.getText() + "', '" + title.getText() + "', " + year.getText() + ", " + gross.getText() + ", " + budget.getText() + ", " + color.getText() + ", " + director_id.getText()+")";
            
                q.execute_update(c.conn, query_add);        
            }
                
        });

        TextField pic_id = new TextField ();
            pic_id.setPromptText("PICTURE_ID");
            pic_id.setPrefColumnCount(6);
        
        Button delete = new Button("Delete");

        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String query_delete = "DELETE FROM PICTURE WHERE PICTURE_ID = " + pic_id.getText();
                q.execute_update(c.conn, query_delete);
            }
        }); 

        HBox hb = new HBox();
        hb.getChildren().addAll(picture_id, rating, language, title, year, gross, budget, color, director_id, add, pic_id, delete);
        hb.setSpacing(10);
        hb.setLayoutX(150);
        hb.setLayoutY(40);

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(cb1, cb2, cb3, cb4, cb6, cb7, cb8, cb9, cb10);
        hb2.setSpacing(10);
        hb2.setLayoutX(10);
        hb2.setLayoutY(280);
     

        stage.setTitle("DB");
        Group root = new Group();
        root.getChildren().addAll(t1, t2, t3, t4, btn_picture, btn_person, btn_clear, btn_director, btn_award, btn_transaction, btn_query, textArea, trans1, trans2, textArea_query, hb, hb2);

        Scene scene = new Scene(root, 1350, 700);
        scene.setFill(Color.GREY);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);
    }
}

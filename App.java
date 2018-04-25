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
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.value.*;
import javafx.beans.property.ReadOnlyIntegerProperty;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        Connector c = new Connector();
        Query q = new Query();  
        

        //TRANSACTIONS AND TEXT AREA

        String transaction1 = "INSERT INTO PICTURE VALUES (16, 13, 'ENGLISH', 'TERMINATOR 5', 2018, 1190515681, 200000000, TRUE, 3);";
        String transaction2 = "INSERT INTO STARS_IN VALUES (11, 1666, 'Sarah');";     
        String Transaction_query = "SELECT PERSON.PERSON_ID, PERSON.FIRST_NAME, PERSON.LAST_NAME, STARS_IN.CHARACTER, PICTURE.TITLE FROM PERSON, STARS_IN, PICTURE WHERE PERSON.PERSON_ID = STARS_IN.ACTOR_PERSON_PERSON_ID AND STARS_IN.PICTURE_PICTURE_ID = PICTURE.PICTURE_ID"; 

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
                textArea.setText(q.get_text_file());
            }
        });

        // FIRST ROW

        Text t1 = new Text(10, 20, "Show table:");  
        t1.setFont(Font.font ("Verdana", 16));

        Text t2 = new Text(150, 20, "Add / Delete record:");  
        t2.setFont(Font.font ("Verdana", 16));

        //Text t3 = new Text(1120, 20, "Delete record:");  
        //t3.setFont(Font.font ("Verdana", 16));

        Text t4 = new Text(10, 340, "Result:");  
        t4.setFont(Font.font ("Verdana", 16));

        Text t5 = new Text(10, 230, "Create a View:");  
        t4.setFont(Font.font ("Verdana", 16));

        Button btn_picture = new Button("PICTURE");
        btn_picture.setLayoutX(10);
        btn_picture.setLayoutY(40);        
        btn_picture.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            String sql = "SELECT * FROM PICTURE";
            q.show_PICTURE(c.conn, sql);        
            }         
        });

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
        
        Button add_picture = new Button("Add");
        add_picture.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {                       
                String sql = "INSERT INTO PICTURE VALUES(" + picture_id.getText() + ", " + rating.getText() + ", '" + language.getText() + "', '" + title.getText() + "', " + year.getText() + ", " + gross.getText() + ", " + budget.getText() + ", " + color.getText() + ", " + director_id.getText()+")";
                System.out.println(sql);
                q.execute_update(c.conn, sql);        
            }             
        });

        Button modify_picture = new Button("Modify");
        modify_picture.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {                       
                String sql = "UPDATE PICTURE SET ";
                if (rating.getText() != null)                
                    sql += "PG_RATING = '" + rating.getText() +"'";
                if (language.getText() != null)                
                    sql += "ORIGINAL_LANGUAGE = '" + language.getText() +"'";
                if (title.getText() != null)                
                    sql += "TITLE = '" + title.getText() +"'";
                if (year.getText() != null)                
                    sql += "PRODUCTION_YEAR = " + year.getText();
                if (gross.getText() != null)                
                    sql += "WORLDWIDE_GROSS = " + gross.getText();
                if (budget.getText() != null)                
                    sql += "BUDGET = " + budget.getText();
                if (color.getText() != null)                
                    sql += "COLOR = " + color.getText();
                if (director_id.getText() != null)                
                    sql += "DIRECTOR_ID = " + director_id.getText();
                
                sql += " WHERE PICTURE_ID = " + picture_id.getText();
    
                q.execute_update(c.conn, sql);  
            }              
        });
        
        TextField pic_id = new TextField ();
            pic_id.setPromptText("PICTURE_ID");
            pic_id.setPrefColumnCount(6);
        
        Button delete_picture = new Button("Delete");
        delete_picture.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String query_delete = "DELETE FROM PICTURE WHERE PICTURE_ID = " + pic_id.getText();
                q.execute_update(c.conn, query_delete);
            }
        }); 

        HBox hb = new HBox();
        hb.getChildren().addAll(picture_id, rating, language, title, year, gross, budget, color, director_id, add_picture, modify_picture, pic_id, delete_picture);
        hb.setSpacing(10);
        hb.setLayoutX(150);
        hb.setLayoutY(40);

        //SECOND ROW

        Button btn_person = new Button("PERSON");
        btn_person.setLayoutX(10);
        btn_person.setLayoutY(80);
        btn_person.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String sql = "SELECT * FROM PERSON";
                q.show_persons(c.conn, sql);
            }
        });

        TextField person_id = new TextField ();
            person_id.setPromptText("PERSON_ID");
            person_id.setPrefColumnCount(6);
        TextField fname = new TextField ();
            fname.setPromptText("FIRST_NAME");
            fname.setPrefColumnCount(7);
        TextField lname = new TextField ();
            lname.setPromptText("LAST_NAME");
            lname.setPrefColumnCount(7);
        TextField gender = new TextField ();
            gender.setPromptText("GENDER");
            gender.setPrefColumnCount(7);
        TextField b_date = new TextField ();
            b_date.setPromptText("BIRTH_DATE");
            b_date.setPrefColumnCount(7);
        TextField b_place = new TextField ();
            b_place.setPromptText("BIRTH_PLACE");
            b_place.setPrefColumnCount(7);
        
        Button add_person = new Button("Add");
        add_person.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {                       
                String query_add = "INSERT INTO PERSON VALUES(" + person_id.getText() + ", '" + fname.getText() + "', '" + lname.getText() + "', '" + gender.getText() + "', STR_TO_DATE('" + b_date.getText() + "', '%d-%m-%Y'), '" + b_place.getText() + "')";
            
                q.execute_update(c.conn, query_add);        
            }             
        });       

        Button modify_person = new Button("Modify");
        modify_person.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {                       
                String sql = "UPDATE PERSON SET ";
                //String s;
                if (!fname.getText().isEmpty())                
                    sql += "FIRST_NAME = '" + fname.getText() +"', ";
                if (!lname.getText().isEmpty())              
                    sql += "LAST_NAME = '" + lname.getText() +"'";
                if (!gender.getText().isEmpty()) {
                    if (!lname.getText().isEmpty())
                        sql += ", ";
                    sql += "GENDER = '" + gender.getText() +"'";
                }
                if (!b_date.getText().isEmpty()) {
                    if (!lname.getText().isEmpty() || !gender.getText().isEmpty())
                        sql += ", ";        
                    sql += "BIRTH_DATE = STR_TO_DATE('" + b_date.getText() + "', '%d-%m-%Y')'";
                }
                if (!b_place.getText().isEmpty()) {
                    if (!lname.getText().isEmpty() || !gender.getText().isEmpty() || !b_date.getText().isEmpty())
                        sql += ", ";           
                    sql += "BIRTH_PLACE = '" + b_place.getText() +"'";
                }

                sql += " WHERE PERSON_ID = " + person_id.getText();
    
                q.execute_update(c.conn, sql);  
            }              
        }); 

        TextField pers_id = new TextField ();
            pers_id.setPromptText("PERSON_ID");
            pers_id.setPrefColumnCount(6);
        
        Button delete_person = new Button("Delete");
        delete_person.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String query_delete = "DELETE FROM PERSON WHERE PERSON_ID = " + pers_id.getText();
                q.execute_update(c.conn, query_delete);
            }
        }); 

        HBox hb_second_row = new HBox();
        hb_second_row.getChildren().addAll(person_id, fname, lname, gender, b_date, b_place, add_person, modify_person, pers_id, delete_person);
        hb_second_row.setSpacing(10);
        hb_second_row.setLayoutX(150);
        hb_second_row.setLayoutY(80);
    

       // THIRD ROW
     
        Button btn_award = new Button("AWARD");
        btn_award.setLayoutX(10);
        btn_award.setLayoutY(120);
        btn_award.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String sql = "SELECT * FROM AWARD";
                q.show_awards(c.conn, sql);
                //String sql = "SELECT * FROM AWARD";
                //q.execute_query(c.conn, sql);
                //textArea.setText(q.get_text_file()); 
            }
        });

        TextField award_id = new TextField ();
            award_id.setPromptText("AWARD_ID");
            award_id.setPrefColumnCount(6);
        TextField aw_name = new TextField ();
            aw_name.setPromptText("NAME");
            aw_name.setPrefColumnCount(7);
   
        
        Button add_award = new Button("Add");
        add_award.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {                       
                String query_add = "INSERT INTO AWARD VALUES(" + award_id.getText() + ", '" + aw_name.getText() + "')";
            
                q.execute_update(c.conn, query_add);        
            }             
        });

        Button modify_award = new Button("Modify");
        modify_award.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {                       
                String sql = "UPDATE AWARD SET ";
                if (aw_name.getText() != null)                
                    sql += "NAME = '" + aw_name.getText() +"'";
                
                sql += " WHERE AWARD_ID = " + award_id.getText();
    
                q.execute_update(c.conn, sql);  
            }              
        }); 

        TextField aw_id = new TextField ();
            aw_id.setPromptText("AWARD_ID");
            aw_id.setPrefColumnCount(6);
        
        Button delete_award = new Button("Delete");
        delete_award.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String query_delete = "DELETE FROM AWARD WHERE AWARD_ID = " + aw_id.getText();
                q.execute_update(c.conn, query_delete);
            }
        }); 

        HBox hb_third_row = new HBox();
        hb_third_row.getChildren().addAll(award_id, aw_name, add_award, modify_award, aw_id, delete_award);
        hb_third_row.setSpacing(10);
        hb_third_row.setLayoutX(150);
        hb_third_row.setLayoutY(120);


        // VIEWS - PICTURE

        String[] pg_ratings = {"'13'", "'18'"};        
        ChoiceBox cb1    = new ChoiceBox(FXCollections.observableArrayList("13", "18"));
        cb1.getSelectionModel().selectedIndexProperty().addListener(new 
            ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {}
            });
        
        String[] languages = {"ENGLISH", "POLISH", "SWEDISH", "PONGLISH"};
        ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList("English", "Polish", "Swedish", "Ponglish"));      
        cb2.getSelectionModel().selectedIndexProperty().addListener(new 
            ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {
                }
            });
        
        String prod_years[] = {"PRODUCTION_YEAR >= 2000", "PRODUCTION_YEAR >= 1980 AND PRODUCTION_YEAR <= 1999"};
        ChoiceBox cb3 = new ChoiceBox(FXCollections.observableArrayList("2000-2017", "1980-1999", "1960-1979"));
        cb1.getSelectionModel().selectedIndexProperty().addListener(new 
            ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {}
            });
        
        //ChoiceBox cb4 = new ChoiceBox(FXCollections.observableArrayList("<$1m", "$1m-$10m", "$11m-$20m", ">$20m"));

        String budgets[] = {"BUDGET < 1000000", "BUDGET >= 1000000 AND BUDGET <100000000", "BUDGET > 100000000"};
        ChoiceBox cb6 = new ChoiceBox(FXCollections.observableArrayList("<$10m", "$10m-$100m", ">$100m"));
        cb6.getSelectionModel().selectedIndexProperty().addListener(new 
            ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {}
            });     

       // ChoiceBox cb7 = new ChoiceBox(FXCollections.observableArrayList("Color", "Black and white"));
       // ChoiceBox cb8 = new ChoiceBox(FXCollections.observableArrayList("Guillermo", "Greta", "Ryan", "Taika"));
       // ChoiceBox cb9 = new ChoiceBox(FXCollections.observableArrayList("Chadwick", "Lupita", "Sally", "Doug"));
       // ChoiceBox cb10 = new ChoiceBox(FXCollections.observableArrayList("Action", "Drama", "Sci-fi", "Romance", "Fantasy"));
       // ChoiceBox cb11 = new ChoiceBox(FXCollections.observableArrayList("US", "UK", "Canada", "New Zealand"));

        Button btn_view = new Button("Create PICTURE View");      
        btn_view.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            String sql = "SELECT * FROM PICTURE WHERE ";
            int i1 = cb1.getSelectionModel().selectedIndexProperty().get();
            int i2 = cb2.getSelectionModel().selectedIndexProperty().get();
            int i3 = cb3.getSelectionModel().selectedIndexProperty().get();
            int i6 = cb6.getSelectionModel().selectedIndexProperty().get();

            if (i1 != -1){
                sql = sql + "PG_RATING = " + pg_ratings[i1];
            }
            if (i2 != -1){
                if (i1 != -1)
                    sql = sql + " AND ";
                sql = sql + "ORIGINAL_LANGUAGE = '" + languages[i2] + "'";
            }
            if (i3 != -1) {
                if (i1 != -1 || i2!=-1)
                    sql = sql + " AND ";
                sql = sql + prod_years[i3];
            }
            if (i6 != -1) {
                if (i1 != -1 || i2!=-1 || i3!=-1)
                    sql = sql + " AND ";
                sql = sql + budgets[i6];
            }

            q.show_PICTURE(c.conn, sql);            
            System.out.println(sql);            
            }         
        });

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(cb1, cb2, cb3, cb6, btn_view);
        hb2.setSpacing(10);
        hb2.setLayoutX(10);
        hb2.setLayoutY(200);

        // VIEWS - PERSON
 
        ChoiceBox cb_pers_1 = new ChoiceBox(FXCollections.observableArrayList("Actor", "Director"));
        cb_pers_1.getSelectionModel().selectedIndexProperty().addListener(new 
            ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {}
            }); 
        
        String[] genders = {"'F'", "'M'"};        
        ChoiceBox cb_pers_2    = new ChoiceBox(FXCollections.observableArrayList("Female", "Male"));
        cb_pers_2.getSelectionModel().selectedIndexProperty().addListener(new 
            ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {}
            }); 

        String[] countries = {"USA", "UK", "NEW ZEALAND", "CANADA", "MEXICO", "Poland", "AUSTRALIA"};        
        ChoiceBox cb_pers_3    = new ChoiceBox(FXCollections.observableArrayList("USA", "UK", "New Zealand", "Canada", "Mexico", "Poland", "Australia", "China"));
        cb_pers_3.getSelectionModel().selectedIndexProperty().addListener(new 
            ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {}
            }); 


        Button btn_view_2 = new Button("Create PERSON View");       
        btn_view_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            String sql = null;
            int i_pers_1 = cb_pers_1.getSelectionModel().selectedIndexProperty().get();
            int i_pers_2 = cb_pers_2.getSelectionModel().selectedIndexProperty().get();
            int i_pers_3 = cb_pers_3.getSelectionModel().selectedIndexProperty().get();

             if (i_pers_1 == 1){
                sql = "SELECT * FROM PERSON, DIRECTOR WHERE PERSON_ID = PERSON_PERSON_ID";   
            }
            else if (i_pers_1 == 0){
                sql = "SELECT * FROM PERSON, ACTOR WHERE PERSON_ID = PERSON_PERSON_ID";   
            }
            else
                sql = "SELECT * FROM PERSON WHERE ";
            
            if (i_pers_2 != -1){
                if (i_pers_1 != -1)
                    sql = sql + " AND ";
                    sql = sql + "GENDER = " + genders[i_pers_2];
            }

            if (i_pers_3 != -1) {
                if (i_pers_1 != -1 || i_pers_2!=-1)
                    sql = sql + " AND ";
                sql = sql + "BIRTH_PLACE = '" + countries[i_pers_3] + "'";
            }

            q.show_persons(c.conn, sql);            
            System.out.println(sql);            
            }         
        });
        
        HBox hb_views = new HBox();
        hb_views.getChildren().addAll(cb_pers_1, cb_pers_2, cb_pers_3, btn_view_2);
        hb_views.setSpacing(10);
        hb_views.setLayoutX(10);
        hb_views.setLayoutY(240);
     
        // PRIMARY STAGE

        primaryStage.setTitle("DB");
        Group root = new Group();
        root.getChildren().addAll(t1, t2, btn_picture, btn_person, btn_clear, btn_award, btn_transaction, btn_query, trans1, trans2, textArea_query, hb, hb2, hb_second_row, hb_third_row, hb_views, textArea);

        Scene scene = new Scene(root, 1350, 700);
        scene.setFill(Color.GREY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);
    }
}

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

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Connector c = new Connector();
        Query q = new Query();
        Picture pic = new Picture();       
    
        Text t1 = new Text(10, 20, "Show table: ");  
        t1.setFont(Font.font ("Verdana", 16));

        Text t2 = new Text(150, 20, "Add record: ");  
        t2.setFont(Font.font ("Verdana", 16));

        Text t3 = new Text(1120, 20, "Delete record: ");  
        t3.setFont(Font.font ("Verdana", 16));

        Text t4 = new Text(10, 340, "Result: ");  
        t4.setFont(Font.font ("Verdana", 16));

        Button btn_2 = new Button("PICTURE");
        btn_2.setLayoutX(10);
        btn_2.setLayoutY(40);        

        Button btn_1 = new Button("PERSON");
        btn_1.setLayoutX(10);
        btn_1.setLayoutY(80);
     
        Button btn_4 = new Button("DIRECTOR");
        btn_4.setLayoutX(10);
        btn_4.setLayoutY(120);

        Button btn_5 = new Button("AWARD");
        btn_5.setLayoutX(10);
        btn_5.setLayoutY(160);

        Button btn_3 = new Button("Clear Text Area");
        btn_3.setLayoutX(10);
        btn_3.setLayoutY(660);

        TextArea textArea = new TextArea();
        textArea.setLayoutX(10);
        textArea.setLayoutY(360);        
        textArea.setPrefHeight(280);
        textArea.setPrefWidth(800); 

        btn_1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                q.show_PERSON(c.conn); 
                textArea.setText(q.text_file);
            }
        });

        btn_2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                q.show_PICTURE(c.conn);
                textArea.setText(q.text_file);         
            }
                
        });

        btn_3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.clear();
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
        Button add = new Button("Add");

        TextField pic_id = new TextField ();
            pic_id.setPromptText("PICTURE_ID");
            pic_id.setPrefColumnCount(6);
        Button delete = new Button("Delete");

        HBox hb = new HBox();
        hb.getChildren().addAll(picture_id, rating, language, title, year, gross, budget, color, director_id, add, pic_id, delete);
        hb.setSpacing(10);
        hb.setLayoutX(150);
        hb.setLayoutY(40);

        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                       
                String query_add = "INSERT INTO PICTURE VALUES(" + picture_id.getText() + ", " + rating.getText() + ", '" + language.getText() + "', '" + title.getText() + "', " + year.getText() + ", " + gross.getText() + ", " + budget.getText() + ", " + color.getText() + ", " + director_id.getText()+")";
            
                q.execute_update(c.conn, query_add);
                /*pic.setID(Integer.parseInt(picture_id.getText()));
                pic.setPGRating(Integer.parseInt(rating.getText()));
                pic.setLanguage(language.getText());
                pic.setTitle(title.getText());
                pic.setProductionYear(Integer.parseInt(year.getText()));
                pic.setWorldwideGross(Integer.parseInt(gross.getText()));
                ...
                */        
            }
                
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String query_delete = "DELETE FROM PICTURE WHERE PICTURE_ID = " + pic_id.getText();
                q.execute_update(c.conn, query_delete);
            }
        });

        stage.setTitle("DB");
        Group root = new Group();
        root.getChildren().addAll(t1, t2, t3, t4, btn_2, btn_1, btn_3, btn_4, btn_5, textArea, hb);

        Scene scene = new Scene(root, 1350, 700);
        scene.setFill(Color.GREY);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);
    }
}

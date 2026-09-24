package com.example.hellofx;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class HelloJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        Label message = new Label("Welcome, Shepherd Sampa!");
        Button button = new Button("Start");
        Button button2 = new Button("Reset");
        button.setOnAction(event ->
                message.setText("Hi Everyone")
        );
        button2.setOnAction(event->
                message.setText("Welcome, Shepherd Sampa!")
        );
        HBox buttons = new HBox(10, button, button2);
        buttons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(message, buttons);
        Scene scene = new Scene(layout, 500, 300);
        stage.setTitle("My First JavaFX Application, 202500717");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {

        launch(args);
    }
}
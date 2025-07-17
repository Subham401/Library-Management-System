package com.example.library_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Library_Management_System extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Library_Management_System.class.getResource("Interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600.0, 400.0);
        stage.setTitle("Library Management System!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        ConnectionClass.getConnection();

        launch();
    }
}
package com.example.library_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckBook {

    Parent root;
    Stage stage;
    Scene scene;


    public void searchByTitle(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("byTitle.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchByAuthor(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("byAuthor.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchByPublisher(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("byPublisher.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

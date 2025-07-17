package com.example.library_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class InterfaceController {
    @FXML

    Parent root;
    Scene scene;
    Stage stage;



    public void switchToMemberPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Member.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void addBook(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("bookDetails.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void checkBook(ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("checkBook.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void issueBook(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("issueBook.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnBook(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("returnBook.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
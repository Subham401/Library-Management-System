package com.example.library_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AddOrRemoveMember {

    Parent root;
    Stage stage;
    Scene scene;

    String addname,addmail,removename,removemail,addcontact,removecontact;

    @FXML
    private TextField fullNameAdd;
    @FXML
    private TextField emailAdd;
    @FXML
    private TextField contactAdd;
    @FXML
    private TextField fullNameRemove;
    @FXML
    private TextField emailRemove;
    @FXML
    private TextField contactRemove;

    public void addMember(ActionEvent actionEvent) {
        addname = fullNameAdd.getText();
        addmail = emailAdd.getText();
        addcontact = contactAdd.getText();

        String query = "INSERT INTO member(name,email,phone) VALUES(?,?,?)";
        try{

            Connection connection = ConnectionClass.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            if(!memberExists(connection,addmail)){
                statement.setString(1,addname);
                statement.setString(2,addmail);
                statement.setString(3,addcontact);

                int noOfRowsAffected = statement.executeUpdate();
                if(noOfRowsAffected > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Member Registered Successfully!!!");
                    alert.showAndWait();

                    fullNameAdd.clear();
                    emailAdd.clear();
                    contactAdd.clear();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Something went Wrong!!! Please Check Details and try again Later!!!");
                    alert.showAndWait();
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Member already Registered!!!");
                alert.showAndWait();

                fullNameAdd.clear();
                emailAdd.clear();
                contactAdd.clear();
            }
            statement.close();
            connection.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void removeMember(ActionEvent actionEvent) {

        removename = fullNameRemove.getText();
        removemail = emailRemove.getText();
        removecontact = contactRemove.getText();

        String query = "DELETE FROM member WHERE email = ?";

        try{
            Connection connection = ConnectionClass.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            if(memberExists(connection,removemail)){

                statement.setString(1,removemail);

                int noOfRowsAffected = statement.executeUpdate();

                if(noOfRowsAffected > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Member Removed Successfully!!!");
                    alert.showAndWait();

                    fullNameRemove.clear();
                    emailRemove.clear();
                    contactRemove.clear();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Something went Wrong!!! Please Check Details and try again Later!!!");
                    alert.showAndWait();
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Member is not Registered!!!");
                alert.showAndWait();

                fullNameRemove.clear();
                emailRemove.clear();
                contactRemove.clear();
            }

            statement.close();
            connection.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    private boolean memberExists(Connection connection,String email){

        String query = "SELECT * from member WHERE email = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,email);

            ResultSet set = statement.executeQuery();

            return set.next();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Member.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goHome(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

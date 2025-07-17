package com.example.library_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAdd {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TextField noOfCopiesField;
    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField bookAuthorField;
    @FXML
    private TextField bookPublisherField;
    @FXML
    private Button addBookButton;

    int copies;
    String title,author,publisher;


    public void addbook(ActionEvent actionEvent) {
        title = bookTitleField.getText();
        author = bookAuthorField.getText();
        publisher = bookPublisherField.getText();
        copies = Integer.parseInt(noOfCopiesField.getText());

        String query = "INSERT INTO book(Title,Author,Publisher,Available_copies) VALUES(?,?,?,?)";
        try {
            Connection connection = ConnectionClass.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            if(isBookPresent(connection,title,author,publisher)){

                String newQuery = "UPDATE book SET available_copies = available_copies + ? WHERE Title = ? AND Author = ? AND Publisher = ?";

                PreparedStatement statement1 = connection.prepareStatement(newQuery);

                statement1.setInt(1,copies);
                statement1.setString(2,title);
                statement1.setString(3,author);
                statement1.setString(4,publisher);

                int noOfRowsAffected = statement1.executeUpdate();
                if(noOfRowsAffected > 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Book Already exist! Number of copies increased!!");
                    alert.showAndWait();

                    bookTitleField.clear();
                    bookAuthorField.clear();
                    bookPublisherField.clear();
                    noOfCopiesField.clear();
                }

            }else{
                statement.setString(1,title);
                statement.setString(2,author);
                statement.setString(3,publisher);
                statement.setInt(4,copies);

                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Book added successfully!");
                alert.showAndWait();

                bookTitleField.clear();
                bookAuthorField.clear();
                bookPublisherField.clear();
                noOfCopiesField.clear();
            }

            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.getStackTrace();
        }


    }

    public boolean isBookPresent(Connection connection, String title, String author, String publisher){

        String query = "SELECT * FROM book WHERE Title = ? AND Author = ? AND Publisher = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,title);
            statement.setString(2,author);
            statement.setString(3,publisher);

            ResultSet set = statement.executeQuery();
            if(set.next()){

                set.close();
                statement.close();

                return true;

            }else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

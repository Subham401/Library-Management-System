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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class issueRecord {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private TextField memberMail;
    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField bookAuthorField;
    @FXML
    private TextField bookPublisherField;
    @FXML
    private TextField memberMailR;
    @FXML
    private TextField bookTitleFieldR;
    @FXML
    private TextField bookAuthorFieldR;
    @FXML
    private TextField bookPublisherFieldR;



    public void bookissue() {

        String mail = memberMail.getText().trim();
        String title = bookTitleField.getText().trim();
        String author = bookAuthorField.getText().trim();
        String publisher = bookPublisherField.getText().trim();

        String query1 = "Select member_id FROM member WHERE email = ?";
        String query2 = "Select Book_id,available_copies FROM book WHERE Title = ? AND Author = ? AND Publisher = ?";
        String query3 = "INSERT INTO issuerecord(book_id,member_id) VALUES(?,?)";
        String query4 = "UPDATE book SET available_copies = available_copies - 1 WHERE Book_id = ?";


        try{
            Connection connection = ConnectionClass.getConnection();
            PreparedStatement statement1 = connection.prepareStatement(query1);
            PreparedStatement statement2 = connection.prepareStatement(query2);
            PreparedStatement statement3 = connection.prepareStatement(query3);
            PreparedStatement statement4 = connection.prepareStatement(query4);

            int book_id = -1,member_id = -1,copies = -1;

            if(memberExists(connection,mail)){
                statement1.setString(1,mail);
                ResultSet set = statement1.executeQuery();
                if (set.next()){
                    member_id = set.getInt("member_id");
                }
                set.close();
                statement1.close();

                statement2.setString(1,title);
                statement2.setString(2,author);
                statement2.setString(3,publisher);
                ResultSet set1 = statement2.executeQuery();
                if (set1.next()){
                    book_id = set1.getInt("Book_id");
                    copies = set1.getInt("available_copies");
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("BOOK NOT FOUND");
                    alert.setHeaderText(null);
                    alert.setContentText("Book not found in the catalog.");
                    alert.showAndWait();

                    clearFields();
                    return;
                }
                set1.close();statement2.close();
                if(copies > 0){
                    connection.setAutoCommit(false);
                    try{
                        statement3.setInt(1, book_id);
                        statement3.setInt(2, member_id);

                        int noOfRowsAffected = statement3.executeUpdate();

                        statement4.setInt(1, book_id);
                        statement4.executeUpdate();

                        connection.commit();

                        if (noOfRowsAffected > 0) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("SUCCESS");
                            alert.setHeaderText(null);
                            alert.setContentText("!!! BOOK ISSUED !!!");
                            alert.showAndWait();

                            clearFields();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText(null);
                            alert.setContentText("Something Went Wrong!! Please Try Again!!!");
                            alert.showAndWait();

                            clearFields();
                        }

                        statement3.close();
                        statement4.close();
                    }catch(SQLException e){
                        connection.rollback();
                        throw e;
                    }finally {
                        connection.setAutoCommit(true);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("BOOK UNAVAILABLE");
                    alert.setHeaderText(null);
                    alert.setContentText("No copies available for this book at the moment.");
                    alert.showAndWait();

                    clearFields();

                }

            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Member is not Registered!!!");
                alert.showAndWait();

                clearFields();
            }





        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void bookreturn() {

        String mailR = memberMailR.getText().trim();
        String titleR = bookTitleFieldR.getText().trim();
        String authorR = bookAuthorFieldR.getText().trim();
        String publisherR = bookPublisherFieldR.getText().trim();

        String query1 = "Select member_id FROM member WHERE email = ?";
        String query2 = "Select Book_id FROM book WHERE Title = ? AND Author = ? AND Publisher = ?";
        String query3 = "UPDATE issuerecord SET return_date = CURDATE() WHERE book_id = ? AND member_id = ? AND return_date IS NULL";
        String query4 = "UPDATE book SET available_copies = available_copies + 1 WHERE Book_id = ?";

        Connection connection = null;

        try{
            connection = ConnectionClass.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement statement1 = connection.prepareStatement(query1);
            PreparedStatement statement2 = connection.prepareStatement(query2);
            PreparedStatement statement3 = connection.prepareStatement(query3);
            PreparedStatement statement4 = connection.prepareStatement(query4);


            int member_id = -1,book_id = -1;

            statement1.setString(1,mailR);
            statement2.setString(1,titleR);
            statement2.setString(2,authorR);
            statement2.setString(3,publisherR);

            ResultSet set1 = statement1.executeQuery();
            ResultSet set2 = statement2.executeQuery();

            if(set1.next()){
                member_id = set1.getInt("member_id");
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("MEMBER NOT FOUND");
                alert.setHeaderText(null);
                alert.setContentText("No member found with the provided email.");
                alert.showAndWait();
                clearFieldsR();
                return;
            }

            if (set2.next()){
                book_id = set2.getInt("Book_id");
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("BOOK NOT FOUND");
                alert.setHeaderText(null);
                alert.setContentText("Book not found in the catalog.");
                alert.showAndWait();

                clearFieldsR();
                return;
            }
            statement3.setInt(1,book_id);
            statement3.setInt(2,member_id);

            int noOfRowsAffected = statement3.executeUpdate();
            if(noOfRowsAffected > 0){
                statement4.setInt(1,book_id);
                statement4.executeUpdate();

                connection.commit();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("SUCCESS");
                alert.setHeaderText(null);
                alert.setContentText("!!! BOOK RETURNED SUCCESSFULLY !!!");
                alert.showAndWait();

                clearFieldsR();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("!!! BOOK RETURN FAILED !!!");
                alert.showAndWait();

                connection.setAutoCommit(true);

                clearFieldsR();
            }

            set1.close();
            set2.close();
            statement1.close();
            statement2.close();
            statement3.close();
            statement4.close();
            connection.close();


        }catch (SQLException e){
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DATABASE ERROR");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while processing the return.");
            alert.showAndWait();
        }

    }

    public boolean memberExists(Connection connection,String email){

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

    public void clearFields(){
        memberMail.clear();
        bookTitleField.clear();
        bookAuthorField.clear();
        bookPublisherField.clear();
    }
    public void clearFieldsR(){
        memberMailR.clear();
        bookTitleFieldR.clear();
        bookAuthorFieldR.clear();
        bookPublisherFieldR.clear();
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

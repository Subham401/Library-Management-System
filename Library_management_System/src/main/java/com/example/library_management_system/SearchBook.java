package com.example.library_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchBook {

    Parent root;
    Stage stage;
    Scene scene;

    String title,publisher,author;

    @FXML
    private javafx.scene.control.TableView<Book> bookTable;

    @FXML
    private javafx.scene.control.TableColumn<Book, Integer> bookIdColumn;

    @FXML
    private javafx.scene.control.TableColumn<Book, String> titleColumn;

    @FXML
    private javafx.scene.control.TableColumn<Book, String> authorColumn;

    @FXML
    private javafx.scene.control.TableColumn<Book, String> publisherColumn;

    @FXML
    private javafx.scene.control.TableColumn<Book, Integer> availableCopiesColumn;


    @FXML
    private TextField titleName;

    @FXML
    private TextField authorName;

    @FXML
    private TextField publisherName;

    @FXML
    public void initialize() {
        bookIdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("publisher"));
        availableCopiesColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("availableCopies"));
    }


    public void searchTitle(ActionEvent actionEvent) {
        title = titleName.getText();

        String query = "SELECT * from book WHERE Title = ?";

        javafx.collections.ObservableList<Book> books = javafx.collections.FXCollections.observableArrayList();

        try{
            Connection connection = ConnectionClass.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,title);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                int id = set.getInt("Book_id");
                String t = set.getString("Title");
                String a = set.getString("Author");
                String p = set.getString("Publisher");
                int copies = set.getInt("available_copies");

                books.add(new Book(id, t, a, p, copies));
            }

            bookTable.setItems(books);

            set.close();
            statement.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void searchPublisher(ActionEvent actionEvent) {
        publisher = publisherName.getText();

        String query = "SELECT * from book WHERE Publisher = ?";

        javafx.collections.ObservableList<Book> books = javafx.collections.FXCollections.observableArrayList();

        try{
            Connection connection = ConnectionClass.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,publisher);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                int id = set.getInt("Book_id");
                String t = set.getString("Title");
                String a = set.getString("Author");
                String p = set.getString("Publisher");
                int copies = set.getInt("available_copies");

                books.add(new Book(id, t, a, p, copies));
            }

            bookTable.setItems(books);

            set.close();
            statement.close();
            connection.close();

        }catch (SQLException e){
            e.getStackTrace();
        }

    }

    public void searchAuthor(ActionEvent actionEvent) {
        author = authorName.getText();

        String query = "SELECT * from book WHERE Author = ?";

        javafx.collections.ObservableList<Book> books = javafx.collections.FXCollections.observableArrayList();

        try{
            Connection connection = ConnectionClass.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,author);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                int id = set.getInt("Book_id");
                String t = set.getString("Title");
                String a = set.getString("Author");
                String p = set.getString("Publisher");
                int copies = set.getInt("available_copies");

                books.add(new Book(id, t, a, p, copies));
            }

            bookTable.setItems(books);

            set.close();
            statement.close();
            connection.close();

        }catch (SQLException e){
            e.getStackTrace();
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CheckBook.fxml"));
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

module com.example.library_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.library_management_system to javafx.fxml;
    exports com.example.library_management_system;
}
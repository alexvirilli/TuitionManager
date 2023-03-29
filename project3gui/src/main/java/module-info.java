module com.example.project3gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.project3gui to javafx.fxml;
    exports com.example.project3gui;
}
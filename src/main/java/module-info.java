module com.example.javafxmaventraining {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxmaventraining to javafx.fxml;
    exports com.example.javafxmaventraining;
}
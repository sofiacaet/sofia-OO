module com.caetano {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.caetano to javafx.fxml;
    exports com.caetano;
}

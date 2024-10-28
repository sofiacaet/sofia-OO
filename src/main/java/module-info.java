module com.caetano {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.caetano to javafx.fxml;
    exports com.caetano;
}

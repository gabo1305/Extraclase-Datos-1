module Interfaz {
    requires javafx.controls;
    requires javafx.fxml;

    opens Interfaz to javafx.graphics;
}
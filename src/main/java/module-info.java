module com.tokioschool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.tokioschool to javafx.fxml;
    exports com.tokioschool;
}
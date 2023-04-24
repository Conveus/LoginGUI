module com.example.logingui {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.logingui to javafx.fxml;
    exports com.example.logingui;
}
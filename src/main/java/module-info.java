module com.mycompany.requestconverteradmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires java.naming;
    
    opens com.mycompany.requestconverteradmin to javafx.fxml;
    opens com.mycompany.requestconverteradmin.data to javafx.base;
    exports com.mycompany.requestconverteradmin;
    
}

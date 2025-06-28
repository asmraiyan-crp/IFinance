module org.example.ifinance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports org.example.ifinance.demo.dao;
    exports org.example.ifinance.demo.db;
    exports org.example.ifinance.demo.model;
    exports org.example.ifinance.demo.utils;
    exports org.example.ifinance.demo;
    opens org.example.ifinance.demo to javafx.fxml;
}
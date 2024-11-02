module org.example.fx2gradle {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fx2gradle to javafx.fxml;
    exports org.example.fx2gradle;
}
module com.example.workoutmaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.workoutmaker to javafx.fxml;
    exports com.example.workoutmaker;
}
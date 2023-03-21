module a.graphingcalc {
    requires javafx.controls;
    requires javafx.fxml;


    opens a.graphingcalc to javafx.fxml;
    exports a.graphingcalc;
}
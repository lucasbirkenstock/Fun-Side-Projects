module bankprogram.bankaccount2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens bankprogram.bankaccount2 to javafx.fxml;
    exports bankprogram.bankaccount2;
}
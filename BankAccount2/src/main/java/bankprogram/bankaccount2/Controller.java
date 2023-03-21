package bankprogram.bankaccount2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private Parent mRoot;
    private Stage mStage;
    private Scene mScene;
    private ArrayList accountList;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    @FXML // From login screen, switch to account creation page.
    public void switchToCreateAccount(ActionEvent event) throws IOException {
        mRoot = FXMLLoader.load(getClass().getResource("createAccountMenu.fxml"));
        mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mScene = new Scene(mRoot);
        mStage.setScene(mScene);
        mStage.show();
    }


    // Helper methods for account creation **
    public boolean checkMatchingPassword() {
        return password1.getText().equals(password2.getText());
    }

    public boolean checkUniqueEmail() {
        for (Object a : accountList) {
            a = (Account) a;
            if (((Account) a).getEmailAddress().equals(emailAddress.getText())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkFieldsHaveContent() {
        return (!firstName.getText().trim().isEmpty() && !lastName.getText().trim().isEmpty() && !accountName.getText().trim().isEmpty() && !emailAddress.getText().trim().isEmpty() && !password1.getText().trim().isEmpty() && !password2.getText().trim().isEmpty());
    }
    // **

    // Account creation text fields **
    @FXML
    private TextField firstName = new TextField();
    @FXML
    private TextField lastName = new TextField();
    @FXML
    private TextField accountName = new TextField();
    @FXML
    private TextField emailAddress = new TextField();
    @FXML
    private TextField password1 = new TextField();
    @FXML
    private TextField password2 = new TextField();



    @FXML // From account creation screen, switch to login page.
    public void createAccount(ActionEvent event) throws IOException {

        if (checkUniqueEmail() && checkMatchingPassword() && checkFieldsHaveContent()) {
            //accountList.add(new Account(...));
        }

        mRoot = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mScene = new Scene(mRoot);
        mStage.setScene(mScene);
        mStage.show();
        System.out.println(checkMatchingPassword());
    }
}
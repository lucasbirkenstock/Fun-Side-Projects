package bankprogram.bankaccount2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private Parent mRoot;
    private Stage mStage;
    private Scene mScene;
    private static ArrayList<Account> accountList = new ArrayList<>();
    private Model theModel = new Model();

    @FXML // From login screen, switch to account creation page.
    public void switchToCreateAccount(ActionEvent event) throws IOException {
        mRoot = FXMLLoader.load(getClass().getResource("createAccountMenu.fxml"));
        mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mScene = new Scene(mRoot);
        mStage.setScene(mScene);
        mStage.show();
    }


    // Helper methods for account creation **
    public boolean isMatchingPassword() { return password1.getText().equals(password2.getText()); }

    public boolean isUniqueEmail() {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getEmailAddress().equals(emailAddress.getText())) {
                return false;
            }
        }
        return true;
    }

    public boolean fieldsHaveContent() { return (!firstName.getText().trim().isEmpty() && !lastName.getText().trim().isEmpty() && !accountName.getText().trim().isEmpty() && !emailAddress.getText().trim().isEmpty() && !password1.getText().trim().isEmpty() && !password2.getText().trim().isEmpty()); }

    public void displayPasswordError() { passwordMatchError.setVisible(true); }

    public void displayFieldsError() { enterAllFieldsError.setVisible(true); }

    public void displayEmailError() { emailInUseError.setVisible(true); }
    // **

    // Login page FXML
    @FXML
    private TextField loginEmail = new TextField();
    @FXML
    private TextField loginPassword = new TextField();

    // Account creation page FXML
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
    @FXML
    private Text passwordMatchError = new Text();
    @FXML
    private Text enterAllFieldsError = new Text();
    @FXML
    private Text emailInUseError = new Text();

    // Logged in homepage fields
    @FXML
    private Label welcomeMessage = new Label();


    @FXML // From account creation screen, switch to login page via Create Account button. Checks for errors.
    public void createAccount(ActionEvent event) throws IOException {
        if (isUniqueEmail() && isMatchingPassword() && fieldsHaveContent()) {
            accountList.add(new Account(emailAddress.getText(), firstName.getText(), lastName.getText(), accountName.getText(), password1.getText()));
            mRoot = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mScene = new Scene(mRoot);
            mStage.setScene(mScene);
            mStage.show();
        } else {
            if (!isMatchingPassword()) {
                displayPasswordError();
            }
            if (!isUniqueEmail()) {
                displayEmailError();
            }
            if (!fieldsHaveContent()) {
                displayFieldsError();
            }
        }
    }

    public void login(ActionEvent event) throws IOException {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getEmailAddress().equals(loginEmail.getText()) && accountList.get(i).getPassword().equals(loginPassword.getText())) {
                theModel.setAccount(accountList.get(i));
                mRoot = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                mScene = new Scene(mRoot);
                mStage.setScene(mScene);
                mStage.show();
                initializeHomepage();
                break;
            }
            return;
        }

    }

    public void initializeHomepage() {
        welcomeMessage.setText("Welcome, " + theModel.getAccount().getFirstName() + " " + theModel.getAccount().getLastName());
    }
}
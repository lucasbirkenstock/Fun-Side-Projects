package bankprogram.bankaccount2;

public class Account {
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mPassword;

    public Account(String emailAddress, String firstName, String lastName, String username, String password) {
        mEmailAddress = emailAddress;
        mFirstName = firstName;
        mLastName = lastName;
        mUsername = username;
        mPassword = password;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }
}

package bankprogram.bankaccount2;

public class Model {
    private static Account mloggedAccount;

    public Model() {
    }

    protected Account getAccount() {
        return mloggedAccount;
    }

    protected void setAccount(Account a) {
        mloggedAccount = a;
    }
}

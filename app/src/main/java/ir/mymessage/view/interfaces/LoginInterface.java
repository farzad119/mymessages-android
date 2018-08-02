package ir.mymessage.view.interfaces;

public interface LoginInterface {
    String getUserName();
    String getPassword();
    void setupLoginActivity();
    void onLoginClicked();
    void startMainActivity();
    void showUsernamePassword();
}

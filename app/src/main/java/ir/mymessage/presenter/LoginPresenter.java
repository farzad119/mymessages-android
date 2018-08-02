package ir.mymessage.presenter;

import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.LoginInterface;

public class LoginPresenter extends BasePresenter{

    private final LoginInterface loginInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        setupView();
    }

    public void onLoginClicked(){
        if (loginInterface.getUserName().equals("farzad119") && loginInterface.getPassword().equals("testpass1")){
            loginInterface.startMainActivity();
        }
    }

    @Override
    protected void setupView() {
        loginInterface.setupLoginActivity();
    }

    @Override
    protected void setupErrorView() {

    }
}

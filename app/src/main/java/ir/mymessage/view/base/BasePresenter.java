package ir.mymessage.view.base;

import android.content.Context;

public abstract class BasePresenter {

    //protected RestClient restClient = new RestClient();
    //protected DBClient dbClient;
    protected Context context;

    protected abstract void setupView();
    protected abstract void setupErrorView();
}

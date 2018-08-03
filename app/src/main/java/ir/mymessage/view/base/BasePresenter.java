package ir.mymessage.view.base;

import android.content.Context;

import ir.mymessage.service.ApiClient;
import ir.mymessage.service.ApiInterface;
import ir.mymessage.utils.MySharedPrefrences;

public abstract class BasePresenter {

    //protected RestClient restClient = new RestClient();
    //protected DBClient dbClient;
    protected Context context;
    protected ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);

}

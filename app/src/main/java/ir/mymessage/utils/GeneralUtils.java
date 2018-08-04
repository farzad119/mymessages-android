package ir.mymessage.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class GeneralUtils {
    Context context;

    public GeneralUtils(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {

        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }

    public Date dateParser(String timestamp) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("IRST"));
        Date date = null;
        try {
            date = fmt.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

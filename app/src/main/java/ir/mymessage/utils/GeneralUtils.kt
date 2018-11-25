package ir.mymessage.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.SimpleTimeZone
import java.util.TimeZone

class GeneralUtils(var context: Context?) {

    fun isNetworkAvailable(): Boolean{
            val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
        }

    fun dateParser(timestamp: String?): Date? {
        val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        fmt.timeZone = TimeZone.getTimeZone("IRST")
        var date: Date? = null
        try {
            date = fmt.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }
}

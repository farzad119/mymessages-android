package ir.mymessage.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://farzad119.ir/mymessage/api/"

    fun getRetrofit(): Retrofit? {

        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()

        val gson = GsonBuilder()
                .setLenient()
                .create()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

        return retrofit
    }
}


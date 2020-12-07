package com.example.imagecrawler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.imagecrawler.network.APIService
import com.example.imagecrawler.network.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val retrofit = RetrofitClient.client
    val api = retrofit.create(APIService::class.java)
    companion object {
        const val TAG = "tntan"
        const val KEY = "e17dba05850668400dc483a1f8dc5fc5"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()
    }

    private fun fetchData() {
        api.getImage(KEY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<Any>() {
                override fun onNext(response: Any?) {
                    Log.i(TAG, "fecthData onNext: $response")
                }

                override fun onError(e: Throwable?) {
                    Log.i(TAG, "fecthData onError: ")
                }

                override fun onComplete() {
                    Log.i(TAG, "fetchData onComplete: ")
                }

            })
    }
}
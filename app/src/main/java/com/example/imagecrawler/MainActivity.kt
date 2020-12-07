package com.example.imagecrawler

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagecrawler.adapter.ImageAdapter
import com.example.imagecrawler.model.FlickerImage
import com.example.imagecrawler.network.APIService
import com.example.imagecrawler.network.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val retrofit = RetrofitClient.client
    private val api = retrofit.create(APIService::class.java)
    private val imgAdapter = ImageAdapter()

    companion object {
        const val TAG = "STYLER TEST"
        const val KEY = "e17dba05850668400dc483a1f8dc5fc5"
        const val PER_PAGE = "30"
        const val THRESH_HOLD_AUTO_SUGGESTION = 1
        val countries =
            listOf("Japan", "German", "Korea", "Vietnam", "China", "Indonesia", "India", "Mexico")
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAutoCompleteText()
        setUpBtnClick()
        setUpRecyclerView()
    }

    private fun setUpAutoCompleteText() {
        auto_text_view_search.setAdapter(
            ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                countries
            )
        )
        auto_text_view_search.threshold = THRESH_HOLD_AUTO_SUGGESTION
    }


    private fun setUpBtnClick() {
        btn_search.setOnClickListener { _ ->
            fetchData(auto_text_view_search.text.toString())
        }
    }

    private fun setUpRecyclerView() {
        recycler_view.apply {
            adapter = imgAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun fetchData(searchText: String) {
        api.getImage(KEY, searchText, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { _ ->
                Log.i(TAG, "fetchData: doOnError ")
            }
            .subscribe(object : DisposableObserver<FlickerImage>() {
                override fun onNext(response: FlickerImage?) {
                    Log.i(TAG, "fecthData onNext: ${response!!}")
                    recycler_view.show()
                    imgAdapter.setData(response)
                    imgAdapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable?) {
                    Log.i(TAG, "fecthData onError: ")
                    recycler_view.hide()
                }

                override fun onComplete() {
                    Log.i(TAG, "fetchData onComplete: ")
                }
            })
    }
}
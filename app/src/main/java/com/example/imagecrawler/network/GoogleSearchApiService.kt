package com.example.imagecrawler.network

import com.example.imagecrawler.model.FlickerImage
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSearchApiService {
    @GET("?")
    fun getImage(
        @Query("ouput") output: String,
        @Query("hl") hl: String,
        @Query("q") q: String,
    ): Observable<FlickerImage>
}
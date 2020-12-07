package com.example.imagecrawler.network

import com.example.imagecrawler.model.FlickerImage
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    fun getImage(
        @Query("api_key") key: String,
        @Query("text") searchText: String,
        @Query("per_page") perPage: String
    ): Observable<FlickerImage>
}
package com.jhoander.galeryphoto.data.remote

import com.jhoander.galeryphoto.domain.model.Article
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleListApi {
    @GET("http://private-f0eea-mobilegllatam.apiary-mock.com/list")
    fun getNews(): Observable<Article>
}
package com.jhoander.galeryphoto.data.remote

import com.jhoander.galeryphoto.domain.model.Article
import io.reactivex.Observable
import retrofit2.http.GET

interface ArticleApi {
    @GET("list")
    fun getArticles(): Observable<List<Article>>
}
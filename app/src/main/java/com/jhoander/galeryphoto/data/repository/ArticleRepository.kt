package com.jhoander.galeryphoto.data.repository

import com.jhoander.galeryphoto.domain.model.Article
import io.reactivex.Observable

interface ArticleRepository {

    fun getArticles(): Observable<List<Article>>
}
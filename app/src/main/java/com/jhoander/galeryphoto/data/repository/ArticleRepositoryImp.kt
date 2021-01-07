package com.jhoander.galeryphoto.data.repository

import com.jhoander.galeryphoto.data.remote.ArticleApi
import com.jhoander.galeryphoto.domain.model.Article
import io.reactivex.Observable

class ArticleRepositoryImp(
    private val api: ArticleApi
) : ArticleRepository {
    override fun getArticles(): Observable<List<Article>> {
        return api.getArticles().map {
            if (it.isEmpty()) {
                throw Exception("Error")
            }
            it
        }
    }

}
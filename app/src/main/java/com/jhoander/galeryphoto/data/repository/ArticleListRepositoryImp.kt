package com.jhoander.galeryphoto.data.repository

import com.jhoander.galeryphoto.data.remote.ArticleListApi
import com.jhoander.galeryphoto.domain.model.Article
import io.reactivex.Observable

class ArticleListRepositoryImp(
    private val api: ArticleListApi
) : ArticleListRepository {
    override fun getData(): Observable<Article> {
        return api.getNews().map {
            if (it.components.isEmpty()) {
                throw Exception("Error")
            }
            it
        }
    }

}
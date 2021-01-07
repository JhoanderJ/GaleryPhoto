package com.jhoander.galeryphoto.domain.usecase

import com.jhoander.galeryphoto.data.repository.ArticleRepository
import com.jhoander.galeryphoto.domain.model.Article
import com.jhoander.galeryphoto.utils.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor(private val articleRepository: ArticleRepository) :
    UseCase<List<Article>>() {
    override fun createObservableUseCase(): Observable<List<Article>> {
        return articleRepository.getArticles()
    }

}
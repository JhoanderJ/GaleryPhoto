package com.jhoander.galeryphoto.domain.usecase

import com.jhoander.galeryphoto.data.repository.ArticleListRepository
import com.jhoander.galeryphoto.domain.model.Article
import com.jhoander.galeryphoto.utils.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor(private val newsListRepository: ArticleListRepository) :
    UseCase<Article>() {
    override fun createObservableUseCase(): Observable<Article> {
        return newsListRepository.getData()
    }

}
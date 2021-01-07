package com.jhoander.galeryphoto.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhoander.galeryphoto.domain.model.Article
import com.jhoander.galeryphoto.domain.usecase.GetArticleListUseCase
import com.jhoander.galeryphoto.utils.Failure
import com.jhoander.galeryphoto.utils.Resource
import com.jhoander.galeryphoto.utils.ResourceState
import com.jhoander.galeryphoto.utils.base.UseCaseObserver
import com.jhoander.galeryphoto.utils.post
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(private val getArticleListUseCase: GetArticleListUseCase) :
    ViewModel() {

    var liveData: MutableLiveData<Resource<List<Article>>> = MutableLiveData()

    fun getArticleList() {
        liveData.post(status = ResourceState.LOADING)
        getArticleListUseCase.execute(object : UseCaseObserver<List<Article>>() {
            override fun onNext(value: List<Article>) {
                liveData.post(status = ResourceState.SUCCESS, data = value)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                liveData.post(
                    status = ResourceState.ERROR, failure = Failure.Error(
                        "ha ocurrido un error inesperado intente nuevamente"
                    )
                )
            }
        })

    }

    override fun onCleared() {
        getArticleListUseCase.dispose()
    }
}

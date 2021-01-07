package com.jhoander.galeryphoto.di.module

import com.jhoander.galeryphoto.data.remote.ArticleApi
import com.jhoander.galeryphoto.data.repository.ArticleRepository
import com.jhoander.galeryphoto.data.repository.ArticleRepositoryImp
import com.jhoander.galeryphoto.utils.base.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class ArticleRepositoryModule {


    @Provides
    fun provideRepository(
        api: ArticleApi
    ): ArticleRepository {
        return ArticleRepositoryImp(api)
    }

    @Provides
    fun provideApiService(): ArticleApi {
        OkHttpClient()
        return ApiService.build(
            ArticleApi::class.java,
            "http://private-f0eea-mobilegllatam.apiary-mock.com/"
        )
    }
}
package com.jhoander.galeryphoto.di.module

import com.jhoander.galeryphoto.presentation.fragment.ArticleListFragment
import dagger.Module
import dagger.Provides

@Module
class ArticleActivityModule {

    @Provides
    fun provideFragment(): ArticleListFragment {
        return ArticleListFragment.newInstance()
    }

}
package com.jhoander.galeryphoto.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhoander.galeryphoto.presentation.viewmodel.ArticleListViewModel
import com.jhoander.galeryphoto.utils.base.ViewModelFactory
import com.jhoander.galeryphoto.utils.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ArticleListFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    abstract fun bindCompaniesViewModel(viewModel: ArticleListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
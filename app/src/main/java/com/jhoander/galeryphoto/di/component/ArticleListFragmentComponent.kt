package com.jhoander.galeryphoto.di.component

import com.jhoander.galeryphoto.di.module.ArticleListFragmentModule
import com.jhoander.galeryphoto.di.module.ArticleRepositoryModule
import com.jhoander.galeryphoto.presentation.fragment.ArticleListFragment
import com.jhoander.galeryphoto.utils.base.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ArticleListFragmentModule::class, ArticleRepositoryModule::class])
interface ArticleListFragmentComponent : FragmentComponent<ArticleListFragment> {
}
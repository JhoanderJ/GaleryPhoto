package com.jhoander.galeryphoto.di.component

import com.jhoander.galeryphoto.di.module.ArticleDetailModule
import com.jhoander.galeryphoto.presentation.fragment.ArticleDetailFragment
import com.jhoander.galeryphoto.utils.base.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ArticleDetailModule::class])
interface ArticleDetailComponent : FragmentComponent<ArticleDetailFragment> {
}
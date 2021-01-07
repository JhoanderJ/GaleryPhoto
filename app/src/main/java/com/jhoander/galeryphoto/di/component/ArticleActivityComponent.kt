package com.jhoander.galeryphoto.di.component

import com.jhoander.galeryphoto.di.module.ArticleActivityModule
import com.jhoander.galeryphoto.presentation.activity.ArticleActivity
import com.jhoander.galeryphoto.utils.base.ActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ArticleActivityModule::class])
interface ArticleActivityComponent : ActivityComponent<ArticleActivity> {
}
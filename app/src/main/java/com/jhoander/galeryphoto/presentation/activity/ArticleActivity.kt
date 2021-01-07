package com.jhoander.galeryphoto.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.jhoander.galeryphoto.R
import com.jhoander.galeryphoto.di.component.DaggerArticleActivityComponent
import com.jhoander.galeryphoto.presentation.fragment.ArticleListFragment
import javax.inject.Inject

class ArticleActivity : AppCompatActivity() {

    @Inject
    lateinit var articleListFragment: ArticleListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(R.layout.acticle_activity)
        setFragment(R.id.fragmentContainer, articleListFragment, "fragmentList")
    }

    private fun setFragment(idContainer: Int, fragment: Fragment, tag: String) {
        val trans: FragmentTransaction = supportFragmentManager.beginTransaction()
        trans.replace(idContainer, fragment, tag)
        trans.commit()
    }

    private fun injectDependencies() {
        DaggerArticleActivityComponent.builder().build().inject(this)

    }
}

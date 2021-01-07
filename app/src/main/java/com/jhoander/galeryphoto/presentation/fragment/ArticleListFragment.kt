package com.jhoander.galeryphoto.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhoander.galeryphoto.R
import com.jhoander.galeryphoto.di.component.DaggerArticleListFragmentComponent
import com.jhoander.galeryphoto.domain.model.Article
import com.jhoander.galeryphoto.presentation.adapter.ArticleListAdapter
import com.jhoander.galeryphoto.presentation.viewmodel.ArticleListViewModel
import com.jhoander.galeryphoto.utils.Failure
import com.jhoander.galeryphoto.utils.ResourceState
import com.jhoander.galeryphoto.utils.base.ViewModelFactory
import com.jhoander.galeryphoto.utils.base.getViewModel
import com.jhoander.galeryphoto.utils.extension.showMessage
import com.jhoander.galeryphoto.utils.extension.showProgress
import kotlinx.android.synthetic.main.article_list_fragment.*
import javax.inject.Inject

class ArticleListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ArticleListViewModel

    private lateinit var articleListAdapter: ArticleListAdapter

    var articleDetailFragment: ArticleDetailFragment = ArticleDetailFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependence()
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticleList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.article_list_fragment, container, false)


    companion object {
        fun newInstance(): ArticleListFragment {
            val instance = ArticleListFragment()
            return instance
        }
    }

    private fun injectDependence() {
        DaggerArticleListFragmentComponent.builder().build().inject(this)
    }

    private fun initViewModel() {
        viewModel = getViewModel(viewModelFactory)
        viewModel.liveData.observe(this, Observer {
            it?.also {
                handleArticleList(it.status, it.data, it.failure)
                pullToRefresh()
            }
        })
    }

    private fun pullToRefresh() {
        itemsSwipeToRefresh.setOnRefreshListener {
            viewModel.getArticleList()
            viewModel = getViewModel(viewModelFactory)
            viewModel.liveData.observe(this, Observer {
                it?.also {
                    handleArticleList(it.status, it.data, it.failure)
                    itemsSwipeToRefresh.isRefreshing = false
                }
            })
        }
    }

    private fun displayArticles(articles: List<Article>) {
        articleListAdapter = ArticleListAdapter{
            changeFragment(it)
        }
        articleRv.layoutManager = LinearLayoutManager(activity)
        articleRv.layoutManager = GridLayoutManager(context, 1)
        articleRv.adapter = articleListAdapter

        articles?.let {
            articleListAdapter.setList(it)
        }
    }

    private fun handleArticleList(status: ResourceState, data: List<Article>?, failure: Failure?) {
        when (status) {
            ResourceState.LOADING -> {
                pbArticle.showProgress(true, activity)
            }
            ResourceState.SUCCESS -> {
                pbArticle.showProgress(false, activity)
                data?.let {
                    displayArticles(data)
                }
            }
            ResourceState.ERROR -> {
                pbArticle.showProgress(false, activity)
                when (failure) {
                    Failure.NetworkConnection -> {

                    }
                    Failure.ServerError -> {
                        pbArticle.showProgress(false, activity)
                        showMessage((failure as Failure.Error).errorMessage, context)
                    }
                }
            }
            else -> {
            }
        }

    }

    private fun addFragment(fragment: Fragment?, idContainer: Int) {
        val trans: FragmentTransaction? = parentFragmentManager.beginTransaction()
        fragment?.let {
            trans?.add(idContainer, it)
        }
        trans?.addToBackStack(null)
        trans?.commit()
    }

    private fun changeFragment(article : Article){
        val b = Bundle()
        b.putParcelable("article", article)
        articleDetailFragment.setArguments(b)
        addFragment(articleDetailFragment, R.id.fragmentContainer)
    }

}
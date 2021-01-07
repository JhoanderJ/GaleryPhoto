package com.jhoander.galeryphoto.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.jhoander.galeryphoto.R
import com.jhoander.galeryphoto.di.component.DaggerArticleDetailComponent
import com.jhoander.galeryphoto.domain.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_detail_fragment.*

class ArticleDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependence()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<Article>("article") as Article
        loadImage(data.image)
        tvDescription.text = data.description
        tvName.text = data.title
        ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun loadImage(urlImage: String?) {
        urlImage?.let {
            if (ivImage.tag == it) {
                return@let
            }
            Picasso.get()
                .load(it)
                .into(ivImage)

            ivImage.tag = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.article_detail_fragment, container, false)

    private fun injectDependence() {
        DaggerArticleDetailComponent.builder().build().inject(this)
    }

}
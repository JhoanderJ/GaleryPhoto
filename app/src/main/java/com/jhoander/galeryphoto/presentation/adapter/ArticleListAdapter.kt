package com.jhoander.galeryphoto.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhoander.galeryphoto.R
import com.jhoander.galeryphoto.domain.model.Article
import com.jhoander.galeryphoto.utils.extension.inflate
import com.jhoander.galeryphoto.utils.extension.setSafeOnClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_articulo_list.view.*

class ArticleListAdapter constructor(val listener: (Article) -> Unit) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    private lateinit var articles: List<Article>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleListAdapter.ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.item_articulo_list)
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleListAdapter.ViewHolder, position: Int) {
        holder.initView(articles[position], position)
    }

    fun setList(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initView(item: Article?, pos: Int) {
            if (item == null) {
                itemView.visibility = View.GONE
                return
            }

            itemView.title.text = item.title
            itemView.description.text = item.description

            item.image?.let {
                if (itemView.image.tag == it) {
                    return@let
                }
                Picasso.get()
                    .load(it)
                    .into(itemView.image)

                itemView.image.tag = it
            }

            itemView.setSafeOnClickListener {
                listener(articles[adapterPosition])
            }
        }
    }
}
package com.sunil0354.demo.ui.main

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sunil0354.demo.R
import com.sunil0354.demo.repository.model.TestList
import com.sunil0354.demo.repository.model.TestListItem
import com.sunil0354.demo.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(
    private val listener: (TestListItem) -> Unit, private val isRemoveEnable: Boolean = false
) : RecyclerView.Adapter<MainAdapter.NewsHolder>() {
    private var list: List<TestListItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsHolder(parent.inflate(R.layout.item_main))

    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int)  {
        newsHolder.bind(list[position], listener, isRemoveEnable)
    }

    override fun getItemCount() = list.size

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(
            newsArticle: TestListItem,
            listener: (TestListItem) -> Unit,
            isRemoveEnable: Boolean
        ) = with(itemView) {
            rl1.setOnClickListener {
                listener(newsArticle)
            }
            tvItemName?.text = newsArticle.itemName
            tvid?.text = newsArticle.itemId
            tvLabelName?.text = newsArticle.labName
            tvPrice?.text = "$" + newsArticle.minPrice.toString()
            tvRemove?.visibility = if (isRemoveEnable) {
                View.VISIBLE
            } else {
                View.GONE
            }
            Glide.with(context)
                .load(newsArticle.url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.logo)
                        .override(50, 50)
                        .error(R.drawable.logo)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(ivImage)
            tvRemove.setOnClickListener {
                listener(newsArticle)
            }
            if (!isRemoveEnable){
                ll1.setOnClickListener {
                    listener(newsArticle)
                }
                rl2.setOnClickListener {
                    listener(newsArticle)
                }
            }
        }
    }

    /**
     * Swap function to set new data on updating
     */
    fun replaceItems(items: TestList) {
        list = items
        notifyDataSetChanged()
    }
}
package com.nilin.simplenews


import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nilin.simplenews.model.NewsModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_news.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * Created by liangd on 2017/6/6.
 */

class NewsAdapter(val items: List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, null))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        return holder.bind(Items[position],listener)
        var news = items.get(position)
        holder.itemView.item_title.text = news.desc
        holder.itemView.item_time.text = news.publishedAt.substring(0, 10)
        var model: NewsModel = items[position]
        doAsync {
            val data = URL(model.images[0] + "?imageView2/0/w/1000").readBytes()
            uiThread {
                if (data != null) {
                    val bitma = BitmapFactory.decodeByteArray(data, 0, data.size)
                    holder.itemView.item_icon.setImageBitmap(bitma)
                } else {
                    holder.itemView.item_icon.colorFilter
                }
            }
        }
    }

    override fun getItemCount() = items.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
//        fun bind(item: model, listener: (model) -> Unit) = with(itemView) {
//            item_title.text = model.desc
////            item_icon.loadUrl(item.url)
//            setOnClickListener { listener(item) }
//        }

    }

}





package com.nilin.developgoods

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nilin.developgoods.model.NewsModel
import kotlinx.android.synthetic.main.item_news.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.uiThread
import java.net.URL
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




/**
 * Created by liangd on 2017/6/6.
 */
class NewsAdapter(var items:List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

//    var items:NewsAdapter

    fun NewsAdapter(context: Context, data: List<NewsModel>) {
        var mContext = context
        var mData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var news = items.get(position)
        holder.itemView.item_title.text = news.desc
        holder.itemView.item_time.text = news.publishedAt.substring(0, 10)
        var model: NewsModel = items[position]
        doAsync {
            val data = URL(model.images[0] + "?imageView2/0/w/100").readBytes()
            uiThread {
                if (data != null) {
                    val bitma = BitmapFactory.decodeByteArray(data, 0, data.size)
                    holder.itemView.item_icon.setImageBitmap(bitma)
                } else {
                    holder.itemView.item_icon.colorFilter
                }
            }
        }
//
//        holder.itemView.onClick {
//            var position = holder.adapterPosition
//            var context = holder.itemView.context
//            val intent = Intent()
//            intent.setClass(context, DetailsActivity::class.java)
//            intent.putExtra("url", news.url)
//            context.startActivity(intent)
//
//        }
    }

//    override fun getItemCount() = items.size
    override fun getItemCount()= 20

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        fun ViewHolder(item: View) {
//            super(itemView)
        }

    }



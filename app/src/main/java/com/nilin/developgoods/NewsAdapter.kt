package com.nilin.developgoods

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nilin.developgoods.model.NewsModel
import kotlinx.android.synthetic.main.item_news.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * Created by liangd on 2017/6/6.
 */
class NewsAdapter(context: Context,var items: List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    fun NewsAdapter(context: Context, data: List<NewsModel>) {
        var mContext = context
        var mData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //判断是否设置了监听器
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener {
                val position = holder.layoutPosition // 1
                mOnItemClickListener!!.onItemClick(holder.itemView, position) // 2
            }
        }


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

    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private var mOnItemClickListener: OnItemClickListener? = null


    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }

    //新增数据
    fun addData(position: Int,items: List<NewsModel>) {
        var mposition = position
        var mitems = items


        notifyItemInserted(position)
    }

}



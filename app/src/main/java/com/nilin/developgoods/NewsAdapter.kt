package com.nilin.developgoods

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nilin.developgoods.model.NewsModel
import kotlinx.android.synthetic.main.item_news.view.*


/**
 * Created by liangd on 2017/6/6.
 */
class NewsAdapter(var mList: List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    fun NewsAdapter(mList: List<NewsModel>) {
        var mmList = mList
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

//        holder.itemView.item_title.text = items.get(position).get(position).desc
//        holder.itemView.item_title.text = mList[0].get(position).desc
        holder.itemView.item_title.text = mList[position].desc
        holder.itemView.item_time.text = mList[position].publishedAt.substring(0, 10)
//        var model: NewsModel = mList[position]
//        doAsync {
//            val data = URL(model.images[0] + "?imageView2/0/w/100").readBytes()
//            uiThread {
//                if (data != null) {
//                    val bitma = BitmapFactory.decodeByteArray(data, 0, data.size)
//                    holder.itemView.item_icon.setImageBitmap(bitma)
//                } else {
//                    holder.itemView.item_icon.colorFilter
//                }
//            }
//        }

    }

    override fun getItemCount() = mList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private var mOnItemClickListener: OnItemClickListener? = null


    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }

//    //新增数据
//    fun addData(results:List<NewsModel>) {
//        mList.add(results)
//
//    }

}



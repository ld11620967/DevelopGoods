package com.nilin.simplenews

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nilin.simplenews.model.NewsModel
import kotlinx.android.synthetic.main.item_news.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * Created by liangd on 2017/6/6.
 */
class NewsAdapter(var list:List<NewsModel>, var context: Context): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v: View = LayoutInflater.from(context).inflate(R.layout.item_news,null)
            var model:NewsModel=list[position];
        v.item_title.text=model.desc
        v.item_time.text=model.publishedAt

//        if (model.images != "") {
//            doAsync {
//                val data= URL(model.images).readBytes()
//                if (data != null) {
//                    uiThread{
//                        val bitma = BitmapFactory.decodeByteArray(data, 0, data.size)
//                        v.item_icon.setImageBitmap(bitma)
////                    toast("success")
////                }else{
////                    toast("error")
//                    }
//
//                }
//            }
//        }

        return v
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

}
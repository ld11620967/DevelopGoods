package com.nilin.developgoods

import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.developgoods.model.Article
import java.text.SimpleDateFormat
import java.util.*


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ArticleAdapter(var context: Context, layoutId: Int) : BaseQuickAdapter<Article, BaseViewHolder>(layoutId) {

    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    override fun convert(viewHolder: BaseViewHolder, article: Article) {
        if (article.desc.length > 50) {
            viewHolder.setText(R.id.title, article.desc.substring(0, 49) + "...")
        } else {
            viewHolder.setText(R.id.title, article.desc)
        }

        viewHolder.setText(R.id.publishedAt, DateUtils.getRelativeTimeSpanString(sdf.parse(article.publishedAt).time))

        val image: ImageView = viewHolder.getView(R.id.image)

        if (article.images.isNullOrEmpty()) {
            image.visibility = View.GONE
        } else {
            Glide.with(context).load(article.images[0]).into(image)
        }
    }
}



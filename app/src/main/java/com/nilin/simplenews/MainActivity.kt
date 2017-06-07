package com.nilin.simplenews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.nilin.simplenews.model.NewsModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var model1 = NewsModel(1, "111", "http://blog.niunan.net/content/images/logo.jpg", "2017.05.06")
        var model2 = NewsModel(1, "222", "http://blog.niunan.net/content/images/logo.jpg", "2017.11.06")
        var model3 = NewsModel(1, "333", "http://blog.niunan.net/content/images/logo.jpg", "2017.04.06")
        var model4 = NewsModel(1, "444", "http://blog.niunan.net/content/images/logo.jpg", "2017.05.12")

        var list:List<NewsModel> = listOf(model1,model2,model3,model4)

        listview.adapter = NewsAdapter(list, this)

//        listview.setOnItemClickListener()
    }
}


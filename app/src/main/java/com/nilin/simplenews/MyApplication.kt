package com.nilin.simplenews

import android.app.Application
import android.content.Context

/**
 * Created by liangd on 2017/6/15.
 */
class MyApplication:Application(){


    override fun onCreate() {
        context=applicationContext
    }


    companion object {
        var context: Context = null!!
        fun context():Context {
            return context
        }
    }

}


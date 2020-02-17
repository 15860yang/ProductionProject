package com.snowman.funnyjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.snowman.baselibrary.ioc.annotation.CheckNet
import com.snowman.baselibrary.ioc.annotation.OnClick
import com.snowman.baselibrary.ioc.annotation.ViewById
import com.snowman.baselibrary.ioc.api.ViewUtils

class MainActivity : AppCompatActivity() {

    @ViewById(R.id.main_tv)
    private var mainTv: TextView? = null

    @ViewById(R.id.main_iv)
    private var mainIv: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewUtils.inject(this)
    }

    @OnClick(R.id.main_tv, R.id.main_iv)
    @CheckNet
    fun onClick(v: View) {
        when (v) {
            is TextView ->
                Toast.makeText(this, "hello World! textView", Toast.LENGTH_SHORT).show()
            is ImageView ->
                Toast.makeText(this, "hello World! ImageView", Toast.LENGTH_SHORT).show()
        }
    }
}


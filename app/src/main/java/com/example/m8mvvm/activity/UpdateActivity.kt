package com.example.m8mvvm.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.m8mvvm.R
import com.example.m8mvvm.model.Post
import com.example.m8mvvm.viewModel.CreateViewModel
import com.example.m8mvvm.viewModel.UpdateViewModel

class UpdateActivity : AppCompatActivity() {
    lateinit var et_title: EditText
    lateinit var et_body: EditText
    lateinit var viewModel:UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        et_title = findViewById(R.id.et_title)
        et_body = findViewById(R.id.et_body)
        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        val btn_post = findViewById<Button>(R.id.btn_update)

        val post = intent.getSerializableExtra("post") as Post

        et_title.setText(post.title)
        et_body.setText(post.body)

        btn_cancel.setOnClickListener {
            finish()
        }
        btn_post.setOnClickListener {
            back(post)
        }
    }

    fun back(post: Post){
        post.title = et_title.text.toString()
        post.body = et_body.text.toString()

        viewModel.apiPostUpdate(post).observe(this, {
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }
}
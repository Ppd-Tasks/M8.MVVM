package com.example.m8mvvm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.m8mvvm.R
import com.example.m8mvvm.model.Post
import com.example.m8mvvm.viewModel.CreateViewModel
import com.example.m8mvvm.viewModel.MainViewModel

class CreateActivity : AppCompatActivity() {
    lateinit var et_title: EditText
    lateinit var et_body: EditText
    lateinit var viewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
        et_title = findViewById(R.id.et_title)
        et_body = findViewById(R.id.et_body)
        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        val btn_post = findViewById<Button>(R.id.btn_post)

        btn_cancel.setOnClickListener {
            finish()
        }
        btn_post.setOnClickListener {
            back()
        }
    }

    fun back(){
        val title:String = et_title.text.toString()
        val body:String = et_body.text.toString()

        val post = Post(1,2,title,body)

        viewModel.apiPostCreate(post).observe(this, Observer {
            val intent = Intent()
            setResult(RESULT_OK,intent)
            finish()
        })
    }
}
package com.example.m8mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.m8mvvm.activity.MainActivity
import com.example.m8mvvm.R
import com.example.m8mvvm.model.Post
import com.example.m8mvvm.utils.Utils

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]
        if (holder is PosterViewHolder) {
            val ll_poster = holder.ll_poster
            val tv_title = holder.tv_title
            val tv_body = holder.tv_body
            val btn_update = holder.btn_update

            tv_title.setText(post.title!!.toUpperCase())
            tv_body.setText(post.body)

            ll_poster.setOnLongClickListener {
                deletePostDialog(post)
                false
            }

            btn_update.setOnClickListener {
                activity.callUpdateActivity(post)
            }
        }
    }

    inner class PosterViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var ll_poster: LinearLayout
        var tv_title: TextView
        var tv_body: TextView
        var btn_update: Button

        init {
            ll_poster = view.findViewById(R.id.ll_poster)
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
            btn_update = view.findViewById(R.id.btn_update)
        }
    }

    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.viewModel.apiPostDelete(post).observe(activity, Observer {
                    activity.viewModel.apiPostList()
                })
            }

            override fun onNegativeClick() {
                Toast.makeText(activity, "Can't delete this item", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
package com.example.m8mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.m8mvvm.model.Post
import com.example.m8mvvm.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateViewModel:ViewModel() {

    val createPost = MutableLiveData<Post>()

    fun apiPostCreate(post: Post):LiveData<Post> {

        RetrofitHttp.postService.createPost(post)!!.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                createPost.value = response.body()
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                createPost.value = null
            }

        })
        return createPost
    }
}
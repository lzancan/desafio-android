package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.api.PicPayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            service.getUsers()
                /*.enqueue(object : Callback<List<UserResponse>> {
                    override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                        val message = getString(R.string.error)

                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.GONE

                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onResponse(
                        call: Call<List<UserResponse>>,
                        response: Response<List<UserResponse>>
                    ) {
                        progressBar.visibility = View.GONE

                        adapter.users = response.body()!!
                    }
                })*/
        }
    }
}
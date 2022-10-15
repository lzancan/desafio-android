package com.picpay.desafio.android.presentation.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_user.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        showLoading()

        configureList()

        observeViewModel()

        viewModel.fetchData()
    }

    private fun configureList() = with(binding) {
        recyclerView.adapter = UserListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun updateList(list: List<User>) {
        (binding.recyclerView.adapter as UserListAdapter).users = list
    }

    private fun observeViewModel() = with(viewModel) {
        usersLoaded.observe(this@MainActivity) {
            hideLoading()
            updateList(it)
        }
        loadError.observe(this@MainActivity) {
            showError()
        }
    }

    private fun showLoading() = with(binding) {
        userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() = with(binding) {
        userListProgressBar.visibility = View.GONE
    }

    private fun hideLoadingAndList() = with(binding) {
        userListProgressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    private fun showError() = with(binding) {
        hideLoadingAndList()

        Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT)
            .show()
    }
}

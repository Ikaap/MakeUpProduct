package com.ikapurwanti.makeupproduct.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikapurwanti.makeupproduct.R
import com.ikapurwanti.makeupproduct.data.network.api.datasource.MakeUpApiDataSource
import com.ikapurwanti.makeupproduct.data.network.api.datasource.MakeUpDataSource
import com.ikapurwanti.makeupproduct.data.network.api.service.MakeUpService
import com.ikapurwanti.makeupproduct.data.repository.MakeUpRepository
import com.ikapurwanti.makeupproduct.data.repository.MakeUpRepositoryImpl
import com.ikapurwanti.makeupproduct.databinding.ActivityMainBinding
import com.ikapurwanti.makeupproduct.presentation.main.adapter.MakeUpListAdapter
import com.ikapurwanti.retrofitexample.utils.GenericViewModelFactory
import com.ikapurwanti.retrofitexample.utils.proceedWhen

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapterMakeUp = MakeUpListAdapter()

    private val viewModel : MainViewModel by viewModels {
        val service = MakeUpService.invoke()
        val dataSource : MakeUpDataSource = MakeUpApiDataSource(service)
        val repo : MakeUpRepository = MakeUpRepositoryImpl(dataSource)
        GenericViewModelFactory.create(MainViewModel(repo))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRv()
        getDataMakeUp()
        observeData()
    }

    private fun observeData() {
        viewModel.makeUpLiveData.observe(this){
            it.proceedWhen(
                doOnSuccess = {
                    binding.rvMakeup.isVisible = true
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvMakeup.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = adapterMakeUp
                    }
                    it.payload?.let {
                        adapterMakeUp.setData(it)
                    }
                },
                doOnLoading = {
                    binding.rvMakeup.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                },
                doOnError = {err ->
                    binding.rvMakeup.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = err.exception?.message.orEmpty()
                },
                doOnEmpty = {
                    binding.rvMakeup.isVisible = false
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = getString(R.string.text_error_data_empty)
                }
            )
        }
    }

    private fun getDataMakeUp() {
        viewModel.getMakeUp()
    }

    private fun setUpRv() {
        binding.rvMakeup.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterMakeUp
            adapterMakeUp.refreshData()
        }
    }
}
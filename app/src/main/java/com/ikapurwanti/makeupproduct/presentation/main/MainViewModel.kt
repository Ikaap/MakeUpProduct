package com.ikapurwanti.makeupproduct.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikapurwanti.makeupproduct.data.repository.MakeUpRepository
import com.ikapurwanti.makeupproduct.model.MakeUpViewParam
import com.ikapurwanti.retrofitexample.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MakeUpRepository) : ViewModel() {

    private val _makeUpLiveData = MutableLiveData<ResultWrapper<List<MakeUpViewParam>>>()
    val makeUpLiveData: LiveData<ResultWrapper<List<MakeUpViewParam>>>
        get() = _makeUpLiveData

    fun getMakeUp(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getMakeUp().collect{
                _makeUpLiveData.postValue(it)
            }
        }
    }
}
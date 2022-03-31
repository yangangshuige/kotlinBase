package com.xtool.polaris.ui.home

import androidx.lifecycle.*
import com.xtool.polaris.bean.BannerBean
import com.xtool.polaris.network.NetworkUtils.callRequest
import com.xtool.polaris.network.NetworkUtils.handlerResponse
import com.xtool.polaris.network.ResourceState
import com.xtool.polaris.network.RetrofitFactory
import com.xtool.polaris.network.fold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val dispatchers = Dispatchers.IO
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    private val bannerState =
        UnStickyLiveData<ResourceState<List<BannerBean>>>()
    val bannerLiveData = bannerState
    val text: LiveData<String> = _text
    fun http() {
        viewModelScope.launch {
            val response = withContext(dispatchers) {
                callRequest { handlerResponse(RetrofitFactory.instance.service.getBannerJson()) }
            }
            response.fold(onSuccess = {
                bannerState.postValue(ResourceState.success(it))
            }, onFailure = {
                bannerState.postValue(ResourceState.error(null,-1,""))
            })
        }
    }
}
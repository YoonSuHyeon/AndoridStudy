package com.example.mvvmexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel :ViewModel() {
    val networkService = RetrofitFactory.create()

    fun getNews():MutableLiveData<List<ItemModel>>{
        var liveData=MutableLiveData<List<ItemModel>>()
        networkService.getList(QUERY, API_KEY,1,10)
            ?.enqueue(object : Callback<PageListModel?> {
                override fun onResponse(
                    call: Call<PageListModel?>,
                    response: Response<PageListModel?>
                ) {
                    if (response.isSuccessful) {
                        liveData.postValue(response.body()!!.articles)
                    }
                }

                override fun onFailure(
                    call: Call<PageListModel?>,
                    t: Throwable
                ) {
                }
            })
        return liveData
    }
    companion object{
        private const val QUERY= "flower"
        private const val API_KEY = "42ae94278ea943948945616f4b661987"
    }
}
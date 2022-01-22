package uz.ilhomjon.youtubeapi.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import uz.ilhomjon.youtubeapi.models.YoutubeApiData
import uz.ilhomjon.youtubeapi.repository.YoutubeRepository
import uz.ilhomjon.youtubeapi.retrofit.ApiClient
import uz.ilhomjon.youtubeapi.utils.NetworkHelper
import uz.ilhomjon.youtubeapi.utils.Resource

class YoutubeViewModel : ViewModel() {

    private val youtubeRepository = YoutubeRepository(ApiClient.apiService)
    private val liveData = MutableLiveData<Resource<YoutubeApiData>>()

    fun getYoutubeData(context: Context):LiveData<Resource<YoutubeApiData>>{
        val myNetworkHelper = NetworkHelper(context)
        if (myNetworkHelper.isNetworkConnected()){
            viewModelScope.launch {
                liveData.postValue(Resource.loading(null))
                val response = youtubeRepository.getData()
                if (response.isSuccessful){
                    liveData.postValue(Resource.success(response.body()))
                }else{
                    liveData.postValue(Resource.error(response.errorBody()?.string().toString(), null))
                }
            }
        }else{
            liveData.postValue(Resource.error("Internet not connected", null))
        }
        return liveData
    }
}
package uz.ilhomjon.youtubeapi.repository

import uz.ilhomjon.youtubeapi.retrofit.ApiService

class YoutubeRepository(val apiService: ApiService) {

    suspend fun getData() = apiService.getData()
}
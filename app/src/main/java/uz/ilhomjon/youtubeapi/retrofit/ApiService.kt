package uz.ilhomjon.youtubeapi.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.ilhomjon.youtubeapi.models.YoutubeApiData
import uz.ilhomjon.youtubeapi.utils.Resource

interface ApiService {
    @GET("search")
    suspend fun getData(
        @Query("key") key: String = "AIzaSyD9snBrBGM1LaAWZrzrHhhTD4krQqVmjxo",
        @Query("channelId") channelId: String = "UC1WFcPmc93SyNhX80iOfrRQ",
        @Query("part") part:String = "snippet,id",
        @Query("order") order:String = "date",
        @Query("maxResults") maxResults:Int = 20
    ):Response<YoutubeApiData>
}
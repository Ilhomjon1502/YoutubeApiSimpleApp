package uz.ilhomjon.youtubeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.ilhomjon.youtubeapi.adapters.YoutubeAdapter
import uz.ilhomjon.youtubeapi.databinding.ActivityMainBinding
import uz.ilhomjon.youtubeapi.models.Item
import uz.ilhomjon.youtubeapi.utils.Status
import uz.ilhomjon.youtubeapi.viewmodels.YoutubeViewModel

class MainActivity : AppCompatActivity() {

//    documentation youtube link: https://developers.google.com/youtube/android/player
//    all videos link: https://www.googleapis.com/youtube/v3/search?key=AIzaSyD9snBrBGM1LaAWZrzrHhhTD4krQqVmjxo&channelId=UC1WFcPmc93SyNhX80iOfrRQ&part=snippet,id&order=date&maxResults=20

    lateinit var binding: ActivityMainBinding
    lateinit var youtubeViewModel: YoutubeViewModel
    private val TAG = "MainActivity"
    lateinit var youtubeAdapter:YoutubeAdapter
    lateinit var list: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        youtubeAdapter = YoutubeAdapter(list, object :YoutubeAdapter.OnCLick{
            override fun onCLick(videoId: String) {
                val intent = Intent(this@MainActivity, YoutubeActivity::class.java)
                intent.putExtra("video_id", videoId)
                startActivity(intent)
            }
        })
        binding.rv.adapter = youtubeAdapter

        youtubeViewModel = ViewModelProvider(this)[YoutubeViewModel::class.java]
        youtubeViewModel.getYoutubeData(this).observe(this, Observer {
            when(it.status){
                Status.ERROR->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error \n${it.message}", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS->{
                    binding.progressBar.visibility = View.GONE
                    Log.d(TAG, "onCreate: ${it.data}")
                    it.data?.items?.let { it1 -> list.addAll(it1) }
                    youtubeAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}
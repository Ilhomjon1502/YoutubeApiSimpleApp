package uz.ilhomjon.youtubeapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.ilhomjon.youtubeapi.databinding.ItemRvBinding
import uz.ilhomjon.youtubeapi.models.Item

class YoutubeAdapter(val list: List<Item>, val onCLick: OnCLick) : RecyclerView.Adapter<YoutubeAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(item: Item) {
            Picasso.get().load(item.snippet.thumbnails.medium.url).into(itemRv.imageItem)
            itemRv.tvItem.text = item.snippet.title
            itemRv.root.setOnClickListener {
                onCLick.onCLick(item.id.videoId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnCLick{
        fun onCLick(videoId:String)
    }
}
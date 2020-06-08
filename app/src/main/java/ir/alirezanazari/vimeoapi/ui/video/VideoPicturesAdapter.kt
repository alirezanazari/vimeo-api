package ir.alirezanazari.vimeoapi.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.data.net.entity.search.Picture
import ir.alirezanazari.vimeoapi.internal.ImageLoader
import kotlinx.android.synthetic.main.row_picture.view.*


class VideoPicturesAdapter(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<VideoPicturesAdapter.PictureViewHolder>() {

    val items = ArrayList<Picture>()

    fun setItems(data: List<Picture>){
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_picture, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Picture) {
            itemView.apply {
                imageLoader.load(ivPicture, item.link, R.drawable.place_holder)
            }
        }
    }
}
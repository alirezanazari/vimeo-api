package ir.alirezanazari.vimeoapi.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video
import ir.alirezanazari.vimeoapi.internal.ImageLoader
import kotlinx.android.synthetic.main.row_video.view.*


class SearchResultAdapter(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<Video?>()
    var onClick: ((id: String?) -> Unit)? = null

    fun setItems(videos: List<Video>) {
        items.addAll(videos)
        notifyDataSetChanged()
    }

    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addLoader() {
        items.add(null)
        notifyItemInserted(items.size - 1)
    }

    fun removeLoader() {
        if (items.size == 0) return
        val item = items[items.size - 1]
        if (item == null) {
            items.remove(item)
            notifyItemRemoved(items.size - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] != null)
            0
        else
            1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0)
            VideoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_video,
                    parent,
                    false
                )
            )
        else
            LoaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_loader,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoaderViewHolder) {
            holder.bind()
        } else if (holder is VideoViewHolder) {
            if (items[position] != null) holder.bind(items[position]!!)
        }
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Video) {
            itemView.apply {
                val description = if (item.description.isNullOrEmpty()) "" else item.description
                val url =
                    if (item.pictures.sizes.isNotEmpty()) item.pictures.sizes[0].linkWithPlayButton else ""

                tvName.text = item.name
                tvDescription.text = description
                imageLoader.load(ivPoster, url, R.drawable.place_holder)

                setOnClickListener { onClick?.invoke(items[adapterPosition]?.uri) }
            }
        }
    }

    inner class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            itemView.apply {

            }
        }
    }

}
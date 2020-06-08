package ir.alirezanazari.vimeoapi.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.data.net.entity.comment.Comment
import kotlinx.android.synthetic.main.row_comment.view.*


class VideoCommentsAdapter : RecyclerView.Adapter<VideoCommentsAdapter.CommentViewHolder>() {

    val items = ArrayList<Comment>()

    fun setItems(data: List<Comment>) {
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Comment) {
            itemView.apply {
                tvName.text = "${item.user.name} say :"
                tvComment.text = item.text
            }
        }
    }
}
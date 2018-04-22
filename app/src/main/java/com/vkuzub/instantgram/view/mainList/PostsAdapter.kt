package com.vkuzub.instantgram.view.mainList

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import com.vkuzub.instantgram.R
import com.vkuzub.instantgram.data.network.models.response.Post
import com.vkuzub.instantgram.utils.extensions.glide
import com.vkuzub.instantgram.utils.extensions.inflate
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.android.synthetic.main.photo_progress.view.*

class PostsAdapter(_data: List<Post>? = null) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var data: List<Post>? = _data
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_post, false))

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        if (item != null) {
            with(item, {
                holder.tvAccount.text = owner?.account ?: "Unnamed"
                val userName = owner?.username ?: "Unnamed"
                holder.tvProfileName.text = userName

                holder.profileAvatar.glide(holder.profileAvatarProgress, owner?.avatar
                        ?: "", true, CropCircleTransformation(holder.profileAvatar.context), R.drawable.cloud_off)
                val likesStr = "Liked by ${likedByUsers?.joinToString(", ")}"
                val spannableLikes = SpannableString(likesStr)
                spannableLikes.setSpan(ForegroundColorSpan(Color.BLACK), 9, likesStr.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                holder.tvLikedBy.text = spannableLikes

                val spannableDescription = SpannableString("$userName $postDescription")
                spannableDescription.setSpan(ForegroundColorSpan(Color.BLACK), 0, userName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                holder.tvPostDescription.text = spannableDescription
                holder.tvCreatedAt.text = "$createdAt hours ago"


                holder.vpPhotos.adapter = PhotosPagerAdapter(images?.toList() ?: listOf())
                holder.ciPhotosIndicator.setViewPager(holder.vpPhotos)
            })
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flAccountAvatar = itemView.flAccountAvatar
        val profileAvatar = flAccountAvatar.ivPhotoToLoad
        val profileAvatarProgress = flAccountAvatar.pbPhotoProgress
        val tvAccount = itemView.tvAccount
        val tvProfileName = itemView.tvProfileName
        val tvLikedBy = itemView.tvLikedBy
        val tvPostDescription = itemView.tvPostDescription
        val tvCreatedAt = itemView.tvCreatedAt
        val ciPhotosIndicator = itemView.ciPhotosIndicator
        val vpPhotos = itemView.vpPhotos
    }

}
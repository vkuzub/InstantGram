package com.vkuzub.instantgram.view.mainList

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.vkuzub.instantgram.R
import com.vkuzub.instantgram.utils.extensions.glide
import com.vkuzub.instantgram.utils.extensions.inflate
import kotlinx.android.synthetic.main.photo_progress.view.*

class PhotosPagerAdapter(val photos: List<String>) : PagerAdapter() {

    override fun getCount() = photos.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val flPhotoProgress = container.inflate(R.layout.photo_progress, false)

        val profileAvatar = flPhotoProgress.ivPhotoToLoad
        val profileAvatarProgress = flPhotoProgress.pbPhotoProgress
        profileAvatar.glide(profileAvatarProgress, photos[position]
                ?: "", true, null, R.drawable.cloud_off)

        container.addView(flPhotoProgress)
        return flPhotoProgress
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View)
            container.removeView(`object`)
    }
}
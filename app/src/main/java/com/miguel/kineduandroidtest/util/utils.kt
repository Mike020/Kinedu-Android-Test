package com.miguel.kineduandroidtest.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.miguel.kineduandroidtest.R

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}


fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

fun changeHeader (html : String) : String {

    val head = "<head><meta name=\"viewport\" content=\"width=device-width, user-scalable=yes\" /></head>"
    val closedTag = "</body></html>"
    val formattedUrl = head + html + closedTag
    return formattedUrl

}
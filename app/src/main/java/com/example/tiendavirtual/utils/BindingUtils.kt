package com.example.tiendavirtual.utils

import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.tiendavirtual.R


@BindingAdapter("imageUrl")
fun ImageView.loadByUrl(url: String) {
    val anchoPantalla = context.resources.displayMetrics.widthPixels

    val options = RequestOptions()
        .override(anchoPantalla/2, anchoPantalla/2)

    Glide.with(this)
        .load(url)
        .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
@BindingAdapter("favorite")
fun ImageButton.setFavorite(esFavorite: Boolean){
    val buttonRes = if (esFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
    setImageResource(buttonRes)
}

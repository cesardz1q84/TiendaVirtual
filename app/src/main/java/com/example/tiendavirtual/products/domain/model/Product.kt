package com.example.tiendavirtual.products.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Product(
    @PrimaryKey @SerializedName("objectId") val id: String,
    val productName: String,
    val price: String,
    val imageUrl: String,
    var favorite: Boolean
):Parcelable
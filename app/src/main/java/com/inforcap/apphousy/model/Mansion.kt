package com.inforcap.apphousy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mansion(
    val id: Int,
    val name: String,
    val price: Int,
    val photo: String,
    val size: Int,
    val renovation: Boolean,
    val credit: Boolean,
    val description: String,
    val cause: String
) : Parcelable

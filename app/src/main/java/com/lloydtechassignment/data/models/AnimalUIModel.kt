package com.lloydtechassignment.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class  AnimalUIModel(
    var id: Int,
    var name: String,
    var isFav: Int,
    var habitat: String,
    var imageLink: String
) : Parcelable
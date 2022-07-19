package com.lloydtechassignment.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AnimalsRespItem(
    var active_time: String?,
    var animal_type: String?,
    var diet: String?,
    var geo_range: String?,
    var habitat: String?,
    var id: Int?,
    var image_link: String?,
    var latin_name: String?,
    var length_max: String?,
    var length_min: String?,
    var lifespan: String?,
    var name: String?,
    var weight_max: String?,
    var weight_min: String?
) : Parcelable



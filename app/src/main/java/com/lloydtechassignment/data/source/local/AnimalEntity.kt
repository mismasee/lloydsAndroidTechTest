package com.lloydtechassignment.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal")
data class  AnimalEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "isFav")
    var isFav: Int,

    @ColumnInfo(name = "geoRange")
    var habitat: String,

    @ColumnInfo(name = "imageLink")
    var imageLink: String
)
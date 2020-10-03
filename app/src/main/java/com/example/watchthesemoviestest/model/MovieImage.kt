package com.example.watchthesemoviestest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image_table")
class MovieImage(
    @SerializedName("images")
    val movieImages: Images,
    @SerializedName("change_keys")
    val changeKeys: List<String>
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}
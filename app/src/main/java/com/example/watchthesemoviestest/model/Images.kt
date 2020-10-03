package com.example.watchthesemoviestest.model

import com.google.gson.annotations.SerializedName

class Images(
    @SerializedName("base_url")
    val baseURL: String,
    @SerializedName("secure_base_url")
    val secureBaseURL: String,
    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>,
    @SerializedName("logo_sizes")
    val logoSizes: List<String>,
    @SerializedName("poster_sizes")
    val posterSizes: List<String>,
    @SerializedName("profile_sizes")
    val profileSizes: List<String>,
    @SerializedName("still_sizes")
    val stillSizes: List<String>
)
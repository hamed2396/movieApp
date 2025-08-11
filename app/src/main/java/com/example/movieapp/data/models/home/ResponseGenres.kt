package com.example.movieapp.data.models.home


import com.google.gson.annotations.SerializedName

data class ResponseGenres(
    @SerializedName("genres")
    var genres: List<Genre>?
) {
    data class Genre(
        @SerializedName("id")
        var id: Int?, // 28
        @SerializedName("name")
        var name: String? // Action
    )
}
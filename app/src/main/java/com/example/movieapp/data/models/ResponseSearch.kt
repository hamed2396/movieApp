package com.example.movieapp.data.models


import com.google.gson.annotations.SerializedName

data class ResponseSearch(
    @SerializedName("page")
    var page: Int?, // 1
    @SerializedName("results")
    var results: List<Result>?,
    @SerializedName("total_pages")
    var totalPages: Int?, // 2
    @SerializedName("total_results")
    var totalResults: Int? // 28
) {
    data class Result(
        @SerializedName("adult")
        var adult: Boolean?, // false
        @SerializedName("backdrop_path")
        var backdropPath: String?, // /enNubozHn9pXi0ycTVYUWfpHZm.jpg
        @SerializedName("genre_ids")
        var genreIds: List<Int?>?,
        @SerializedName("id")
        var id: Int?, // 155
        @SerializedName("original_language")
        var originalLanguage: String?, // en
        @SerializedName("original_title")
        var originalTitle: String?, // The Dark Knight
        @SerializedName("overview")
        var overview: String?, // Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.
        @SerializedName("popularity")
        var popularity: Double?, // 24.5128
        @SerializedName("poster_path")
        var posterPath: String?, // /qJ2tW6WMUDux911r6m7haRef0WH.jpg
        @SerializedName("release_date")
        var releaseDate: String?, // 2008-07-16
        @SerializedName("title")
        var title: String?, // The Dark Knight
        @SerializedName("video")
        var video: Boolean?, // false
        @SerializedName("vote_average")
        var voteAverage: Double?, // 8.5
        @SerializedName("vote_count")
        var voteCount: Int? // 34208
    )
}
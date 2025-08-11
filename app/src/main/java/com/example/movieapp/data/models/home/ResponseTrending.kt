package com.example.movieapp.data.models.home


import com.google.gson.annotations.SerializedName

data class ResponseTrending(
    @SerializedName("page")
    var page: Int?, // 1
    @SerializedName("results")
    var results: List<Result>?,
    @SerializedName("total_pages")
    var totalPages: Int?, // 500
    @SerializedName("total_results")
    var totalResults: Int? // 10000
) {
    data class Result(
        @SerializedName("adult")
        var adult: Boolean?, // false
        @SerializedName("backdrop_path")
        var backdropPath: String?, // /kyqM6padQzZ1eYxv84i9smNvZAG.jpg
        @SerializedName("genre_ids")
        var genreIds: List<Int?>?,
        @SerializedName("id")
        var id: Int?, // 1078605
        @SerializedName("media_type")
        var mediaType: String?, // movie
        @SerializedName("original_language")
        var originalLanguage: String?, // en
        @SerializedName("original_title")
        var originalTitle: String?, // Weapons
        @SerializedName("overview")
        var overview: String?, // When all but one child from the same class mysteriously vanish on the same night at exactly the same time, a community is left questioning who or what is behind their disappearance.
        @SerializedName("popularity")
        var popularity: Double?, // 249.1853
        @SerializedName("poster_path")
        var posterPath: String?, // /cpf7vsRZ0MYRQcnLWteD5jK9ymT.jpg
        @SerializedName("release_date")
        var releaseDate: String?, // 2025-08-06
        @SerializedName("title")
        var title: String?, // Weapons
        @SerializedName("video")
        var video: Boolean?, // false
        @SerializedName("vote_average")
        var voteAverage: Double?, // 7.8
        @SerializedName("vote_count")
        var voteCount: Int? // 200
    )
}
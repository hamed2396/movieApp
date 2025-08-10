package com.example.movieapp.data.models.home

import com.google.gson.annotations.SerializedName

data class ResponseTopRated(
    @SerializedName("page")
    var page: Int?, // 1
    @SerializedName("results")
    var results: List<Result?>?,
    @SerializedName("total_pages")
    var totalPages: Int?, // 514
    @SerializedName("total_results")
    var totalResults: Int? // 10273
) {
    data class Result(
        @SerializedName("adult")
        var adult: Boolean?, // false
        @SerializedName("backdrop_path")
        var backdropPath: String?, // /zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg
        @SerializedName("genre_ids")
        var genreIds: List<Int?>?,
        @SerializedName("id")
        var id: Int?, // 278
        @SerializedName("original_language")
        var originalLanguage: String?, // en
        @SerializedName("original_title")
        var originalTitle: String?, // The Shawshank Redemption
        @SerializedName("overview")
        var overview: String?, // Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.
        @SerializedName("popularity")
        var popularity: Double?, // 31.9922
        @SerializedName("poster_path")
        var posterPath: String?, // /9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg
        @SerializedName("release_date")
        var releaseDate: String?, // 1994-09-23
        @SerializedName("title")
        var title: String?, // The Shawshank Redemption
        @SerializedName("video")
        var video: Boolean?, // false
        @SerializedName("vote_average")
        var voteAverage: Double?, // 8.712
        @SerializedName("vote_count")
        var voteCount: Int? // 28661
    )
}
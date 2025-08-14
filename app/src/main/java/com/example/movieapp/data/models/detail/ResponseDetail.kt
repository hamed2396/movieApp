package com.example.movieapp.data.models.detail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
@Parcelize
data class ResponseDetail(
    @SerializedName("adult")
    var adult: Boolean?, // false
    @SerializedName("backdrop_path")
    var backdropPath: String?, // /kqHypb4MdEBUFiphf49bK99T4cn.jpg
    @SerializedName("budget")
    var budget: Int?, // 5
    @SerializedName("homepage")
    var homepage: String?, // https://www.amazon.com/gp/video/detail/B0DMF7MXKT
    @SerializedName("id")
    var id: Int?, // 755898
    @SerializedName("imdb_id")
    var imdbId: String?, // tt13186306
    @SerializedName("origin_country")
    var originCountry:@RawValue List<String>?,
    @SerializedName("original_language")
    var originalLanguage: String?, // en
    @SerializedName("original_title")
    var originalTitle: String?, // War of the Worlds
    @SerializedName("overview")
    var overview: String?, // Will Radford is a top analyst for Homeland Security who tracks potential threats through a mass surveillance program, until one day an attack by an unknown entity leads him to question whether the government is hiding something from him... and from the rest of the world.
    @SerializedName("popularity")
    var popularity: Double?, // 1604.146
    @SerializedName("poster_path")
    var posterPath: String?, // /yvirUYrva23IudARHn3mMGVxWqM.jpg
    @SerializedName("release_date")
    var releaseDate: String?, // 2025-07-29
    @SerializedName("revenue")
    var revenue: Int?, // 0
    @SerializedName("runtime")
    var runtime: Int?, // 91
    @SerializedName("spoken_languages")
    var spokenLanguages: @RawValue List<SpokenLanguage?>?,
    @SerializedName("status")
    var status: String?, // Released
    @SerializedName("tagline")
    var tagline: String?, // Your data is deadly.
    @SerializedName("title")
    var title: String?, // War of the Worlds
    @SerializedName("video")
    var video: Boolean?, // false
    @SerializedName("vote_average")
    var voteAverage: Double?, // 4.259
    @SerializedName("vote_count")
    var voteCount: Int? // 286
): Parcelable {
    @Parcelize
    data class Genre(
        @SerializedName("id")
        var id: Int?, // 878
        @SerializedName("name")
        var name: String? // Science Fiction
    ): Parcelable
    @Parcelize
    data class ProductionCompany(
        @SerializedName("id")
        var id: Int?, // 33
        @SerializedName("logo_path")
        var logoPath: String?, // /6exxhPonOo0M995SAchY0ijpRao.png
        @SerializedName("name")
        var name: String?, // Universal Pictures
        @SerializedName("origin_country")
        var originCountry: String? // US
    ): Parcelable
    @Parcelize
    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        var iso31661: String?, // US
        @SerializedName("name")
        var name: String? // United States of America
    ): Parcelable
    @Parcelize
    data class SpokenLanguage(
        @SerializedName("english_name")
        var englishName: String?, // English
        @SerializedName("iso_639_1")
        var iso6391: String?, // en
        @SerializedName("name")
        var name: String? // English
    ): Parcelable
}
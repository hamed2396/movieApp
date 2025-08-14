package com.example.movieapp.data.models.detail


import com.google.gson.annotations.SerializedName

data class ResponseMovieActors(
    @SerializedName("cast")
    var cast: List<Cast>?,
    @SerializedName("crew")
    var crew: List<Crew?>?,
    @SerializedName("id")
    var id: Int? // 278
) {
    data class Cast(
        @SerializedName("adult")
        var adult: Boolean?, // false
        @SerializedName("cast_id")
        var castId: Int?, // 3
        @SerializedName("character")
        var character: String?, // Andy Dufresne
        @SerializedName("credit_id")
        var creditId: String?, // 52fe4231c3a36847f800b131
        @SerializedName("gender")
        var gender: Int?, // 2
        @SerializedName("id")
        var id: Int?, // 504
        @SerializedName("known_for_department")
        var knownForDepartment: String?, // Acting
        @SerializedName("name")
        var name: String?, // Tim Robbins
        @SerializedName("order")
        var order: Int?, // 0
        @SerializedName("original_name")
        var originalName: String?, // Tim Robbins
        @SerializedName("popularity")
        var popularity: Double?, // 3.8693
        @SerializedName("profile_path")
        var profilePath: String? // /djLVFETFTvPyVUdrd7aLVykobof.jpg
    )

    data class Crew(
        @SerializedName("adult")
        var adult: Boolean?, // false
        @SerializedName("credit_id")
        var creditId: String?, // 52fe4231c3a36847f800b165
        @SerializedName("department")
        var department: String?, // Production
        @SerializedName("gender")
        var gender: Int?, // 0
        @SerializedName("id")
        var id: Int?, // 6583
        @SerializedName("job")
        var job: String?, // Casting
        @SerializedName("known_for_department")
        var knownForDepartment: String?, // Production
        @SerializedName("name")
        var name: String?, // Julie Lichter
        @SerializedName("original_name")
        var originalName: String?, // Julie Lichter
        @SerializedName("popularity")
        var popularity: Double?, // 1.8431
        @SerializedName("profile_path")
        var profilePath: String? // /pZUcIBbaWsX64d5Qy3SyGKcl88U.jpg
    )
}
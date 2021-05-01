package br.com.guiabolso.chucknorris.data.remote.response


import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("value")
    val value: String
)
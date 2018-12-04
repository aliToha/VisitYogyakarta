package com.muthohhari.visityogyakarta.models

import com.google.gson.annotations.SerializedName

data class Pariwisata(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("result")
    var result: String
)
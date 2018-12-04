package com.muthohhari.visityogyakarta.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("alamat_pariwisata")
    var alamatPariwisata: String,
    @SerializedName("detail_pariwisata")
    var detailPariwisata: String,
    @SerializedName("gambar_pariwisata")
    var gambarPariwisata: String,
    @SerializedName("nama_pariwisata")
    var namaPariwisata: String
) : Parcelable
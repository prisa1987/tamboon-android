package com.omise.tamboon.data.entity

import com.google.gson.annotations.SerializedName

data class Charity (

    var id: String = "",

    var name: String = "",

    @SerializedName("logo_url")
    var logoURL: String? = null
)
package com.omise.tamboon.data.entity

import com.google.gson.annotations.SerializedName

data class Card(

        var id: String = "",

        var name: String = "",

        @SerializedName("last_digits")
        var lastDigits:String = "",

        var brand: String = "",

        @SerializedName("expiration_month")
        var expireMonth: Int = 0,

        @SerializedName("expiration_year")
        var expireYear: Int = 0
)
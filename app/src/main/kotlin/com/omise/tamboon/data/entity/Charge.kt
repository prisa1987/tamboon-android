package com.omise.tamboon.data.entity

data class Charge (

        var id: String? = null,
        var transaction: String = "",
        var amount: Double = 0.00,
        var currency: String = "",
        var card: Card = Card()

)
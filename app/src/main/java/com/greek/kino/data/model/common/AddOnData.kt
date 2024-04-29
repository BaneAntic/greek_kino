package com.greek.kino.data.model.common

import com.greek.kino.domain.model.AddOn
import com.squareup.moshi.Json

data class AddOnData(
    @Json(name = "amount")
    val amount: Double?,
    @Json(name = "gameType")
    val gameType: String?,
)

fun AddOnData.toAddOn() = AddOn(amount, gameType)

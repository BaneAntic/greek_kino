package com.greek.kino.data.model.common

import com.greek.kino.domain.model.PricePoints
import com.squareup.moshi.Json

data class PricePointsData(
    @Json(name = "addOn")
    val addOn: List<AddOnData?>?,
    @Json(name = "amount")
    val amount: Double?,
)

fun PricePointsData.toPricePoints() = PricePoints(addOn?.map { it?.toAddOn() }, amount)

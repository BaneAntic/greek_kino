package com.greek.kino.data.model.common

import com.greek.kino.domain.model.PrizeCategory
import com.squareup.moshi.Json

data class PrizeCategoryData(
    @Json(name = "categoryType")
    val categoryType: Int?,
    @Json(name = "distributed")
    val distributed: Double?,
    @Json(name = "divident")
    val divident: Double?,
    @Json(name = "fixed")
    val fixed: Double?,
    @Json(name = "gameType")
    val gameType: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "jackpot")
    val jackpot: Int?,
    @Json(name = "winners")
    val winners: Int?,
)

fun PrizeCategoryData.toPrizeCategory() = PrizeCategory(categoryType, distributed, divident, fixed, gameType, id, jackpot, winners)

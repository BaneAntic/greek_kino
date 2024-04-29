package com.greek.kino.view.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.greek.kino.R

sealed class NavItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {
    object UpcomingGames :
        NavItem(
            route = "upcoming",
            R.string.upcoming_games,
            R.drawable.ic_talon,
        )

    object Talon :
        NavItem(
            "talon/{drawId}",
            R.string.talon,
            R.drawable.ic_talon,
        )

    object Live :
        NavItem(
            "live",
            R.string.live,
            R.drawable.ic_live,
        )

    object Results :
        NavItem(
            "results",
            R.string.results,
            R.drawable.ic_history,
        )

    companion object {
        val allValues = listOf(Talon, Live, Results)
    }
}

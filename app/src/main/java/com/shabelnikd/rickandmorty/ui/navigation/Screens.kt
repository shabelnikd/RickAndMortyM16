package com.shabelnikd.rickandmorty.ui.navigation

import com.shabelnikd.rickandmorty.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screens {
    @Serializable
    data object CharactersListScreen : Screens

    @Serializable data class CharacterDetails(val id: Int)

    @Serializable
    data object EpisodesListScreen : Screens
}


data class BottomNavItem<T : Any>(
    val title: String,
    val route: T,
    val iconResId: Int
)


val bottomNavItems = listOf(
    BottomNavItem(
        title = "Персонажи",
        route = Screens.CharactersListScreen,
        iconResId = R.drawable.ic_characters,
    ),
    BottomNavItem(
        title = "Эпизоды",
        route = Screens.EpisodesListScreen,
        iconResId = R.drawable.ic_episodes,
    )
)

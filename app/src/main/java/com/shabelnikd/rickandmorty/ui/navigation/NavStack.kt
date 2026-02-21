package com.shabelnikd.rickandmorty.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shabelnikd.character.ui.characters.CharacterListScreen
import com.shabelnikd.character.ui.characters.detail.CharacterDetailScreen
import com.shabelnikd.episode.ui.episodes.EpisodeListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavStack() {
    val navController = rememberNavController()
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            NavBar(
                navController = navController, modifier = Modifier,
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Screens.CharactersListScreen
        ) {
            composable<Screens.CharactersListScreen> {
                CharacterListScreen(
                    onCharacterClick = { characterId ->
                        navController.navigate(Screens.CharacterDetails(id = characterId))
                    }
                )
            }

            dialog<Screens.CharacterDetails> { backStackEntry ->
                val route = backStackEntry.toRoute<Screens.CharacterDetails>()
                val characterId = route.id

                CharacterDetailScreen(
                    characterId = characterId
                )
            }

            composable<Screens.EpisodesListScreen> {
                EpisodeListScreen()
            }
        }
    }
}
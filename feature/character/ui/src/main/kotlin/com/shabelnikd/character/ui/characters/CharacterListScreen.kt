package com.shabelnikd.character.ui.characters

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.shabelnikd.character.ui.components.CharacterListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<CharacterListViewModel>()
    val characterList = viewModel.pagingData.collectAsLazyPagingItems()
    val listState = rememberLazyListState()


    Scaffold(modifier = modifier) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding), state = listState) {
            items(count = characterList.itemCount) { index ->
                val item = characterList[index]

                item?.let { character ->
                    CharacterListItem(
                        character = character
                    )
                }
            }
        }
    }
}
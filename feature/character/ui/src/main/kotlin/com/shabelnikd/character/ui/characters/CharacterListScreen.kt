package com.shabelnikd.character.ui.characters

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.shabelnikd.character.ui.components.CharacterListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(modifier: Modifier = Modifier, onCharacterClick: (Int) -> Unit) {
    val viewModel = koinViewModel<CharacterListViewModel>()
    val characterList = viewModel.pagingData.collectAsLazyPagingItems()
    val listState = rememberLazyListState()



    LazyColumn(state = listState) {
        items(count = characterList.itemCount) { index ->
            val item = characterList[index]
            item?.let { character ->
                CharacterListItem(
                    character = character,
                    onClick = {
                        onCharacterClick(character.id)
                    }
                )
            }
        }

    }
}
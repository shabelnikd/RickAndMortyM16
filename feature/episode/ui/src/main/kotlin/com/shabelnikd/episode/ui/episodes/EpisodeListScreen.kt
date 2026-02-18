package com.shabelnikd.episode.ui.episodes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.shabelnikd.episode.ui.components.EpisodeListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeListScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<EpisodeListViewModel>()
    val episodeList = viewModel.pagingData.collectAsLazyPagingItems()
    val listState = rememberLazyListState()


    Scaffold(modifier = modifier) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding), state = listState) {
            items(count = episodeList.itemCount) { index ->
                val item = episodeList[index]

                item?.let { episode ->
                    EpisodeListItem(
                        episode = episode
                    )
                }
            }
        }
    }
}
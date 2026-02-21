package com.shabelnikd.episode.ui.episodes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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


    LazyColumn(state = listState) {
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
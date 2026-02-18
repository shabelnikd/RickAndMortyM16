package com.shabelnikd.episode.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shabelnikd.episode.domain.usecase.GetEpisodePageSourceUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class EpisodeListViewModel(
    private val getEpisodePageSourceUseCase: GetEpisodePageSourceUseCase
) : ViewModel() {

//    val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    val pagingData = getEpisodePageSourceUseCase(page = 1)
        .cachedIn(viewModelScope)
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, initialValue = PagingData.empty())

}
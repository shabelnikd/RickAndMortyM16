package com.shabelnikd.character.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shabelnikd.character.domain.model.CharacterParams
import com.shabelnikd.character.domain.usecase.GetCharactersPageSourceUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CharacterListViewModel(
    private val getCharactersPageSourceUseCase: GetCharactersPageSourceUseCase
) : ViewModel() {

//    val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    val pagingData = getCharactersPageSourceUseCase(CharacterParams(page = 1))
        .cachedIn(viewModelScope)
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, initialValue = PagingData.empty())

}
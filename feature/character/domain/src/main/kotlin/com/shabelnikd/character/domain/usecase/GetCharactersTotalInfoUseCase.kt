package com.shabelnikd.character.domain.usecase

import com.shabelnikd.character.domain.model.Info
import com.shabelnikd.character.domain.repository.CharacterRepository
import com.shabelnikd.core.network.model.AppError
import com.shabelnikd.core.network.model.DataResult

class GetCharactersTotalInfoUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): DataResult<Info, AppError> {
        return repository.getCharactersTotalInfo()
    }
}
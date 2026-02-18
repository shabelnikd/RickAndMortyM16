package com.shabelnikd.character.domain.di

import com.shabelnikd.character.domain.usecase.GetCharacterByIdUseCase
import com.shabelnikd.character.domain.usecase.GetCharactersByIdsUseCase
import com.shabelnikd.character.domain.usecase.GetCharactersPageSourceUseCase
import com.shabelnikd.character.domain.usecase.GetCharactersTotalInfoUseCase
import com.shabelnikd.character.domain.usecase.GetCharactersUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val characterDomainModule = module {
    factoryOf(::GetCharactersUseCase)
    factoryOf(::GetCharacterByIdUseCase)
    factoryOf(::GetCharactersByIdsUseCase)
    factoryOf(::GetCharactersPageSourceUseCase)
    factoryOf(::GetCharactersTotalInfoUseCase)
}
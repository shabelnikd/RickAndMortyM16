package com.shabelnikd.rickandmorty.domain.di

import com.shabelnikd.rickandmorty.domain.usecase.GetCharacterByIdUseCase
import com.shabelnikd.rickandmorty.domain.usecase.GetCharactersByIdsUseCase
import com.shabelnikd.rickandmorty.domain.usecase.GetCharactersPageSourceUseCase
import com.shabelnikd.rickandmorty.domain.usecase.GetCharactersTotalInfoUseCase
import com.shabelnikd.rickandmorty.domain.usecase.GetCharactersUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCharactersUseCase)
    factoryOf(::GetCharacterByIdUseCase)
    factoryOf(::GetCharactersByIdsUseCase)
    factoryOf(::GetCharactersPageSourceUseCase)
    factoryOf(::GetCharactersTotalInfoUseCase)
}
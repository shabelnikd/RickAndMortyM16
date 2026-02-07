package com.shabelnikd.rickandmorty.data.di

import com.shabelnikd.rickandmorty.data.repository.RealCharacterRepository
import com.shabelnikd.rickandmorty.domain.repository.CharacterRepository
import org.koin.dsl.module

val dataModule = module {
    single<CharacterRepository> { RealCharacterRepository(get()) }
}
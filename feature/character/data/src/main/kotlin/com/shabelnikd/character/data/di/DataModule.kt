package com.shabelnikd.character.data.di

import com.shabelnikd.character.data.repository.RealCharacterRepository
import com.shabelnikd.character.domain.repository.CharacterRepository
import org.koin.dsl.module

val characterDataModule = module {
    single<CharacterRepository> { RealCharacterRepository(get()) }
}
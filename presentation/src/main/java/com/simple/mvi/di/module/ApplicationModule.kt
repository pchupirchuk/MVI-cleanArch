package com.simple.mvi.di.module

import com.simple.data.managers.CharactersManager
import com.simple.data.managers.CharactersManagerImpl
import org.koin.dsl.module

val applicationModule = module {

    single<CharactersManager> { CharactersManagerImpl(get()) }
}
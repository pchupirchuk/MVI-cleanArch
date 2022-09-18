package com.simple.mvi.di.module

import com.simple.data.managers.CharactersManager
import com.simple.data.managers.CharactersManagerImpl
import com.simple.mvi.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activityModule = module {

    single<CharactersManager> {
        CharactersManagerImpl(get())
    }

    viewModel {
        HomeViewModel(get())
    }
}
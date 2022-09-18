package com.simple.mvi.features.home

import com.simple.data.managers.CharactersManager
import com.simple.mvi.common.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Rim Gazzah on 8/26/20.
 **/
class HomeViewModel @Inject constructor(private val dataManager: CharactersManager) :
    BaseViewModel<HomeIntent, HomeAction, HomeState>() {
    override fun intentToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.LoadAllCharacters -> HomeAction.AllCharacters
            is HomeIntent.ClearSearch -> HomeAction.AllCharacters
            is HomeIntent.SearchCharacter -> HomeAction.SearchCharacters(intent.name)
            is HomeIntent.ViewCharacter -> HomeAction.GetCharacterInfo(intent.persona)
        }
    }


    override fun handleAction(action: HomeAction) {
        launchOnUI {
            when (action) {
                is HomeAction.AllCharacters -> {
                    dataManager.getAllCharacters().collect {
                        mState.postValue(it.reduce())
                    }
                }
                is HomeAction.SearchCharacters -> {
                    dataManager.searchCharacters(action.name).collect {
                        mState.postValue(it.reduce(true))
                    }
                }
                is HomeAction.GetCharacterInfo -> {
                    // could get additional data from dataManager here
                    mState.postValue(HomeState.Details(action.persona))
                }
            }
        }
    }
}
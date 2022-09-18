package com.simple.mvi.features.home

import com.simple.domain.entities.Persona
import com.simple.mvi.common.ViewAction

/**
 * Created by Rim Gazzah on 8/26/20.
 **/
sealed class HomeAction : ViewAction {
    data class SearchCharacters(val name: String) : HomeAction()
    object AllCharacters : HomeAction()
    data class GetCharacterInfo(val persona: Persona) : HomeAction()
}
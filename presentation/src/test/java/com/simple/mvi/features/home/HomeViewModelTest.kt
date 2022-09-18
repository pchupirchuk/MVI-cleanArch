package com.simple.mvi.features.home

import com.simple.data.managers.CharactersManager
import com.simple.domain.entities.Persona
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(mock(CharactersManager::class.java))
    }

    @Test
    fun `LoadAllCharacters intent should be interpreted as AllCharacters action`() {
        val action = viewModel.intentToAction(HomeIntent.LoadAllCharacters)
        assertEquals(HomeAction.AllCharacters, action)
    }

    @Test
    fun `ClearSearch intent should be interpreted as AllCharacters action`() {
        val action = viewModel.intentToAction(HomeIntent.ClearSearch)
        assertEquals(HomeAction.AllCharacters, action)
    }

    @Test
    fun `SearchCharacter intent should be interpreted as SearchCharacters action`() {
        val action = viewModel.intentToAction(HomeIntent.SearchCharacter("query"))
        assertEquals(HomeAction.SearchCharacters("query"), action)
    }

    @Test
    fun `ViewCharacter intent should be interpreted as GetCharacterInfo action`() {
        val persona = Persona(1,"","","","","")
        val intent = HomeIntent.ViewCharacter(persona)

        val action = viewModel.intentToAction(intent)
        assertEquals(HomeAction.GetCharacterInfo(persona), action)
    }
}
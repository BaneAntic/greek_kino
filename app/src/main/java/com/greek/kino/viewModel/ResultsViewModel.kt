package com.greek.kino.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greek.kino.domain.model.GameDetails
import com.greek.kino.domain.model.UpcomingGameDetails
import com.greek.kino.domain.useCase.GetGameDetailsUseCase
import com.greek.kino.domain.useCase.GetGameResultsUseCase
import com.greek.kino.domain.useCase.GetRandomSelectedNumbersUseCase
import com.greek.kino.domain.useCase.GetUpcomingGamesUseCase
import com.greek.kino.domain.useCase.UploadPlayedNumbersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel
    @Inject
    constructor(
        private val getGameResultsUseCase: GetGameResultsUseCase,
        private val getUpcomingGamesUseCase: GetUpcomingGamesUseCase,
        private val getGameDetailsUseCase: GetGameDetailsUseCase,
        private val getRandomSelectedNumbersUseCase: GetRandomSelectedNumbersUseCase,
        private val uploadPlayedNumbersUseCase: UploadPlayedNumbersUseCase,
    ) : ViewModel() {
        var gameResults: MutableStateFlow<List<GameDetails>> = MutableStateFlow(emptyList())
            private set

        var upcomingGameResults: MutableStateFlow<List<UpcomingGameDetails>> =
            MutableStateFlow(emptyList())
            private set

        var gameDetailsResult: MutableStateFlow<GameDetails?> = MutableStateFlow(null)
            private set

        var selectedNumbers: MutableStateFlow<List<Int>> = MutableStateFlow(listOf())
            private set

        var selectedDraw: Int? = null

        var selectedNumberCount: MutableStateFlow<Int> = MutableStateFlow(5)
            private set

        private val manuallySelectedNumbers: MutableList<Int> = mutableListOf()

        fun loadResultsFor(date: String) {
            viewModelScope.launch {
                gameResults.value =
                    getGameResultsUseCase.invoke(date)
                        .stateIn(viewModelScope).value
            }
        }

        fun loadGameDetailsFor(drawId: Int?) {
            viewModelScope.launch {
                selectedDraw = drawId
                drawId?.let {
                    gameDetailsResult.value =
                        getGameDetailsUseCase.invoke(it).stateIn(viewModelScope).value
                }
            }
        }

        fun loadUpcomingGames() {
            viewModelScope.launch {
                upcomingGameResults.value =
                    getUpcomingGamesUseCase.invoke().stateIn(viewModelScope).value
            }
        }

        fun playPickedNumbers(drawId: Int) {
            viewModelScope.launch(Dispatchers.IO) {
                uploadPlayedNumbersUseCase.invoke(drawId, selectedNumbers.value)
            }
        }

        fun getRandomSelectedNumbers(numberOfElements: Int) {
            viewModelScope.launch {
                selectedNumbers.value = getRandomSelectedNumbersUseCase.invoke(numberOfElements)
                manuallySelectedNumbers.clear()
                manuallySelectedNumbers.addAll(selectedNumbers.value)
            }
        }

        fun updateNumberOfPossibleItems(numberOfItems: Int) = viewModelScope.launch { selectedNumberCount.emit(numberOfItems) }

        fun addSelectedNumberInList(number: Int): Boolean =
            if (manuallySelectedNumbers.size < selectedNumberCount.value) {
                manuallySelectedNumbers.add(number)
                selectedNumbers.value = manuallySelectedNumbers.toList()
                true
            } else {
                false
            } // Add message to user that he exceeded max

        fun removeSelectedNumberFromList(number: Int): Boolean {
            manuallySelectedNumbers.remove(number)
            selectedNumbers.value = manuallySelectedNumbers.toList()
            return true
        }

        fun clearSelectedValues() {
            manuallySelectedNumbers.clear()
            selectedNumbers.value = emptyList()
        }
    }

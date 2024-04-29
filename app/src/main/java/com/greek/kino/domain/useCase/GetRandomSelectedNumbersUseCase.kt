package com.greek.kino.domain.useCase

import com.greek.kino.data.util.GameConstants
import javax.inject.Inject

class GetRandomSelectedNumbersUseCase
    @Inject
    constructor() {
        operator fun invoke(numberOfElements: Int): List<Int> {
            if (numberOfElements < 1 || numberOfElements > 15) throw ArrayIndexOutOfBoundsException()

            val shuffledTalon = GameConstants.GreekKinoTalon.shuffled()
            return shuffledTalon.take(numberOfElements)
        }
    }

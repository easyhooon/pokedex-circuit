package com.easyhooon.pokedex.di.module

import com.easyhooon.pokedex.di.factory.CircuitPresenterFactory
import com.easyhooon.pokedex.di.factory.CircuitUiFactory
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class CircuitModule {
    @Factory(binds = [Presenter.Factory::class])
    fun circuitPresenterFactoryComponent() = CircuitPresenterFactory()

    @Factory(binds = [Ui.Factory::class])
    fun circuitUiFactoryComponent() =  CircuitUiFactory()

    @Single
    fun circuit(
        presenterFactory: CircuitPresenterFactory,
        uiFactory: CircuitUiFactory
    ) = Circuit.Builder()
        .addPresenterFactory(presenterFactory)
        .addUiFactory(uiFactory)
        .build()
}

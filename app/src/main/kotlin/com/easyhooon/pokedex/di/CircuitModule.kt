package com.easyhooon.pokedex.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

//@Module
//@InstallIn(ActivityRetainedComponent::class)
//abstract class CircuitModule {
//
//    @Multibinds
//    abstract fun presenterFactories(): Set<Presenter.Factory>
//
//    @Multibinds
//    abstract fun uiFactories(): Set<Ui.Factory>
//
//    companion object {
//        @[Provides ActivityRetainedScoped]
//        fun provideCircuit(
//            presenterFactories: @JvmSuppressWildcards Set<Presenter.Factory>,
//            uiFactories: @JvmSuppressWildcards Set<Ui.Factory>,
//        ): Circuit = Circuit.Builder()
//            .addPresenterFactories(presenterFactories)
//            .addUiFactories(uiFactories)
//            .build()
//    }
//}

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

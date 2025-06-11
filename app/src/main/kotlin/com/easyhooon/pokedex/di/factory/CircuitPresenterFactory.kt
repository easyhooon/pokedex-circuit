package com.easyhooon.pokedex.di.factory

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

class CircuitPresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*> {
        return when (screen) {
            is MainScreen -> MainPresenter(screen, navigator)
            is LoginScreen -> LoginPresenter(screen, navigator)
            else -> throw Exception("Invalid Screen Detected! :: $screen")
        }
    }
}

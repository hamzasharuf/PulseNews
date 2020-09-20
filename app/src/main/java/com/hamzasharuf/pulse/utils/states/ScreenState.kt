package com.hamzasharuf.pulse.utils.states

sealed class ScreenState {
    object NewsScreenState: ScreenState()
    object DetailsScreenState: ScreenState()
    object SettingsScreenState: ScreenState()
}
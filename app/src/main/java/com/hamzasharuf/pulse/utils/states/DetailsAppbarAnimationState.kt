package com.hamzasharuf.pulse.utils.states

sealed class DetailsAppbarAnimationState {
    object Processing: DetailsAppbarAnimationState()
    object Finished: DetailsAppbarAnimationState()
}
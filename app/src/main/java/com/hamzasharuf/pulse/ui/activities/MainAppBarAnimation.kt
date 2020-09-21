package com.hamzasharuf.pulse.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup

class MainAppBarAnimation {

    private var from: Int? = null
    private var to: Int? = null
    private var duration: Long = 500
    private lateinit var anim: ValueAnimator

    operator fun invoke(): MainAppBarAnimation {
        return this
    }

    fun initialHeight(value: Int): MainAppBarAnimation {
        from = value
        return this
    }

    fun finalHeight(value: Int): MainAppBarAnimation {
        to = value
        return this
    }

    fun duration(value: Long): MainAppBarAnimation {
        duration = value
        return this
    }

    fun applyAnimationOn(view: View): MainAppBarAnimation {

        // Check if all required information are available
        if (!requiredDataValidation())
            throw NullPointerException("Some values haven't been initialized")

        // Assign the start and end positions as well as the duration
        anim = ValueAnimator.ofInt(from!!, to!!)
        anim.duration = this.duration


        // Update the height of the View with the progress value of the animation
        anim.addUpdateListener { valueAnimator ->
            val x = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams =
                view.layoutParams
            layoutParams.height = x
            view.layoutParams = layoutParams
        }

        return this
    }

    fun onAnimationFinished(predicate: (() -> Unit)): MainAppBarAnimation {
        // If we have any thing to do after the animation is finished
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                predicate()
            }
        })
        return this
    }

    fun start() {
        anim.start()
    }

    private fun requiredDataValidation() = this.from != null && this.to != null

}
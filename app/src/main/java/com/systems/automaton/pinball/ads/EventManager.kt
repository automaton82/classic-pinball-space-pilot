package com.systems.automaton.pinball.ads

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics

class EventManager {

    var isInitialized: Boolean = false; private set
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun initialize(context: Context) {

        if (isInitialized) {
            throw IllegalStateException("Can't initialize an already initialized singleton.")
        }

        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        isInitialized = true
    }

    companion object {
        @JvmField
        val instance = EventManager()
    }
}
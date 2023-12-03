package com.example.lab1

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

class LanguageManager {
    enum class Locales(val locale: String) { Ukranian("uk"), English("en") }

    fun updateBaseContextLocale(
        context: Context,
        language: Locales
    ): Context {
        val locale = Locale(language.locale)
        Locale.setDefault(locale)
        updateResourcesLocale(context, locale)
        return updateResourcesLocaleLegacy(context, locale)
    }

    fun persistedLocale(context: Context): Locales {
        val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        return Locales.valueOf(sharedPreferences.getString("locale", "English") ?: "English")
    }

    fun persistLocale(context: Context, locale: Locales) {
        val sharedPreferencesEditor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
        sharedPreferencesEditor.putString("locale", locale.name)
        sharedPreferencesEditor.apply()
    }

    private fun updateResourcesLocale(
        context: Context,
        locale: Locale
    ): Context? {
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLocaleLegacy(
        context: Context,
        locale: Locale
    ): Context {
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)
        return context
    }
}
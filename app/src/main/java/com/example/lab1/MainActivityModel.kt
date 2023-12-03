package com.example.lab1

import android.content.Context
import androidx.lifecycle.ViewModel

class MainActivityModel: ViewModel() {
    fun changeLocaleButtonBehaviour(context: Context) {
        val langManager = LanguageManager()
        if (langManager.persistedLocale(context) == LanguageManager.Locales.English)
            langManager.persistLocale(context, LanguageManager.Locales.Ukranian)
        else langManager.persistLocale(context, LanguageManager.Locales.English)
    }
}
package com.example.lab1

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[MainActivityModel::class.java]

        binding.changeLocaleButton.setOnClickListener {
            viewModel.changeLocaleButtonBehaviour(this)
            this.recreate()
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val langManager = LanguageManager()
        val language = langManager.persistedLocale(newBase)
        super.attachBaseContext(langManager.updateBaseContextLocale(newBase, language))
    }
}
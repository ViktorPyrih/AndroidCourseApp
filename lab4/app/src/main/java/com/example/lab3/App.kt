package com.example.lab3

import android.app.Application
import com.example.lab3.db.ApplicationDatabase
import com.example.lab3.db.StoreRepository

class App : Application() {

    val appDatabase by lazy { ApplicationDatabase.getDatabase(this) }
    val repository by lazy { StoreRepository(appDatabase.staffDao(), appDatabase.productDao()) }
}
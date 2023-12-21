package com.example.lab3.models

import androidx.room.PrimaryKey

open class StoreEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
)
package com.example.lab3.models

import androidx.room.Entity

@Entity(tableName = "activities")
data class ActivityEntity (
    var activity: String = "",
    var type: String = "",
    var participants: Int = 0,
    var price: Float = 0.0f,
    var link: String = "",
    var key: String = "",
    var accessibility: Float = 0.0f
) : StoreEntity(){}
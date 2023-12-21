package com.example.lab3.models

import androidx.room.Entity

@Entity(tableName = "staffs")
data class Staff(
    var name: String = "",
    var salary: Float = 0.0f,
    var cv: String = ""
) : StoreEntity(){

    @Transient
    var products: List<Product> = listOf()
}
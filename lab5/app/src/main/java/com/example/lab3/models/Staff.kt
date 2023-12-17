package com.example.lab3.models

class Staff(
    id: Int = 0,
    name: String = "Default",
    var salary: Int = 0,
    var cv: String = "Employee cv"
) : StoreEntity(id, name)
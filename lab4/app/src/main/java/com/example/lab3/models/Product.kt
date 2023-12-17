package com.example.lab3.models

class Product(
    id: Int = 0,
    name: String = "Name",
    var price: Int = 100,
    var quantity: Int = 0,
    var description: String = "Description"
) : StoreEntity(id, name)
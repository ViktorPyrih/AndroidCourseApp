package com.example.lab3.delegates

import com.example.lab3.models.StoreEntity

interface EntityActionsListener {
    fun onDelete(item: StoreEntity)
}
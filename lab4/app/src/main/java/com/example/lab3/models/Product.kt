package com.example.lab3.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore

@Entity(tableName = "products", foreignKeys =
[
    ForeignKey(entity = Staff::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("id_staff"),
    onDelete = ForeignKey.SET_NULL)
])
data class Product (
    var name: String = "",
    var price: Float = 0.0f,
    var quantity: Int = 0,
    var description: String = "",

    @Ignore
    var staff : Staff? = null

) : StoreEntity(){
    @ColumnInfo(name = "id_staff")
    var idStaff : Long? = staff?.id
}

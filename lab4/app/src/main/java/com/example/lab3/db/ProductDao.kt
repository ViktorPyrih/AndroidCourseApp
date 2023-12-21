package com.example.lab3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lab3.models.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product) : Long

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: Long): Product

    @Query("SELECT * FROM products WHERE name = :name")
    suspend fun getByName(name: String): Product

    @Query("SELECT * FROM products WHERE id_staff = :id")
    suspend fun getByStaffId(id: Long): List<Product>

    @Query("DELETE FROM products")
    suspend fun deleteAll()

}

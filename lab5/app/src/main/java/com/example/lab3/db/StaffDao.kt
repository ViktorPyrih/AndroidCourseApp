package com.example.lab3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lab3.models.Staff

@Dao
interface StaffDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(staff: Staff) : Long

    @Update
    suspend  fun update(staff: Staff)

    @Delete
    suspend  fun delete(staff: Staff)

    @Query("SELECT * FROM staffs")
    suspend  fun getAll(): List<Staff>

    @Query("SELECT * FROM staffs WHERE id = :id")
    suspend  fun getById(id: Long): Staff

    @Query("SELECT * FROM staffs WHERE name = :name")
    suspend  fun getByName(name: String): Staff

    @Query("DELETE FROM staffs")
    suspend  fun deleteAll()

}

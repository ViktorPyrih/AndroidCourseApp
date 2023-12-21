package com.example.lab3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab3.models.Product
import com.example.lab3.models.Staff

@Database(entities = [Product::class, Staff::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun staffDao(): StaffDao

    companion object {
        private const val DATABASE_NAME = "store.db"

        @Volatile
        private var instance: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ApplicationDatabase {
            return Room.databaseBuilder(
                context,
                ApplicationDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
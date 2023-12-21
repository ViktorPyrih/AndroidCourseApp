package com.example.lab3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lab3.models.ActivityEntity
import com.example.lab3.models.Product
import com.example.lab3.models.Staff

@Database(entities = [Product::class, Staff::class, ActivityEntity::class], version = 2)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun staffDao(): StaffDao
    abstract fun activityDao(): ActivityDao

    companion object {
        private const val DATABASE_NAME = "store.db"

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE `activities` " +
                        "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`activity` TEXT NOT NULL, " +
                        "`type` TEXT NOT NULL, " +
                        "`participants` INTEGER NOT NULL, " +
                        "`price` REAL NOT NULL, " +
                        "`link` TEXT NOT NULL, " +
                        "`key` TEXT NOT NULL, " +
                        "`accessibility` REAL NOT NULL) ")
            }
        }

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
            ).addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}
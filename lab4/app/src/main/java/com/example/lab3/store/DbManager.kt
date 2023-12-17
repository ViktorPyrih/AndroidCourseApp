package com.example.lab3.store

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.lab3.models.Product
import com.example.lab3.models.Staff

class DbManager(context: Context?): SQLiteOpenHelper(context, "app_data", null, 1) {

    var onDbChanged: () -> Unit = {}

    companion object{
        const val TABLE_PRODUCT = "product"
        private const val COLUMN_PRODUCT_ID = "id"
        private const val COLUMN_PRODUCT_NAME = "name"
        private const val COLUMN_PRODUCT_PRICE = "price"
        private const val COLUMN_PRODUCT_QUANTITY = "quantity"
        private const val COLUMN_PRODUCT_DESCRIPTION = "description"

        const val TABLE_STAFF = "staff"
        private const val COLUMN_STAFF_ID = "id"
        private const val COLUMN_STAFF_NAME = "name"
        private const val COLUMN_STAFF_SALARY = "salary"
        private const val COLUMN_STAFF_CV = "cv"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TABLE_PRODUCT(" +
                "$COLUMN_PRODUCT_ID integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_PRODUCT_NAME text, " +
                "$COLUMN_PRODUCT_PRICE integer, " +
                "$COLUMN_PRODUCT_QUANTITY integer, " +
                "$COLUMN_PRODUCT_DESCRIPTION text" +
                ");")

        db?.execSQL("create table $TABLE_STAFF(" +
                "$COLUMN_STAFF_ID integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_STAFF_NAME text, " +
                "$COLUMN_STAFF_SALARY integer, " +
                "$COLUMN_STAFF_CV text" +
                ");")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_STAFF")
        onCreate(db)
    }

    fun saveProduct(product: Product): Boolean {
        val db = this.readableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_PRODUCT_NAME, product.name)
        contentValues.put(COLUMN_PRODUCT_PRICE, product.price)
        contentValues.put(COLUMN_PRODUCT_QUANTITY, product.quantity)
        contentValues.put(COLUMN_PRODUCT_DESCRIPTION, product.description)

        var res: Long = db.insert(TABLE_PRODUCT, null, contentValues)
        db.close()
        onDbChanged()
        return res > 0
    }

    fun getAllProducts(): ArrayList<Product> {
        val db = this.readableDatabase
        var products: ArrayList<Product> = arrayListOf()

        val cursor = db.rawQuery("select * from $TABLE_PRODUCT", null)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                var product = Product()

                val nameColumnIndex = cursor.getColumnIndex(COLUMN_PRODUCT_NAME)
                if (nameColumnIndex >= 0) product.name = cursor.getString(nameColumnIndex)

                val priceColumnIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)
                if (priceColumnIndex >= 0) product.price = cursor.getInt(priceColumnIndex)

                val quantityColumnIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY)
                if (quantityColumnIndex >= 0) product.quantity = cursor.getInt(quantityColumnIndex)

                val descriptionColumnIndex = cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)
                if (descriptionColumnIndex >= 0) product.description = cursor.getString(descriptionColumnIndex)

                products.add(product)
                cursor.moveToNext()
            }
        }
        return products
    }

    fun saveStaff(staff: Staff): Boolean {
        val db = this.readableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_STAFF_NAME, staff.name)
        contentValues.put(COLUMN_STAFF_SALARY, staff.salary)
        contentValues.put(COLUMN_STAFF_CV, staff.cv)

        var res: Long = db.insert(TABLE_STAFF, null, contentValues)
        db.close()
        onDbChanged()
        return res > 0
    }

    fun getAllStaff(): ArrayList<Staff> {
        val db = this.readableDatabase
        var staffs: ArrayList<Staff> = arrayListOf()

        val cursor = db.rawQuery("select * from $TABLE_STAFF", null)
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                var staff = Staff()

                val nameColumnIndex = cursor.getColumnIndex(COLUMN_STAFF_NAME)
                if (nameColumnIndex >= 0) staff.name = cursor.getString(nameColumnIndex)

                val salaryColumnIndex = cursor.getColumnIndex(COLUMN_STAFF_SALARY)
                if (salaryColumnIndex >= 0) staff.salary = cursor.getInt(salaryColumnIndex)

                val quantityColumnIndex = cursor.getColumnIndex(COLUMN_STAFF_CV)
                if (quantityColumnIndex >= 0) staff.cv = cursor.getString(quantityColumnIndex)

                staffs.add(staff)
                cursor.moveToNext()
            }
        }
        return staffs
    }

}
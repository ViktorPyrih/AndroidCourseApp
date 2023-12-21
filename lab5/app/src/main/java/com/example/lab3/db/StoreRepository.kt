package com.example.lab3.db

import com.example.lab3.api.ActivityApi
import com.example.lab3.api.ActivityRetrofitClient
import com.example.lab3.models.ActivityEntity
import com.example.lab3.models.Product
import com.example.lab3.models.Staff
import com.example.lab3.models.StoreEntity

class StoreRepository(private val staffDao : StaffDao,
                      private val productDao : ProductDao,
                      private val activityDao : ActivityDao) {

    private val activityRetrofitClient = ActivityRetrofitClient.getClient()
    private val activityApi = activityRetrofitClient.create(ActivityApi::class.java)

    suspend fun getStaffs() : List<Staff> {
        val staffs = staffDao.getAll()
        for (staff in staffs) {
            staff.products = productDao.getByStaffId(staff.id)
        }
        return staffs
    }

    suspend fun getProducts() : List<Product> {
        val products = productDao.getAll()
        for (product in products) {
            if (product.idStaff != null)
                product.staff = staffDao.getById(product.idStaff as Long)
        }
        return products
    }

    suspend fun loadFromApiAndSaveToDbActivities(count: Int){
        for (i in 1..count) {
            val response = activityApi.getActivity()
            if (!response.isSuccessful) {
                throw Exception("Error: ${response.code()}")
            }
            insertEntity(response.body() as ActivityEntity)
        }
    }

    suspend fun getActivities() : List<ActivityEntity> {
        var activities = activityDao.getAll()
        return activities
    }

    suspend fun getStoreEntities() : List<StoreEntity> {
        val staffs = getStaffs()
        val products = getProducts()
        val activities = getActivities()
        return activities.asSequence().plus(staffs).plus(products).toList()
    }

    suspend fun getSfaffById(staff1Id: Long): Staff {
        return staffDao.getById(staff1Id)
    }

    suspend fun getProductById(productId: Long): Product {
        return productDao.getById(productId)
    }

    suspend fun insertEntity(storeEntity: StoreEntity) : Long {
        val id = when (storeEntity) {
            is Staff -> staffDao.insert(storeEntity)
            is Product -> productDao.insert(storeEntity)
            is ActivityEntity -> activityDao.insert(storeEntity)
            else -> throw IllegalArgumentException("Unknown entity")
        }
        return id
    }

    suspend fun updateEntity(storeEntity: StoreEntity) {
        when (storeEntity) {
            is Staff -> staffDao.update(storeEntity)
            is Product -> productDao.update(storeEntity)
            is ActivityEntity -> activityDao.update(storeEntity)
            else -> throw IllegalArgumentException("Unknown entity")
        }
    }

    suspend fun deleteEntity(storeEntity: StoreEntity) {
        when (storeEntity) {
            is Staff -> staffDao.delete(storeEntity)
            is Product -> productDao.delete(storeEntity)
            is ActivityEntity -> activityDao.delete(storeEntity)
            else -> throw IllegalArgumentException("Unknown entity")
        }
    }

    suspend fun deleteAllStaffs() {
        staffDao.deleteAll()
    }

    suspend fun deleteAllProducts() {
        productDao.deleteAll()
    }

    suspend fun deleteAllActivities() {
        activityDao.deleteAll()
    }
}
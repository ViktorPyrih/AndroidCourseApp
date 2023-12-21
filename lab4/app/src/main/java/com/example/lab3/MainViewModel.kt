package com.example.lab3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab3.models.Product
import com.example.lab3.models.Staff
import com.example.lab3.models.StoreEntity
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = (application as App).repository

    private var _storeEntities : MutableLiveData<List<StoreEntity>> =
        MutableLiveData<List<StoreEntity>>().apply {
            value = emptyList()
        }
    val storeEntities : MutableLiveData<List<StoreEntity>> = _storeEntities

    init{
        viewModelScope.launch{
            repository.deleteAllStaffs();
            repository.deleteAllProducts();

            val staff1_id = repository.insertEntity(Staff("John", 1000.0f, "CV1 a lot of text text"))
            val staff2_id = repository.insertEntity(Staff("Jack", 2000.0f, "CV2 text text"))
            val staff3_id = repository.insertEntity(Staff("Jim", 3000.0f, "CV3 text"))
            val staff4_id = repository.insertEntity(Staff("Jill", 4000.0f, "CV4 text text text"))
            val product1_id = repository.insertEntity(Product("Bread", 10.0f, 100, "Bread description", repository.getSfaffById(staff1_id)))
            val product2_id = repository.insertEntity(Product("Milk", 20.0f, 200, "Milk description", repository.getSfaffById(staff3_id)))
            val product3_id = repository.insertEntity(Product("Meat", 30.0f, 300, "Meat description", repository.getSfaffById(staff3_id)))
            val product4_id = repository.insertEntity(Product("Fish", 40.0f, 400, "Fish description", repository.getSfaffById(staff4_id)))
            val product5_id = repository.insertEntity(Product("Eggs", 50.0f, 150, "Eggs description", repository.getSfaffById(staff3_id)))
            val product6_id = repository.insertEntity(Product("Cheese", 62.5f, 50, "Cheese description", repository.getSfaffById(staff2_id)))
            val product7_id = repository.insertEntity(Product("Butter", 70.0f, 320, "Butter description", repository.getSfaffById(staff4_id)))
            val product8_id = repository.insertEntity(Product("Water", 80.0f, 110, "Water description", repository.getSfaffById(staff2_id)))

            updateStoreEntities()
        }
    }

    private suspend fun updateStoreEntities() {
        _storeEntities.value = repository.getStoreEntities()
    }

    fun deleteEntity(storeEntity: StoreEntity) {
        viewModelScope.launch {
            repository.deleteEntity(storeEntity)
            updateStoreEntities()
        }
    }
}
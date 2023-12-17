package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.lab3.adapter.ProductAdapter
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.models.Product
import com.example.lab3.models.Staff
import com.example.lab3.models.StoreEntity
import com.example.lab3.store.DbManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbManager = DbManager(this)

        val array = arrayOf(Staff(0, "Scarlett", 2300),
            Product(1, "Butter", 2),
            Staff(2, "Antonio", 3000),
            Product(3, "Water", 1))

        array.forEach {
            if (it is Product) dbManager.saveProduct(it)
            if (it is Staff) dbManager.saveStaff(it)
        }

        var entitiesFromDb: Array<StoreEntity> = arrayOf()
        entitiesFromDb += dbManager.getAllProducts().toTypedArray()
        entitiesFromDb += dbManager.getAllStaff().toTypedArray()
        binding.myRecyclerList.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerList.adapter = ProductAdapter(entitiesFromDb)
    }
}
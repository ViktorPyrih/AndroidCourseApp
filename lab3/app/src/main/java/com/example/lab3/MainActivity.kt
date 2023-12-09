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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val array = arrayOf(Staff(0, "Scarlett", 2300.0),
            Product(1, "Butter", 2),
            Staff(2, "Antonio", 3000.5),
            Product(3, "Water", 1))
        binding.myRecyclerList.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerList.adapter = ProductAdapter(array)
    }
}
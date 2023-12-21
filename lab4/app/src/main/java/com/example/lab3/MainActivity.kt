package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lab3.adapters.ProductAdapter
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.delegates.EntityActionsListener
import com.example.lab3.models.StoreEntity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val listener = object : EntityActionsListener {
            override fun onDelete(item: StoreEntity) {
                viewModel.deleteEntity(item)
            }
        }

        binding.recyclerList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val adapter = ProductAdapter(viewModel.storeEntities, listener);
        binding.recyclerList.adapter = adapter

        viewModel.storeEntities.observe(this) {
            adapter.notifyDataSetChanged()
        }
    }
}

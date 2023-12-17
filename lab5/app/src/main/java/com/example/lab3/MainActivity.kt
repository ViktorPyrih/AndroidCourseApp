package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.adapter.ProductAdapter
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.models.StoreEntity
import com.example.lab3.network.IStoreAPI
import com.example.lab3.network.StoreHelper
import com.example.lab3.store.DbManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbManager = DbManager(this)

        binding.refreshButton.setOnClickListener {
            fetchData(dbManager)
        }
        binding.deleteButton.setOnClickListener {
            dbManager.dropTables()
        }

        dbManager.onDbChanged = {
            refreshLayout(dbManager)
        }
        refreshLayout(dbManager)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchData(dbManager: DbManager) {
        val response = StoreHelper.getInstance().create(IStoreAPI::class.java)
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                response.getProducts().body()?.forEach {
                    dbManager.saveProduct(it)
                }
                response.getStaff().body()?.forEach {
                    dbManager.saveStaff(it)
                }
            }
        }
    }

    private fun refreshLayout(dbManager: DbManager) {
        var entitiesFromDb: Array<StoreEntity> = arrayOf()
        entitiesFromDb += dbManager.getAllProducts().toTypedArray()
        entitiesFromDb += dbManager.getAllStaff().toTypedArray()

        binding.myRecyclerList.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerList.adapter = ProductAdapter(entitiesFromDb)
    }
}
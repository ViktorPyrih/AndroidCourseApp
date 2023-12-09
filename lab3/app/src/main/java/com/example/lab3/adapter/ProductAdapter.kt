package com.example.lab3.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.databinding.StoreEntityCardBinding
import com.example.lab3.models.Product
import com.example.lab3.models.Staff
import com.example.lab3.models.StoreEntity

class ProductAdapter(private val storeEntitiesSet: Array<StoreEntity>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(val binding: StoreEntityCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindCard(storeEntity: StoreEntity) {
            binding.mainTitle.text = storeEntity.name
            if (storeEntity is Product) {
                binding.wholeCard.backgroundTintList = binding.root.resources.getColorStateList(R.color.orange)
                binding.subtitle.text = "Price " + storeEntity.price.toString() + " $"
                binding.rightSubtitle.text = storeEntity.quantity.toString()
                binding.description.visibility = View.GONE
            }
            if (storeEntity is Staff) {
                binding.subtitle.text = storeEntity.cv
                binding.rightSubtitle.text = "Salary " + storeEntity.salary.toString() + " $"
                binding.description.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = StoreEntityCardBinding.inflate(from, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return storeEntitiesSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCard(storeEntitiesSet[position])
    }
}
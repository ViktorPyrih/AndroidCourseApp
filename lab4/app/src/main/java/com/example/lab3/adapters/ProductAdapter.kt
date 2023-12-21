package com.example.lab3.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.databinding.EntityCardBinding
import com.example.lab3.delegates.EntityActionsListener
import com.example.lab3.models.Product
import com.example.lab3.models.Staff
import com.example.lab3.models.StoreEntity

class ProductAdapter(
    private val storeEntitiesSet: LiveData<List<StoreEntity>>,
    private val listener: EntityActionsListener
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        class ViewHolder(private val binding: EntityCardBinding,
                         private val listener: EntityActionsListener): RecyclerView.ViewHolder(binding.root) {
            fun bindCard(storeEntity: StoreEntity) {
                if (storeEntity is Product) {
                    binding.cardView.backgroundTintList = binding.root.context.getColorStateList(android.R.color.holo_blue_light)
                    binding.mainTitle.text = storeEntity.name
                    binding.subtitle.text = storeEntity.description.toString()
                    binding.rightSubtitle.text = storeEntity.price.toString() + " $"
                    binding.rightBottom.text = storeEntity.quantity.toString() + " left"
                }
                if (storeEntity is Staff) {
                    binding.cardView.backgroundTintList = binding.root.context.getColorStateList(android.R.color.holo_orange_light)
                    binding.mainTitle.text = storeEntity.name
                    binding.subtitle.text = storeEntity.cv.toString()
                    binding.rightSubtitle.text = storeEntity.salary.toString() + " $"

                    val productsLeft = storeEntity.products.sumOf { it.quantity }
                    binding.rightBottom.text = productsLeft.toString() + " products"
                }
                binding.deleteButton.setOnClickListener {
                    listener.onDelete(storeEntity)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = EntityCardBinding.inflate(from, parent, false)

        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return storeEntitiesSet.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCard(storeEntitiesSet.value!![position])
    }
}
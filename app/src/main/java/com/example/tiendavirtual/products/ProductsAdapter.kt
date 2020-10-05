package com.example.tiendavirtual.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtual.databinding.ItemProductBinding
import com.example.tiendavirtual.products.domain.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter: RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var products: List<Product>? = null
    private var listener: ((product:Product)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }
    override fun getItemCount() = products?.size ?:0

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        products?.let{
            holder.bind(it[position], listener)
        }
    }
    fun setProducts(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }
    fun setProductClickListener(listener: (product:Product)-> Unit){
        this.listener = listener
    }

    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product, listener: ((product:Product)-> Unit)?){
            binding.product = product
            binding.executePendingBindings()
            binding.root.cvContainer.setOnClickListener {
                listener?.let {
                    listener(product)
                }
            }
        }
    }
}
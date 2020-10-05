package com.example.tiendavirtual.data.product

import androidx.lifecycle.LiveData
import com.example.tiendavirtual.data.product.source.local.ProductLocalDataSource
import com.example.tiendavirtual.data.product.source.remote.ProductRemoteDataSource
import com.example.tiendavirtual.products.domain.model.Product

class ProductRepository(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) {

    var dataLocalInvalidHome = false

    suspend fun getHomeProducts(): List<Product>{
        return if (dataLocalInvalidHome) {
            getHomeProductsRemote()
        } else {
            val peliculasLocal = productLocalDataSource.getHomeProducts()
            if (peliculasLocal.isNullOrEmpty()) {
                getHomeProductsRemote()
            } else {
                peliculasLocal
            }
        }
    }
    suspend fun getFavoriteProducts(): List<Product> {
        return productLocalDataSource.getFavoriteProducts()
    }
    fun getNumberFavoriteProducts(): LiveData<Int> {
        return productLocalDataSource.getNumberFavoriteProducts()
    }

    suspend fun getProductById(productId: String): Product {
        return productLocalDataSource.getProductById(productId)
    }

    suspend fun updateFavorite(productId: String, esFavorito: Boolean) {
        productLocalDataSource.updateFavorite(productId, esFavorito)
    }

    private suspend fun getHomeProductsRemote(): List<Product> {
        val remoteProducts = productRemoteDataSource.getHomeProducts()

        val peliculasHashMap = HashMap<String, Product>()
        productLocalDataSource.getHomeProducts().forEach {
            peliculasHashMap[it.id] = it
        }

        remoteProducts.forEach {
            it.favorite = peliculasHashMap[it.id]?.favorite?:false
        }

        productLocalDataSource.saveProducts(remoteProducts)
        dataLocalInvalidHome = false
        return remoteProducts
    }
}
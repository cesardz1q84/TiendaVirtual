package com.example.tiendavirtual.data.product.source.local

import androidx.lifecycle.LiveData
import com.example.tiendavirtual.products.domain.model.Product
import com.mitocode.mitocine.datos.pelicula.source.local.ProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductLocalDataSource(private val productDao: ProductDao) {

    suspend fun getHomeProducts(): List<Product> = withContext(Dispatchers.IO) {
        return@withContext productDao.getHomeProducts()
    }

    suspend fun getFavoriteProducts(): List<Product> = withContext(Dispatchers.IO) {
        return@withContext productDao.getFavoriteProducts()
    }

    fun getNumberFavoriteProducts(): LiveData<Int> = productDao.getNumberFavoriteProducts()


    suspend fun getProductById(productId: String): Product = withContext(Dispatchers.IO) {
        productDao.getProductById(productId)
    }

    suspend fun saveProducts(products: List<Product>) = withContext(Dispatchers.IO) {
        productDao.saveProducts(products)
    }

    suspend fun updateFavorite(productId: String, esFavorito: Boolean) =
        withContext(Dispatchers.IO) {
            productDao.updateFavorite(productId, esFavorito)
        }
}
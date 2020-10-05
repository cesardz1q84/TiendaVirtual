package com.mitocode.mitocine.datos.pelicula.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiendavirtual.products.domain.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product ORDER BY productName ASC")
    suspend fun getHomeProducts(): List<Product>

    @Query("SELECT * FROM Product WHERE id = :productId")
    suspend fun getProductById(productId: String): Product

    @Query("SELECT * FROM Product WHERE favorite = 1 ORDER BY productName ASC")
    suspend fun getFavoriteProducts(): List<Product>

    @Query("SELECT COUNT(*) FROM Product WHERE favorite = 1")
    fun getNumberFavoriteProducts(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(products: List<Product>)

    @Query("UPDATE Product SET favorite = :esFavorito WHERE id = :productId")
    suspend fun updateFavorite(productId: String, esFavorito: Boolean)
}
package com.example.tiendavirtual.data.cartproduct.source.local

import androidx.room.*
import com.example.tiendavirtual.cart.domain.model.CartProduct
import com.example.tiendavirtual.cart.domain.model.ProductAndCartProduct

@Dao
interface CartDao {

    @Transaction
    @Query("SELECT * FROM Product")
    suspend fun getProductsAndCartProducts(): List<ProductAndCartProduct>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCartProducts(products: List<CartProduct>)
}
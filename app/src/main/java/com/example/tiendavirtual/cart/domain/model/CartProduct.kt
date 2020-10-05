package com.example.tiendavirtual.cart.domain.model

import androidx.room.*
import com.example.tiendavirtual.products.domain.model.Product

@Entity
data class CartProduct(
    @PrimaryKey val id: String,
    val productId: String,
    val number: Int
)

data class ProductAndCartProduct(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val cartProduct: CartProduct
)

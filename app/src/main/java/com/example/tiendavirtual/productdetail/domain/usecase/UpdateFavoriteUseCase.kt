package com.example.tiendavirtual.productdetail.domain.usecase

import com.example.tiendavirtual.data.product.ProductRepository

class UpdateFavoriteUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String, esFavorite: Boolean)=
        productRepository.updateFavorite(productId, esFavorite)
}
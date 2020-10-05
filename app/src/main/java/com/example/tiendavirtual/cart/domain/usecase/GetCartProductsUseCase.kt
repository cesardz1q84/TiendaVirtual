package com.example.tiendavirtual.cart.domain.usecase

import com.example.tiendavirtual.data.product.ProductRepository

class GetCartProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() = productRepository.getFavoriteProducts()
}
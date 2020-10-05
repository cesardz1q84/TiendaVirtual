package com.example.tiendavirtual.cart.domain.usecase

import com.example.tiendavirtual.data.product.ProductRepository

class GetNumberCartProductsUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke() = productRepository.getNumberFavoriteProducts()
}
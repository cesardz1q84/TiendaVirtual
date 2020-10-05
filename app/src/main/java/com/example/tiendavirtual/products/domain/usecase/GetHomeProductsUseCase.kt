package com.example.tiendavirtual.products.domain.usecase

import com.example.tiendavirtual.data.product.ProductRepository
import com.example.tiendavirtual.products.domain.model.Product

class GetHomeProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(forzarActualizacion: Boolean): List<Product> {
        productRepository.dataLocalInvalidHome = forzarActualizacion
        return productRepository.getHomeProducts()
    }
}
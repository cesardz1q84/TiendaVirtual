package com.example.tiendavirtual.productdetail.domain.usecase

import com.example.tiendavirtual.data.product.ProductRepository

class GetProductByIdUseCase(  private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String)=
        productRepository.getProductById(productId)
}
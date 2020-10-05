package com.example.tiendavirtual.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendavirtual.productdetail.domain.usecase.GetProductByIdUseCase
import com.example.tiendavirtual.productdetail.domain.usecase.UpdateFavoriteUseCase
import com.example.tiendavirtual.products.domain.model.Product
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
): ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    fun getProductById(productId: String) = viewModelScope.launch {
        val foundProduct = getProductByIdUseCase(productId)
        _product.value = foundProduct
    }

    fun updateFavorite(productId: String, esFavorito: Boolean) = viewModelScope.launch {
       updateFavoriteUseCase(productId, esFavorito)
    }

}
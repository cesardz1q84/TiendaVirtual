package com.example.tiendavirtual.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendavirtual.cart.domain.usecase.GetCartProductsUseCase
import com.example.tiendavirtual.products.domain.model.Product
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartProductsUseCase: GetCartProductsUseCase
) : ViewModel() {
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>>
        get() = _products

    private val _mostrarProgreso = MutableLiveData<Boolean>()
    val mostrarProgreso: LiveData<Boolean>
        get() = _mostrarProgreso


    fun getCartProducts() = viewModelScope.launch {
        _mostrarProgreso.value = true

        val productsList = getCartProductsUseCase()

        _products.value = productsList
        _mostrarProgreso.value = false
    }
}
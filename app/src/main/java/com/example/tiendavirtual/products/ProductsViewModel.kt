package com.example.tiendavirtual.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendavirtual.products.domain.model.Product
import com.example.tiendavirtual.products.domain.usecase.GetHomeProductsUseCase
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getHomeProductsUseCase: GetHomeProductsUseCase
) : ViewModel() {
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>>
        get() = _products

    private val _mostrarProgreso = MutableLiveData<Boolean>()
    val mostrarProgreso: LiveData<Boolean>
        get() = _mostrarProgreso

    fun getHomeProducts(forceUpdate: Boolean) = viewModelScope.launch {
        _mostrarProgreso.value = true

        val productsList = getHomeProductsUseCase(forceUpdate)

        _products.value = productsList
        _mostrarProgreso.value = false
    }
}
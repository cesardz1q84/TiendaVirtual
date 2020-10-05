package com.example.tiendavirtual.data.product.source.remote

import com.example.tiendavirtual.data.remote.TiendaVirtualApi
import com.example.tiendavirtual.products.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ProductRemoteDataSource(private val tiendaVirtualApi: TiendaVirtualApi) {
    suspend fun getHomeProducts(): List<Product> = withContext(Dispatchers.IO) {

        val response = tiendaVirtualApi.getHomeProducts()

        if (response.isSuccessful) {
            return@withContext response.body() ?: emptyList()
        }
        return@withContext emptyList<Product>()
    }
}
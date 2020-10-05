package com.example.tiendavirtual.data.remote

import com.example.tiendavirtual.products.domain.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface TiendaVirtualApi {
    companion object {
        const val URL_BASE =
            "https://api.backendless.com/34D4BCBC-E6B7-9B77-FFEE-8C84BAB1AD00/6A591585-621D-4B80-B082-1D5ADEA689AB/"
    }

    @GET("data/Products?sortBy=productName%20asc")
    suspend fun getHomeProducts():Response<List<Product>>
}
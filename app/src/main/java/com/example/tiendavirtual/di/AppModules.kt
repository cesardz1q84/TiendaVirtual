package com.example.tiendavirtual.di

import androidx.room.Room
import com.example.tiendavirtual.LoginViewModel
import com.example.tiendavirtual.cart.CartViewModel
import com.example.tiendavirtual.cart.domain.usecase.GetCartProductsUseCase
import com.example.tiendavirtual.cart.domain.usecase.GetNumberCartProductsUseCase
import com.example.tiendavirtual.data.local.TiendaVirtualDB
import com.example.tiendavirtual.data.product.ProductRepository
import com.example.tiendavirtual.data.product.source.local.ProductLocalDataSource
import com.example.tiendavirtual.data.product.source.remote.ProductRemoteDataSource
import com.example.tiendavirtual.data.remote.TiendaVirtualApi
import com.example.tiendavirtual.productdetail.ProductDetailViewModel
import com.example.tiendavirtual.productdetail.domain.usecase.GetProductByIdUseCase
import com.example.tiendavirtual.productdetail.domain.usecase.UpdateFavoriteUseCase
import com.example.tiendavirtual.products.ProductsViewModel
import com.example.tiendavirtual.products.domain.usecase.GetHomeProductsUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val appModule = module {

    single {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("Okhttp").d(message)
            }
        }
        ).also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(TiendaVirtualApi.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TiendaVirtualApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            TiendaVirtualDB::class.java,
            "TiendaVirtual.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { ProductLocalDataSource(get<TiendaVirtualDB>().productDao()) }

    single { ProductRemoteDataSource(get()) }

    single { ProductRepository(get(), get()) }

    factory { GetCartProductsUseCase(get()) }

    factory { GetNumberCartProductsUseCase(get()) }

    factory { GetHomeProductsUseCase(get()) }

    factory { GetProductByIdUseCase(get()) }

    factory { UpdateFavoriteUseCase(get()) }

    viewModel { ProductDetailViewModel(get(), get()) }

    viewModel { ProductsViewModel(get()) }

    viewModel { CartViewModel(get()) }

    viewModel { LoginViewModel(get()) }

}
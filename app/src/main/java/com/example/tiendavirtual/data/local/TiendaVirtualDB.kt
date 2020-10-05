package com.example.tiendavirtual.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tiendavirtual.cart.domain.model.CartProduct
import com.example.tiendavirtual.data.cartproduct.source.local.CartDao
import com.example.tiendavirtual.products.domain.model.Product
import com.mitocode.mitocine.datos.pelicula.source.local.ProductDao

@Database(entities = [Product::class, CartProduct::class], version = 2, exportSchema = false)
abstract class TiendaVirtualDB: RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun cartDao(): CartDao


    companion object{
        private var INSTANCE: TiendaVirtualDB? = null

        fun getInstance(context: Context): TiendaVirtualDB {
            return INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: createDB(
                            context
                        ).also {
                            INSTANCE = it
                        }
                }
        }

        private fun createDB(context: Context): TiendaVirtualDB {
            return Room.databaseBuilder(
                context.applicationContext,
                TiendaVirtualDB::class.java,
                "MitoCine.db"
            )
                .build()
        }
    }
}
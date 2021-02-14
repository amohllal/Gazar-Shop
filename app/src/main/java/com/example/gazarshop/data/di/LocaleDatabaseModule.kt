package com.example.gazarshop.data.di

import android.content.Context
import androidx.room.Room
import com.example.gazarshop.data.local.ProductsDAO
import com.example.gazarshop.data.local.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocaleDatabaseModule {

    @Provides
    @Singleton
     fun productsDAO(productsDatabase: ProductsDatabase) : ProductsDAO {
         return productsDatabase.productsDAO()
     }


    companion object {

        //volatile mean that this instance is available for all threads
        @Volatile
        private var Instance : ProductsDatabase? = null
        @Provides
        @Singleton
        fun getDatabase(context: Context) : ProductsDatabase {
            val tempInstance = Instance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "New Database"
                ).fallbackToDestructiveMigration().build()
                Instance = instance
                return instance
            }
        }




    }
}
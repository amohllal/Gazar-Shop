package com.example.gazarshop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gazarshop.data.pojo.ProductNewItem
import com.example.gazarshop.data.pojo.products.ProductResponseItem

@Database(entities = [ProductResponseItem::class, ProductNewItem::class], version = 2, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun productsDAO() : ProductsDAO
}
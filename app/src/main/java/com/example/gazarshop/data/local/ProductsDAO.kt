package com.example.gazarshop.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gazarshop.data.pojo.ProductNewItem
import com.example.gazarshop.data.pojo.products.ProductResponseItem

@Dao
interface ProductsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(productResponseItem: ProductResponseItem)

    @Query("select * from products_table")
     fun allProductsInCard() : LiveData<List<ProductResponseItem>>

     @Query("DELETE FROM products_table")
     suspend fun deleteAllProducts()


     @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun addNewItem(productNewItem: ProductNewItem)

    @Query("select * from new_product")
    fun allNewProducts() : LiveData<List<ProductNewItem>>

}
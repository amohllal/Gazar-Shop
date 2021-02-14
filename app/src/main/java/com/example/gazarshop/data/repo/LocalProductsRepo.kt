package com.example.gazarshop.data.repo

import androidx.lifecycle.LiveData
import com.example.gazarshop.data.local.ProductsDAO
import com.example.gazarshop.data.pojo.ProductNewItem
import com.example.gazarshop.data.pojo.products.ProductResponseItem
import javax.inject.Inject

class LocalProductsRepo @Inject constructor(private val productDao : ProductsDAO) {


    val productLiveData : LiveData<List<ProductResponseItem>> = productDao.allProductsInCard()
    val newProductLiveData : LiveData<List<ProductNewItem>> = productDao.allNewProducts()


    suspend fun addProduct(productResponseItem: ProductResponseItem) {
        productDao.insertProduct(productResponseItem)
    }

     suspend fun deleteAllProducts(){
         productDao.deleteAllProducts()
     }

     suspend fun addNewItem(productNewItem: ProductNewItem){
         productDao.addNewItem(productNewItem)
     }

}
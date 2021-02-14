package com.example.gazarshop.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.gazarshop.data.di.LocaleDatabaseModule
import com.example.gazarshop.data.local.ProductsDatabase
import com.example.gazarshop.data.pojo.ProductNewItem
import com.example.gazarshop.data.pojo.products.ProductResponseItem
import com.example.gazarshop.data.repo.LocalProductsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocaleViewModel @Inject constructor(application: Application) :  AndroidViewModel(application) {
    private  var  allProduct : LiveData<List<ProductResponseItem>>
    private  var  allNewProduct : LiveData<List<ProductNewItem>>
    private var localeRepo : LocalProductsRepo

    init {
        val productDao = LocaleDatabaseModule.getDatabase(application).productsDAO()
        localeRepo = LocalProductsRepo(productDao)
        allProduct = getAllProducts()
        allNewProduct = getAllNewProducts()
    }

    //fetch all product in card
    fun getAllProducts() : LiveData<List<ProductResponseItem>>{
        viewModelScope.launch(Dispatchers.IO){
            allProduct = localeRepo.productLiveData
        }
        return allProduct
    }
    //fetch all new product which user add to app
     fun getAllNewProducts() : LiveData<List<ProductNewItem>>{
        viewModelScope.launch(Dispatchers.IO){
            allNewProduct = localeRepo.newProductLiveData
        }
        return allNewProduct
    }

    //add product to card
    fun addProductToCard(productResponseItem: ProductResponseItem){
        viewModelScope.launch(Dispatchers.IO){
            localeRepo.addProduct(productResponseItem)
        }

    }

    //delete all product
    fun deleteProducts(){
        viewModelScope.launch(Dispatchers.IO){
            localeRepo.deleteAllProducts()
        }
    }

    //add new product to app
    fun addNewProduct(productNewItem: ProductNewItem){
        viewModelScope.launch (Dispatchers.IO){
            localeRepo.addNewItem(productNewItem)
        }
    }



}
package com.example.gazarshop.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gazarshop.data.pojo.products.ProductResponseItem
import com.example.gazarshop.data.pojo.categorydetails.CategoryDetailsItem
import com.example.gazarshop.data.repo.ProductsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repo : ProductsRepo) : ViewModel() {

    val productsLiveData = MutableLiveData<ArrayList<ProductResponseItem>>()
    val productsCategoryLiveData = MutableLiveData<ArrayList<CategoryDetailsItem>>()
    private val compositeDisposable = CompositeDisposable()

    //fetch all remote products
     fun getProducts(){
        compositeDisposable.add(
                repo.fetchProducts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{
                            productsLiveData.value = it
                        }
        )
    }

    //get specific products by category name
     fun getProductsCategory(categoryName : String){
        compositeDisposable.add(
            repo.fetchCategory(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    productsCategoryLiveData.value = it
                }
        )
    }
}
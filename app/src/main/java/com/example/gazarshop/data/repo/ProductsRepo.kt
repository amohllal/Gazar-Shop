package com.example.gazarshop.data.repo

import com.example.gazarshop.data.pojo.products.ProductResponse
import com.example.gazarshop.data.pojo.categorydetails.CategoryDetails
import com.example.gazarshop.data.remote.FakeAPI
import io.reactivex.Observable
import javax.inject.Inject

class ProductsRepo @Inject constructor(private val api : FakeAPI) {


    fun fetchProducts(): Observable<ProductResponse> {
        return api.fetchAllProducts()

    }

     fun fetchCategory(categoryName : String) : Observable<CategoryDetails>{
        return api.fetchSpecialCategory(categoryName)
    }
}
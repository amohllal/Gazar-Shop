package com.example.gazarshop.data.remote

import com.example.gazarshop.data.pojo.products.ProductResponse
import com.example.gazarshop.data.pojo.categorydetails.CategoryDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeAPI {


    @GET("products")
    fun fetchAllProducts() : Observable<ProductResponse>

    @GET("products/category/{cateName}")
    fun fetchSpecialCategory(@Path("cateName") categoryName : String) : Observable<CategoryDetails>
}
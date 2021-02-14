package com.example.gazarshop.data.pojo.authmodel.request


import com.google.gson.annotations.SerializedName

data class RequestBody(
    val city: String,
    val numberBuilding: String,
    val street: String,
    val zipcode: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val password: String,
    val phone: String,
    val username: String
)
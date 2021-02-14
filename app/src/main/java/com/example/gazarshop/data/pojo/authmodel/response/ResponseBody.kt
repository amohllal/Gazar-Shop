package com.example.gazarshop.data.pojo.authmodel.response


import com.google.gson.annotations.SerializedName

data class ResponseBody(
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
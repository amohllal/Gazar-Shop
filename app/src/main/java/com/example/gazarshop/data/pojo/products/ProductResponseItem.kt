package com.example.gazarshop.data.pojo.products


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



@Entity(tableName = "products_table")
data class ProductResponseItem(

    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("title")
    val title: String,
    @Expose(deserialize = false, serialize = false)
    val amount : Int,
    @Expose(deserialize = false, serialize = false)
    val total : Double,


)
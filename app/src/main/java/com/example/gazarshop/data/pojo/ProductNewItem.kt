package com.example.gazarshop.data.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "New_Product")
data class ProductNewItem(
        @PrimaryKey(autoGenerate = true)
        val itemId : Int,
        val itemImage : String,
        val category : String,
        val itemName : String,
        val itemPrice : String,
        val itemDesc : String
        )

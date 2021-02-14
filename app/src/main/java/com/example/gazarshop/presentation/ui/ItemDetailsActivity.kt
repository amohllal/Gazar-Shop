package com.example.gazarshop.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.products.ProductResponseItem
import com.example.gazarshop.databinding.ActivityItemDetailsBinding
import com.example.gazarshop.presentation.viewmodel.LocaleViewModel
import com.example.gazarshop.presentation.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemDetailsBinding
    private var count: Int = 1
    private var productPrice: Double = 0.0
    private var total: Double = 0.0
    private lateinit var itemCategory: String
    private lateinit var itemTitle: String
    private lateinit var itemDesc: String
    private var itemId: Int = 0
    private lateinit var imageUrl: String
    private lateinit var productResponseItem: ProductResponseItem
    private val localeViewModel by lazy { ViewModelProvider(this).get(LocaleViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_details)
        setSupportActionBar(binding.mainAppBar.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initValues()
        setTitle(itemCategory)


        binding.addToCardBtn.setOnClickListener {
            //add item to card then go to Home
            productResponseItem = ProductResponseItem(itemCategory, itemDesc, itemId, imageUrl, total, itemTitle, count, total)
            localeViewModel.addProductToCard(productResponseItem)
            Toast.makeText(this, "added to card successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // increase amount of item
        binding.plusBtn.setOnClickListener {
            count++
            total += productPrice
            binding.productAmount.text = count.toString()
            binding.productPrice.text = total.toString() + "$"
        }

        // decrease amount of item
        binding.subtractionBtn.setOnClickListener {
            count--
            if (total > productPrice) {
                total -= productPrice
            }
            binding.productAmount.text = count.toString()
            binding.productPrice.text = total.toString() + "$"
        }
    }


    private fun initValues() {
        itemCategory = intent.getStringExtra("itemCategory").toString()
        itemTitle = intent.getStringExtra("itemTitle").toString()
        itemDesc = intent.getStringExtra("itemDesc").toString()
        itemId = intent.getIntExtra("itemId", 0)
        imageUrl = intent.getStringExtra("itemImage").toString()

        Glide.with(this).load(imageUrl).into(binding.imageView2)
        binding.productName.text = itemTitle
        binding.productDesc.text = itemDesc
        productPrice = intent.getDoubleExtra("itemPrice", 0.0)
        binding.productPrice.text = productPrice.toString() + "$"
        total = productPrice


    }

}
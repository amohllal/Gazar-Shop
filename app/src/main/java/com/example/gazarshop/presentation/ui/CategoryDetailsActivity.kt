package com.example.gazarshop.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.categorydetails.CategoryDetailsItem
import com.example.gazarshop.presentation.adapter.CategoryDetailsRecyclerAdapter
import com.example.gazarshop.presentation.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailsActivity : AppCompatActivity(), CategoryDetailsRecyclerAdapter.OnItemClickListener{

    lateinit var categoryName : String
    lateinit var recyclerAdapter: CategoryDetailsRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var categoryDetailsItem : ArrayList<CategoryDetailsItem>
    private val productsViewModel by lazy { ViewModelProvider(this).get(ProductsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_details)
        categoryName = intent.getStringExtra("categoryName")!!
        val toolbar : Toolbar = findViewById(R.id.main_app_bar)
        setSupportActionBar(toolbar)
        setTitle(categoryName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initRecyclerView()

        getProductsCategory(categoryName)
    }

    private fun initRecyclerView(){
        recyclerAdapter = CategoryDetailsRecyclerAdapter(this,this)
        recyclerView = findViewById(R.id.product_rec)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    //fetch items by category name
    private fun getProductsCategory(categoryName : String){
        productsViewModel.getProductsCategory(categoryName)
        productsViewModel.productsCategoryLiveData.observe(
            this,
            { t ->
                categoryDetailsItem = t!!
                recyclerAdapter.setList(categoryDetailsItem)
            })
    }

    override fun onClick(position: Int) {

        goToItemDetailsActivity(position)


    }

    //go to item details Activity
    private fun goToItemDetailsActivity(position: Int){
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra("itemCategory", categoryDetailsItem[position].category)
        intent.putExtra("itemTitle", categoryDetailsItem[position].title)
        intent.putExtra("itemImage", categoryDetailsItem[position].image)
        intent.putExtra("itemId", categoryDetailsItem[position].id)
        intent.putExtra("itemDesc", categoryDetailsItem[position].description)
        intent.putExtra("itemPrice", categoryDetailsItem[position].price)
        startActivity(intent)
        finish()
    }
}
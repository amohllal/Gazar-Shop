package com.example.gazarshop.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.ProductNewItem
import com.example.gazarshop.data.pojo.products.ProductResponseItem
import com.example.gazarshop.presentation.adapter.MainAdapter
import com.example.gazarshop.presentation.viewmodel.LocaleViewModel
import com.example.gazarshop.presentation.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var gridView : GridView
    lateinit var mainAdapter: MainAdapter
    private val productsViewModel by lazy { ViewModelProvider(this).get(ProductsViewModel::class.java) }
    private val localeViewModel by lazy { ViewModelProvider(this).get(LocaleViewModel::class.java) }
    lateinit var jeweleryBtn : Button
    lateinit var electronicsBtn : Button
    lateinit var menBtn : Button
    lateinit var womenBtn : Button
    lateinit var categoryName : String
    lateinit var responseItem : ArrayList<ProductResponseItem>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        activity?.title = "All Products"

        initView(root, container)

        jeweleryBtn.setOnClickListener {
            categoryName = jeweleryBtn.text.toString()
            goToProductsDetailsActivity(categoryName)
        }
        electronicsBtn.setOnClickListener {
            categoryName = electronicsBtn.text.toString()
            goToProductsDetailsActivity(categoryName)
        }
        menBtn.setOnClickListener {
            categoryName = menBtn.text.toString()
            goToProductsDetailsActivity(categoryName)
        }
        womenBtn.setOnClickListener {
            categoryName = womenBtn.text.toString()
            goToProductsDetailsActivity(categoryName)
        }


        responseItem = ArrayList<ProductResponseItem>()
        gridView.setOnItemClickListener { adapterView, view, i, l ->
            goToItemDetailsActivity(i)

        }

        observeProducts()
        getLocaleItems()
        return root
    }

    private fun initView(root : View, container: ViewGroup?){
        jeweleryBtn = root.findViewById(R.id.jewelery)
        electronicsBtn = root.findViewById(R.id.electronics)
        menBtn = root.findViewById(R.id.men_clothing)
        womenBtn = root.findViewById(R.id.women_clothing)
        gridView = root.findViewById(R.id.grid_view)
        mainAdapter = MainAdapter(container!!.context)
        gridView.adapter = mainAdapter
    }

    //go to item details activity
    private fun goToItemDetailsActivity(i : Int){
        val intent = Intent(activity,ItemDetailsActivity::class.java)
        intent.putExtra("itemCategory", responseItem.get(i).category)
        intent.putExtra("itemTitle", responseItem.get(i).title)
        intent.putExtra("itemImage", responseItem.get(i).image)
        intent.putExtra("itemId", responseItem.get(i).id)
        intent.putExtra("itemDesc", responseItem.get(i).description)
        intent.putExtra("itemPrice", responseItem.get(i).price)
        startActivity(intent)
    }

    //send category name
    private fun goToProductsDetailsActivity(categoryName : String){
        val intent = Intent(activity, CategoryDetailsActivity::class.java)
        intent.putExtra("categoryName", categoryName)
        startActivity(intent)
    }

    // fetch all remote products
     private fun observeProducts(){
        productsViewModel.getProducts()
        productsViewModel.productsLiveData.observe(viewLifecycleOwner,
            { t ->
                responseItem= t!!
                mainAdapter.setList(responseItem)
            })
    }

    //fetch new locale items from room
    private fun getLocaleItems(){
        localeViewModel.getAllNewProducts()
        localeViewModel.getAllNewProducts().observe(this,
            { t -> Log.d("TAG", "krk onChanged: "+t!!.size) })
    }
}
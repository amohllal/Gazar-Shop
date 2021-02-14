package com.example.gazarshop.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.products.ProductResponseItem
import com.example.gazarshop.presentation.adapter.CardRecyclerAdapter
import com.example.gazarshop.presentation.viewmodel.LocaleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment(), CardRecyclerAdapter.OnItemClickListener {

    private val localeViewModel by lazy { ViewModelProvider(this).get(LocaleViewModel::class.java) }
    private lateinit var cardRecyclerAdapter: CardRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var resetButton : Button
    lateinit var orderButton: Button
    private var productsList :ArrayList<ProductResponseItem> = ArrayList<ProductResponseItem>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_card, container, false)
        activity?.title = "My Card"
        initViews(root)
        fetchAllProduct()


        resetButton.setOnClickListener {
            //delete all items in card
            localeViewModel.deleteProducts()
            Toast.makeText(context, "items deleted successfully", Toast.LENGTH_LONG).show()
            goToHome()

        }

        orderButton.setOnClickListener {
            //submit order and delete all items in card
            localeViewModel.deleteProducts()
            Toast.makeText(context, "Order will be arrived in 15 minutes", Toast.LENGTH_LONG).show()
            goToHome()
        }
        return root
    }

    private fun goToHome(){
        startActivity(Intent(context,MainActivity::class.java))
    }

    private fun initViews(view : View){
        cardRecyclerAdapter = CardRecyclerAdapter(requireContext().applicationContext,this)
        recyclerView = view.findViewById(R.id.cardRecycler)
        recyclerView.adapter = cardRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext, RecyclerView.VERTICAL, false)

        resetButton = view.findViewById(R.id.reset_btn)
        orderButton = view.findViewById(R.id.order_btn)
    }

    // fetch all products in card
    private fun fetchAllProduct(){
        localeViewModel.getAllProducts().observe(this,
            {
                for (item in it){
                    productsList.add(item)
                }
                cardRecyclerAdapter.setList(productsList)

            })

    }

    override fun onClick(position: Int) {
        TODO("Not yet implemented")
    }
}
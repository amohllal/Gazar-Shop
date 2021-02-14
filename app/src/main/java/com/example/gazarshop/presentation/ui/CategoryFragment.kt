package com.example.gazarshop.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.gazarshop.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    lateinit var jewelery_category : CardView
    lateinit var men_category : CardView
    lateinit var women_category : CardView
    lateinit var hardware_category : CardView
    lateinit var categoryName : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_category, container, false)
        activity?.title = "Categories"

        initViews(root)

        jewelery_category.setOnClickListener {
            categoryName = "jewelery"
            goToCateDetailsActivity(categoryName)
        }
        men_category.setOnClickListener {
            categoryName = "men clothing"
            goToCateDetailsActivity(categoryName)
        }
        women_category.setOnClickListener {
            categoryName = "women clothing"
            goToCateDetailsActivity(categoryName)
        }
        hardware_category.setOnClickListener {
            categoryName = "electronics"
            goToCateDetailsActivity(categoryName)
        }
        return root
    }

    private fun goToCateDetailsActivity(categoryName : String){
        val intent = Intent(context, CategoryDetailsActivity::class.java)
        intent.putExtra("categoryName", categoryName)
        startActivity(intent)

    }

    private fun initViews(view: View){
        jewelery_category = view.findViewById(R.id.jewelery_category)
        men_category = view.findViewById(R.id.men_category)
        women_category = view.findViewById(R.id.women_category)
        hardware_category = view.findViewById(R.id.hardware_category)

    }
}
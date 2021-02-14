package com.example.gazarshop.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.categorydetails.CategoryDetailsItem

class CategoryDetailsRecyclerAdapter(val context : Context,  private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<CategoryDetailsRecyclerAdapter.ProductViewHolder>() {


    private var productArrayList = ArrayList<CategoryDetailsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailsRecyclerAdapter.ProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_products_item, parent, false)
        return ProductViewHolder(v, this)
    }

    override fun onBindViewHolder(holder: CategoryDetailsRecyclerAdapter.ProductViewHolder, position: Int) {
        val imgUrl = productArrayList[position].image
        Glide.with(context).load(imgUrl).into(holder.product_image)

        holder.product_name.text = productArrayList[position].title
        holder.product_desc.text = productArrayList[position].description
        holder.product_price.text = productArrayList[position].price.toString()+"$"
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    fun setList(list : ArrayList<CategoryDetailsItem>){
        productArrayList = list
        notifyDataSetChanged()
    }



     class ProductViewHolder(itemView: View, private val onItemClickListener: CategoryDetailsRecyclerAdapter) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
         val product_image : ImageView = itemView.findViewById(R.id.product_avatar)
        val product_name : TextView = itemView.findViewById(R.id.category_product_name)
        val product_desc : TextView = itemView.findViewById(R.id.category_product_desc)
        val product_price : TextView = itemView.findViewById(R.id.category_product_price)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onItemClickListener.onClick(adapterPosition)
        }


    }
    interface OnItemClickListener{
        fun onClick(position : Int)
    }


}
package com.example.gazarshop.presentation.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.products.ProductResponseItem

class MainAdapter (var context: Context) : BaseAdapter() {
    var arrayList = ArrayList<ProductResponseItem>()

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any? {
        return arrayList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    fun setList(list: ArrayList<ProductResponseItem>) {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.product_item, null)
        var productName: TextView = view.findViewById(R.id.productName)
        var productImage: ImageView = view.findViewById(R.id.product_img)

        var listItem: ProductResponseItem = arrayList.get(p0)
        productName.text = listItem.title
        val imgUrl = listItem.image
        Glide.with(context).load(imgUrl).into(productImage)
        return view
    }
}
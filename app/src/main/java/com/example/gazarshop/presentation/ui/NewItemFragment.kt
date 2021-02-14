package com.example.gazarshop.presentation.ui

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.ProductNewItem
import com.example.gazarshop.presentation.viewmodel.LocaleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewItemFragment : Fragment() {
    lateinit var imageUrlItem : String
    lateinit var titleItem : String
    lateinit var priceItem : String
    lateinit var descriptionItem : String
    lateinit var categoryItem : String
    private var itemID : Int = 0
    lateinit var imageViewItem : ImageView
    lateinit var titleView : EditText
    lateinit var priceView : EditText
    lateinit var descView : EditText
    lateinit var submitButton : Button
    val IMAGE_PICK_CODE = 1
    lateinit var uri: Uri
    private val localeViewModel by lazy { ViewModelProvider(this).get(LocaleViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View  = inflater.inflate(R.layout.fragment_newitem, container, false)
        activity?.title = "New Item"

        initViews(view)
        imageViewItem.setOnClickListener {
            //pick an image from gallery
            pickImage()
        }


        submitButton.setOnClickListener {
            //add new item to room and go to Home Screen to return this item
            initValues()
            val productNewItem = ProductNewItem(itemID,imageUrlItem,categoryItem,titleItem,priceItem,descriptionItem)
            localeViewModel.addNewProduct(productNewItem)
            Toast.makeText(context,"Item Added Successfully...", Toast.LENGTH_LONG).show()
            startActivity(Intent(context,MainActivity::class.java))
        }


        return view
    }

    private fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageViewItem.setImageURI(data?.data)
            uri = data?.data!!

        }
    }



    private fun initViews(view: View) {
        imageViewItem = view.findViewById(R.id.imageItem)
        titleView = view.findViewById(R.id.itemName)
        priceView = view.findViewById(R.id.itemPrice)
        descView = view.findViewById(R.id.itemDesc)
        submitButton = view.findViewById(R.id.submit)
    }
    private fun initValues(){
        itemID = 1
        imageUrlItem = uri.path.toString()
        titleItem = titleView.text.toString()
        priceItem = priceView.text.toString()
        descriptionItem = descView.text.toString()
        categoryItem = "electronics"
    }


}
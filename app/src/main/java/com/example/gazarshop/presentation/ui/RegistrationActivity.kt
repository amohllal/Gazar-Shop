package com.example.gazarshop.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gazarshop.R
import com.example.gazarshop.data.pojo.authmodel.request.RequestBody
import com.example.gazarshop.databinding.ActivityRegistrationBinding
import com.example.gazarshop.presentation.viewmodel.UserAuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding
    lateinit var fname : String
    lateinit var lname : String
    lateinit var uname : String
    lateinit var mail : String
    lateinit var pass : String
    lateinit var mob : String
    var cityName : String = ""
    lateinit var streetName : String
    lateinit var numberOfBuilding : String
    lateinit var zipCode : String
    private val userAuthViewModel by lazy { ViewModelProvider(this).get(UserAuthViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_registration)

        initViews()
        binding.nextBtn.setOnClickListener {
            runBlocking {
                signUp()
            }
        }

    }

    //create user
    private suspend fun signUp() {
        userAuthViewModel.userRegister(getViewsArguments().email,getViewsArguments().password,getViewsArguments())

        //observe register success
        userAuthViewModel.authSuccess.observe(this,object :Observer<String>{
            override fun onChanged(t: String?) {
                Toast.makeText(this@RegistrationActivity,t,Toast.LENGTH_LONG).show()
                goToMainProducts()
            }
        })

        //observe register failed
        userAuthViewModel.authFailed.observe(this, object : Observer<String>{
            override fun onChanged(t: String?) {
                Toast.makeText(this@RegistrationActivity,t,Toast.LENGTH_LONG).show()
            }
        })

    }


    private fun goToMainProducts(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    //initialize views
    private fun initViews(){
        val arrayAdapter = ArrayAdapter<String>(this
                , android.R.layout.simple_list_item_1
                , resources.getStringArray(R.array.cities))
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter

    }

    //get values from views
    private fun getViewsArguments() : RequestBody{
        fname = binding.firstNameView.toString()
        lname = binding.lastNameView.toString()
        uname = binding.userNameView.toString()
        mail = binding.emailView.toString()
        pass = binding.passwordView.toString()
        mob = binding.phoneView.toString()
        cityName = getSpinnerItemSelected()
        streetName = binding.street.toString()
        numberOfBuilding = binding.numOfBuild.toString()
        zipCode = binding.zipCode.toString()
        return RequestBody(cityName,numberOfBuilding,streetName,zipCode,mail,fname,lname,pass,mob,uname)
    }

    private fun getSpinnerItemSelected() : String {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                cityName = resources.getStringArray(R.array.cities)[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
        return cityName
    }
}
package com.example.gazarshop.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gazarshop.data.pojo.authmodel.request.RequestBody
import com.example.gazarshop.data.pojo.authmodel.response.ResponseBody
import com.example.gazarshop.data.repo.ProductsRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserAuthViewModel @Inject constructor() : ViewModel() {
    var authSuccess = MutableLiveData<String>()
    var authFailed = MutableLiveData<String>()
    private val firebaseAuth = FirebaseAuth.getInstance()


    //Register new user
    suspend fun userRegister(email : String , password : String, requestBody: RequestBody){
        GlobalScope.launch (Dispatchers.IO){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        authSuccess.value = "User Created done.."
                        FirebaseDatabase.getInstance().reference
                            .child("User")
                            .child(requestBody.phone)
                            .setValue(requestBody)
                    }else {
                        authFailed.value = it.exception?.message
                    }
                }
            withContext(Dispatchers.Main){

            }
        }

    }



}
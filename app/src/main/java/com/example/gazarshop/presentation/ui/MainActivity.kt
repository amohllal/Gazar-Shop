package com.example.gazarshop.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.gazarshop.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if (FirebaseAuth.getInstance().currentUser == null){
            startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
        }


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this,drawerLayout
            ,toolbar
            , R.string.navigation_drawer_open
            , R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.container_layout, HomeFragment()).commit()
            navView.setCheckedItem(R.id.home)
        }




        navView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when(item.itemId){
                    R.id.home -> {
                        navView.menu.findItem(R.id.home).setCheckable(true)
                        supportFragmentManager.beginTransaction().replace(R.id.container_layout, HomeFragment()).commit()
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.category -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container_layout, CategoryFragment()).commit()
                        navView.menu.findItem(R.id.category).setCheckable(true)
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.card -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container_layout, CardFragment()).commit()
                        navView.menu.findItem(R.id.card).setCheckable(true)
                        drawerLayout.closeDrawer(GravityCompat.START)
                }
                    R.id.NewItem -> {
                        supportFragmentManager.beginTransaction().replace(R.id.container_layout, NewItemFragment()).commit()
                        navView.menu.findItem(R.id.NewItem).setCheckable(true)
                        drawerLayout.closeDrawer(GravityCompat.START)

                    }
                    R.id.logout ->{
                        FirebaseAuth.getInstance().signOut()
                        startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
                        finish()
                    }
                }
                return true
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shopping_cart){
            supportFragmentManager.beginTransaction().replace(R.id.container_layout, CardFragment()).commit()
        }
        return super.onOptionsItemSelected(item)
    }
}
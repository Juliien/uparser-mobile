package com.esgi.uparser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.esgi.uparser.fragments.CatalogueFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppPreferences.init(this)
        setContentView(R.layout.main_activity)

        loadFragment(CatalogueFragment())
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_catalogue -> {
                    loadFragment(CatalogueFragment())
                    true
                }
                R.id.navigation_profile -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
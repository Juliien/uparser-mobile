package com.esgi.uparser.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.esgi.uparser.*
import com.esgi.uparser.catalog.CatalogActivity
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        when (activity?.javaClass?.simpleName) {
            CatalogActivity::class.simpleName -> {
                bottomNavigationView.menu.getItem(0).isChecked = true
                bottomNavigationView.menu.getItem(0)
                    .setIcon(R.drawable.ic_menu_main_solid_foreground)
            }

            MainActivity::class.simpleName -> {
                bottomNavigationView.menu.getItem(1).isChecked = true
                bottomNavigationView.menu.getItem(1)
                    .setIcon(R.drawable.ic_menu_main_solid_foreground)
            }

            UserDetailActivity::class.simpleName -> {
                bottomNavigationView.menu.getItem(2).isChecked = true
                bottomNavigationView.menu.getItem(2)
                    .setIcon(R.drawable.ic_menu_user_solid_foreground)
            }

            else -> {
                for (i in 0..2) {
                    bottomNavigationView.menu.getItem(i).isChecked = false
                }
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_catalogue -> activity?.let { it1 ->
                    if (!it.isChecked) {
                        CatalogActivity.navigateTo(it1)
                    }
                }
                R.id.navigation_home -> activity?.let { it1 ->
                    if (!it.isChecked) {
                        MainActivity.navigateTo(it1)
                    }
                }
                R.id.navigation_profile -> activity?.let { it1 ->
                    if (!it.isChecked) {
                        UserDetailActivity.navigateTo(it1)
                    }
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu to use in the action bar
        inflater.inflate(R.menu.bottom_navigation_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

}

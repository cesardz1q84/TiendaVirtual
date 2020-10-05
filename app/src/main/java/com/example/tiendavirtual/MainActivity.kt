package com.example.tiendavirtual

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val loginViewModel by viewModel<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        val navController = navHostFragment.navController


        NavigationUI.setupWithNavController(bottomNav, navController)

        //TODO "bind badge with viewmodel"
        val badge: BadgeDrawable = bottomNav.getOrCreateBadge(
            R.id.cartFragment
        ).apply {
            isVisible = false
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.cartFragment, R.id.settingsFragment -> showBotNav()
                else -> hideBotNav()
            }
        }

        loginViewModel._products.observe(this, Observer {
            badge.apply {
                if (it > 0) {
                    number = it
                    isVisible = true
                } else {
                    isVisible = false
                }
            }
        })
    }

    fun showBotNav() {
        bottomNav.visibility = View.VISIBLE
    }

    fun hideBotNav() {
        bottomNav.visibility = View.GONE
    }

}
package com.noteamname.androidhackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            if (destination.id == R.id.destination_toilet_roll) {
                supportActionBar?.apply {
                    setHomeButtonEnabled(true)
                    setDisplayHomeAsUpEnabled(true)
                }
            } else {
                supportActionBar?.apply {
                    setHomeButtonEnabled(false)
                    setDisplayHomeAsUpEnabled(false)
                }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            navController.popBackStack() || super.onSupportNavigateUp() || run {
                finish()
                true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

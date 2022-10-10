package com.miel3k.masteringandroidpaging3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miel3k.masteringandroidpaging3.dashboard.view.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startDashboardFragment()
    }

    private fun startDashboardFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DashboardFragment())
            .commit()
    }
}
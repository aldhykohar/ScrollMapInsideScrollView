package com.aldhykohar.scrollmapinsidescrollview

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.aldhykohar.scrollmapinsidescrollview.databinding.ActivityMainBinding
import com.aldhykohar.scrollmapinsidescrollview.utilities.WorkAroundMapFragment

class MainActivity : AppCompatActivity(), WorkAroundMapFragment.OnTouchListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listener: MapListener? = null

    fun setListener(listener: MapListener?) {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment,
                MapFragment(), "Home"
            ).commit()
        }
        doRequest()
    }

    private fun doRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE
            ), 101
        )
    }


    override fun onTouch() {
        listener?.setScrollView()
    }
}
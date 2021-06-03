package com.aldhykohar.scrollmapinsidescrollview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.aldhykohar.scrollmapinsidescrollview.databinding.FragmentMapBinding
import com.aldhykohar.scrollmapinsidescrollview.utilities.WorkAroundMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class MapFragment : Fragment(), MapListener {

    private val binding: FragmentMapBinding by lazy {
        FragmentMapBinding.inflate(layoutInflater)
    }

    private lateinit var mMap: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        (requireActivity() as MainActivity).setListener(this)

    }

    private fun setupUI() {
//        if (mMap == null) {
//        }
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.maps) as WorkAroundMapFragment
        mapFragment.getMapAsync { googleMap: GoogleMap ->
            mMap = googleMap
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            Log.e("TAG", "setupUI: " + mMap)
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@getMapAsync
            }
            Log.e("TAG", "setupUI: " + mMap)
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isZoomControlsEnabled = true
            mMap.setMaxZoomPreference(16f)
            mMap.uiSettings.isTiltGesturesEnabled = false
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-5.1609313, 119.4467747)))
        }
    }

    override fun setScrollView() {
        binding.scrollView.requestDisallowInterceptTouchEvent(true)
    }


}
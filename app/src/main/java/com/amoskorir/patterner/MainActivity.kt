package com.amoskorir.patterner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    var googleMap: GoogleMap? = null
    lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeMap(savedInstanceState)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
        googleMap?.let {
            val zoomLevel = 7.0f //This goes up to 21
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-0.3031, 36.0800), zoomLevel))
            drawPolygon(it)
        }
    }

    private fun drawPolygon(map: GoogleMap) {
        val one = LatLng(0.5143, 35.2698)
        val two = LatLng(-0.3031, 36.0800)
        val four = LatLng(-1.2921, 36.8219)
        val three = LatLng(-0.0917, 34.7680)


        val list = listOf(one, two, four,three)

        val polygonOptions = PolygonOptions()
        polygonOptions.addAll(list)
        val groundOverlay = GMapPattern.getBitmapPolygonGround(
            this,
            polygonOptions,
            500,
            500,
            R.drawable.example_pattern
        )

        map.addGroundOverlay(groundOverlay)

        map.addPolygon(polygonOptions)
    }

    private fun initializeMap(savedInstanceState: Bundle?) {
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        MapsInitializer.initialize(this)
        mapView.onResume()
        mapView.getMapAsync(this)
    }
}
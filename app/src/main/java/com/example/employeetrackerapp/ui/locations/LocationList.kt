package com.example.employeetrackerapp.ui.locations

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeetrackerapp.data.dao.LocationDao
import com.example.employeetrackerapp.data.database.DatabaseHelper
import com.example.employeetrackerapp.data.repository.LocationRepository
import com.example.employeetrackerapp.databinding.ActivityLocationListBinding
import com.example.employeetrackerapp.viewmodel.LocationViewModel
import com.example.employeetrackerapp.viewmodel.LocationViewModelFactory

class LocationList : AppCompatActivity() {
    private lateinit var binding: ActivityLocationListBinding

    private lateinit var viewModel: LocationViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityLocationListBinding.inflate(
                layoutInflater
            )
        setContentView(binding.root)

        val dbHelper = DatabaseHelper(this)
        val locationDao = LocationDao(dbHelper)
        val repository = LocationRepository(locationDao)
        val factory = LocationViewModelFactory(repository)

        viewModel =
            ViewModelProvider(
                this,
                factory
            )[LocationViewModel::class.java]

        binding.rvLocations.layoutManager =
            LinearLayoutManager(this)

        viewModel.location.observe(this) { locations ->
            binding.tvLocationCount.text =
                "${locations.size} Locations"

            binding.rvLocations.adapter =
                LocationAdapter(
                    locations = locations,

                    onViewQrClick = { location ->
                        // QR screen will be added later
                    },
                    onEditClick = { location ->
                        val intent =
                            Intent(
                                this,
                                LocationForm::class.java
                            )
                        intent.putExtra(
                            "LOCATION_ID",
                            location.id
                        )

                        startActivity(intent)

                    },
                    onDeleteClick = { location ->
                        AlertDialog.Builder(this)
                            .setTitle("Confirmation")
                            .setMessage(
                                "Are you sure you want to delete " +
                                "${location.locationName}?"
                            )
                            .setNegativeButton(
                                "Cancel",
                                null
                            )
                            .setPositiveButton("" +
                                    "Yes"
                            ) { _, _ ->
                                val success =
                                    viewModel.deleteLocation(
                                        location.id
                                    )
                                if (success) {
                                    Toast.makeText(
                                        this,
                                        "Location deleted successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    viewModel.loadLocations()
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Failed to delete location",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            .show()
                    }
                )
        }
        binding.fabAddLocation.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LocationForm::class.java
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        if(::viewModel.isInitialized){
            viewModel.loadLocations()
        }
    }
}


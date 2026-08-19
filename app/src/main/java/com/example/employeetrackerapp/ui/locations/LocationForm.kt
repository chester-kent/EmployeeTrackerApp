package com.example.employeetrackerapp.ui.locations

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeetrackerapp.data.dao.LocationDao
import com.example.employeetrackerapp.data.database.DatabaseHelper
import com.example.employeetrackerapp.data.model.Location
import com.example.employeetrackerapp.data.repository.LocationRepository
import com.example.employeetrackerapp.databinding.ActivityLocationFormBinding
import com.example.employeetrackerapp.viewmodel.LocationViewModel
import com.example.employeetrackerapp.viewmodel.LocationViewModelFactory

class LocationForm : AppCompatActivity() {

    private lateinit var binding: ActivityLocationFormBinding
    private lateinit var viewModel: LocationViewModel
    private var locationId: Int = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityLocationFormBinding.inflate(
                layoutInflater
            )
        setContentView(binding.root)

        locationId =
            intent.getIntExtra(
                "LOCATION_ID",
                -1
            )

        val dbHelper = DatabaseHelper(this)
        val locationDao = LocationDao(dbHelper)
        val repository = LocationRepository(locationDao)
        val factory = LocationViewModelFactory(repository)

        viewModel =
            ViewModelProvider(
                this,
                factory
            )[LocationViewModel::class.java]

        if (locationId != -1) {
            val location =
                viewModel.getLocationById(
                    locationId
                )
            if (location != null) {
                binding.etLocationName.setText(
                    location.locationName
                )
                binding.etDescription.setText(
                    location.description
                )
                binding.btnSaveLocation.text = "Update Location"
            }
        }

        binding.btnSaveLocation.setOnClickListener {
            saveLocation()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun saveLocation() {
        val locationName =
            binding.etLocationName.text
                .toString()
                .trim()

        val description =
            binding.etDescription.text
                .toString()
                .trim()

        if (locationName.isEmpty()) {
            Toast.makeText(this,
                "Please enter location name",
                Toast.LENGTH_SHORT)
                .show()
            return
        }

        if(locationId == -1) {

            binding.btnSaveLocation.text = "Save Location"
            val temporaryQr =
                "TEMP_${System.currentTimeMillis()}"

            val location =
                Location(
                    locationName = locationName,
                    description = description,
                    qrCode = temporaryQr
                )

            val success =
                viewModel.insertLocation(
                    location
                )

            if (success) {
                Toast.makeText(
                    this,
                    "Location added successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Failed to add location",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {

            val existingLocation =
                viewModel.getLocationById(
                    locationId
                )

            if (existingLocation == null) {
                Toast.makeText(
                    this,
                    "Location not found",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            val location =
                Location(
                    id = locationId,
                    locationName = locationName,
                    description = description,
                    qrCode = existingLocation.qrCode
                )

            val success =
                viewModel.updateLocation(
                    location
                )

            if (success) {
                Toast.makeText(
                    this,
                    "Location updated successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Failed to updated location",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
package com.example.employeetrackerapp.ui.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetrackerapp.data.model.Location
import com.example.employeetrackerapp.databinding.ItemLocationBinding

class LocationAdapter(
    private val locations: List<Location>,
    private val onViewQrClick: (Location) -> Unit,
    private val onEditClick: (Location) -> Unit,
    private val onDeleteClick: (Location) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(
        val binding: ItemLocationBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):  LocationViewHolder {
        val binding =
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LocationViewHolder,
        position: Int
    ) {
        val location = locations[position]

        holder.binding.apply {
            txtLocationName.text =
                location.locationName

            txtDescription.text =
                location.description

            txtQrCode.text =
                location.qrCode

            btnViewQr.setOnClickListener {
                onViewQrClick(location)
            }

            btnEdit.setOnClickListener {
                onEditClick(location)
            }

            btnDelete.setOnClickListener {
                onDeleteClick(location)
            }

        }
    }

    override fun getItemCount(): Int {
        return locations.size
    }
}
package com.example.employeetrackerapp.ui.employees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetrackerapp.data.model.Employees
import com.example.employeetrackerapp.databinding.ItemEmployeeBinding

class EmployeeAdapter(
    private val employees: List<Employees>
): RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(
        val binding: ItemEmployeeBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EmployeeViewHolder,
        position: Int
    ) {
        val employees = employees[position]

        holder.binding.apply {
            txtName.text =
                "${employees.firstname} ${employees.lastname}"

            txtDepartment.text =
                employees.department
        }
    }

    override fun getItemCount(): Int {
        return employees.size
    }
}
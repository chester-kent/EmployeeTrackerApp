package com.example.employeetrackerapp.ui.employees

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetrackerapp.data.model.Employees
import com.example.employeetrackerapp.databinding.ItemEmployeeBinding

class EmployeeAdapter(
    private val employees: List<Employees>,
    private val onEditClick: (Employees) -> Unit,
    private val onDeleteClick: (Employees) -> Unit,
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

    @SuppressLint("SetTextI18n")
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

            btnEdit.setOnClickListener {
                onEditClick(employees)
            }
            btnDelete.setOnClickListener {
                onDeleteClick(employees)
            }
        }
    }

    override fun getItemCount(): Int {
        return employees.size
    }
}
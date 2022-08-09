package com.develo.ff_arsimulator.ui.modules

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.data.source.local.entity.LabEntity
import com.develo.ff_arsimulator.data.source.local.entity.TheoryEntity
import com.develo.ff_arsimulator.databinding.ItemNestedModuleBinding
import com.develo.ff_arsimulator.ui.modules.ModuleDetailActivity.Companion.EXTRA_ID

class NestedModuleAdapter :
    ListAdapter<Any, NestedModuleAdapter.NestedViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Any> =
            object : DiffUtil.ItemCallback<Any>() {
                override fun areItemsTheSame(
                    oldUser: Any,
                    newUser: Any
                ): Boolean {
                    if (oldUser is TheoryEntity && newUser is TheoryEntity) {
                        return oldUser.id == newUser.id
                    }
                    if (oldUser is LabEntity && newUser is LabEntity) {
                        return oldUser.id == newUser.id
                    }
                    return oldUser == newUser
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: Any,
                    newUser: Any
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
        val binding =
            ItemNestedModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NestedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        val moduleItem = getItem(position)
        holder.bind(moduleItem)
    }

    class NestedViewHolder(val binding: ItemNestedModuleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moduleItem: Any) {
            when (moduleItem) {
                is TheoryEntity -> {
                    binding.tvItemTitle.text = itemView.context.getString(
                        R.string.module_item_text,
                        moduleItem.category,
                        moduleItem.moduleNumber.toString(),
                        moduleItem.theoryNumber.toString(),
                        moduleItem.title
                    )
                    binding.cardView.setOnClickListener {
                        val intent = Intent(itemView.context, ModuleDetailActivity::class.java)
                        intent.putExtra(EXTRA_ID, moduleItem.id)
                        itemView.context.startActivity(intent)
                    }
                }
                is LabEntity -> {
                    binding.tvItemTitle.text = itemView.context.getString(
                        R.string.module_item_text,
                        moduleItem.category,
                        moduleItem.moduleNumber.toString(),
                        moduleItem.labNumber.toString(),
                        moduleItem.title
                    )
                    binding.cardView.setOnClickListener {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://ffa-simulator.herokuapp.com/ARApp/${moduleItem.moduleId}/${moduleItem.id}")
                        ).also {
                            itemView.context.startActivity(it)
                        }
                    }
                }
            }
        }
    }
}
package com.develo.ff_arsimulator.ui.modules

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.data.source.local.entity.relations.ModuleWithTheoryWithLab
import com.develo.ff_arsimulator.databinding.ItemModuleBinding

class ModuleAdapter :
    ListAdapter<ModuleWithTheoryWithLab, ModuleAdapter.ModuleViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ModuleWithTheoryWithLab> =
            object : DiffUtil.ItemCallback<ModuleWithTheoryWithLab>() {
                override fun areItemsTheSame(
                    oldUser: ModuleWithTheoryWithLab,
                    newUser: ModuleWithTheoryWithLab
                ): Boolean {
                    return oldUser.moduleEntity.id == newUser.moduleEntity.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: ModuleWithTheoryWithLab,
                    newUser: ModuleWithTheoryWithLab
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val binding = ItemModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = getItem(position)
        holder.bind(module)
    }

    class ModuleViewHolder(val binding: ItemModuleBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(module: ModuleWithTheoryWithLab) {
            val listModuleItem = mutableListOf<Any>()
            module.theory.map { theory ->
                listModuleItem.add(theory)
            }
            module.lab.map { lab ->
                listModuleItem.add(lab)
            }

            binding.tvModuleNumber.text = itemView.context.getString(
                R.string.module_number_text,
                module.moduleEntity.moduleNumber.toString()
            )
            binding.tvModuleTitle.text = module.moduleEntity.moduleTitle
            binding.expandableLayout.visibility = when (module.moduleEntity.isExpandable) {
                true -> View.VISIBLE
                false -> View.GONE
            }

            val nestedAdapter = NestedModuleAdapter()
            nestedAdapter.submitList(listModuleItem)
            binding.rvChildModule.apply {
                layoutManager = LinearLayoutManager(itemView.context)
                setHasFixedSize(true)
                adapter = nestedAdapter
            }
            binding.headerLayout.setOnClickListener {
                module.moduleEntity.setExpendable(!module.moduleEntity.isExpandable)
                bindingAdapter?.notifyDataSetChanged()
            }
        }
    }
}
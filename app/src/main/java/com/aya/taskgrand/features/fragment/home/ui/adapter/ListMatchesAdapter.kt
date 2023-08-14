package com.aya.taskgrand.features.fragment.home.ui.adapter

import com.aya.taskgrand.R
import com.aya.taskgrand.base.BaseAdapter
import com.aya.taskgrand.base.BaseViewHolder
import com.aya.taskgrand.databinding.ItemMatchBinding
import com.aya.taskgrand.features.fragment.home.data.MatchesItems


class ListMatchesAdapter()
    : BaseAdapter<MatchesItems, ItemMatchBinding>( ){
    override fun layoutRes(): Int = R.layout.item_match

    override fun onViewHolderCreated(viewHolder: BaseViewHolder<MatchesItems>) {
        super.onViewHolderCreated(viewHolder)

    }

    override fun onBindItemBinding(
        binding: ItemMatchBinding,
        item: MatchesItems,
        position: Int
    ) {
        binding.apply {

        }
        super.onBindItemBinding(binding, item, position)

    }

}
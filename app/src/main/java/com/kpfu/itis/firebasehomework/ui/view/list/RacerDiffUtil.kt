package com.kpfu.itis.firebasehomework.ui.view.list

import androidx.recyclerview.widget.DiffUtil
import com.kpfu.itis.firebasehomework.model.Racer

class RacerDiffUtil(
    private var oldItems: List<Racer>,
    private var newItems: List<Racer>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].name == newItems[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
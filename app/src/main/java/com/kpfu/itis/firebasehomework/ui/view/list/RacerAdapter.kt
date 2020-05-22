package com.kpfu.itis.firebasehomework.ui.view.list

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kpfu.itis.firebasehomework.model.Racer
import com.kpfu.itis.firebasehomework.ui.view.list.RacerItemHolder.Companion.KEY_NAME
import com.kpfu.itis.firebasehomework.ui.view.list.RacerItemHolder.Companion.KEY_TEAM

class RacerAdapter(
    private var dataSource: ArrayList<Racer>,
    var clickLambda: (Racer) -> Unit
): ListAdapter<Racer, RacerItemHolder>(object : DiffUtil.ItemCallback<Racer>(){

    override fun areItemsTheSame(oldItem: Racer, newItem: Racer): Boolean = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Racer, newItem: Racer): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Racer, newItem: Racer): Any? {
        val diffBundle = Bundle()
        if (oldItem.name !== newItem.name) {
            diffBundle.putString(KEY_NAME, newItem.name)
        }
        if (oldItem.team != newItem.team) {
            diffBundle.putString(KEY_TEAM, newItem.team)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}) {

    override fun onBindViewHolder(holder: RacerItemHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacerItemHolder =
        RacerItemHolder.create(
            parent,
            clickLambda
        )

    override fun onBindViewHolder(
        holder: RacerItemHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as? Bundle
            holder.updateFromBundle(bundle)
        }
    }

    fun updateList(newList: ArrayList<Racer>) {
        val result = DiffUtil.calculateDiff(
            RacerDiffUtil(
                this.dataSource,
                newList
            ), true
        ).dispatchUpdatesTo(this)
        this.dataSource.clear()
        this.dataSource.addAll(newList)
  }
}
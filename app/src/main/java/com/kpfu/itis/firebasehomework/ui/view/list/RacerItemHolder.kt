package com.kpfu.itis.firebasehomework.ui.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kpfu.itis.firebasehomework.R
import com.kpfu.itis.firebasehomework.model.Racer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.racer_item.view.*

class RacerItemHolder(
    override val containerView: View,
    private val clickLambda: (Racer) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(racer: Racer) {
        containerView.apply {
            tv_name.text = racer.name
            tv_team.text = racer.team
            itemView.setOnClickListener {
                clickLambda(racer)
            }
        }
    }

    fun updateFromBundle(bundle: Bundle?) {
        containerView.apply {
            bundle?.apply {
                tv_name.text = getString(KEY_NAME)
                tv_team.text = getString(KEY_TEAM)
            }
        }
    }

    companion object {
        const val KEY_NAME = "name"
        const val KEY_TEAM = "team"
        fun create(parent: ViewGroup, clickLambda: (Racer) -> Unit) =
            RacerItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.racer_item, parent, false),
                clickLambda
            )
    }
}
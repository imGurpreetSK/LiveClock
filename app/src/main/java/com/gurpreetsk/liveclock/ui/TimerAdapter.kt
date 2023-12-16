package com.gurpreetsk.liveclock.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.gurpreetsk.liveclock.ui.model.TimeElement
import com.gurpreetsk.liveclock.databinding.ListItemTimerBinding

class TimerAdapter : ListAdapter<TimeElement, TimerAdapter.TimeElementViewHolder>(TimerDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeElementViewHolder = TimeElementViewHolder(
        ListItemTimerBinding.inflate(
            LayoutInflater.from(parent.context), null, false
        ).root
    )

    override fun onBindViewHolder(holder: TimeElementViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class TimeElementViewHolder(
        private val item: MaterialTextView
    ) : RecyclerView.ViewHolder(item) {

        fun bind(element: TimeElement) {
            item.text = if (!element.isSeparator) {
                element.value.toString()
            } else {
                ":"
            }
        }
    }
}

private object TimerDiffUtil : DiffUtil.ItemCallback<TimeElement>() {

    override fun areItemsTheSame(
        oldItem: TimeElement,
        newItem: TimeElement
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: TimeElement,
        newItem: TimeElement
    ): Boolean = oldItem.value == newItem.value
}

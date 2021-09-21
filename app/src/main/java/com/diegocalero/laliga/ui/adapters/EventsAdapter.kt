package com.diegocalero.laliga.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegocalero.laliga.databinding.ItemListEventBinding
import com.diegocalero.laliga.models.EventModel
import com.diegocalero.laliga.ui.utils.ImageViewUtils

class EventsAdapter(var events: List<EventModel>): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class ViewHolder(private val binding: ItemListEventBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventModel) {
            binding.textviewHomeTeamName.text = event.strHomeTeam
            binding.textViewAwayTeamName.text = event.strAwayTeam
            binding.textViewMatchDate.text = event.dateEvent
            binding.textViewMatchTime.text = event.strTime
            ImageViewUtils.loadImageUrl(binding.root.context, binding.imageViewMatchThumb, event.strThumb)
        }
    }
}
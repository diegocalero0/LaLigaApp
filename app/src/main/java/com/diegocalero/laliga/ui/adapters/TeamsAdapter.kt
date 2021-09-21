package com.diegocalero.laliga.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegocalero.laliga.R
import com.diegocalero.laliga.databinding.ItemListTeamBinding
import com.diegocalero.laliga.models.TeamModel
import com.diegocalero.laliga.ui.utils.ImageViewUtils

class TeamsAdapter(var teams: List<TeamModel>, private val onTeamListener: OnTeamListener): RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onTeamListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    class ViewHolder(private val binding: ItemListTeamBinding, private val onTeamListener: OnTeamListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(team: TeamModel) {

            binding.root.setOnClickListener(this)
            binding.textViewTeamName.text = team.strTeam
            binding.textViewTeamStadium.text = binding.root.resources.getString(R.string.string_stadium_name, team.strStadium)
            ImageViewUtils.loadImageUrl(binding.root.context, binding.imageViewTeamBadge, team.strTeamBadge)
            ImageViewUtils.loadImageUrl(binding.root.context, binding.imageViewStadium, team.strStadiumThumb)
        }

        override fun onClick(view: View?) {
            onTeamListener.onTeamClick(adapterPosition)
        }
    }

    interface OnTeamListener {
        fun onTeamClick(position: Int)
    }

}
package com.diegocalero.laliga.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegocalero.laliga.R
import com.diegocalero.laliga.databinding.FragmentTeamDetailBinding
import com.diegocalero.laliga.ui.adapters.EventsAdapter
import com.diegocalero.laliga.ui.utils.ImageViewUtils
import com.diegocalero.laliga.viewmodels.TeamDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : Fragment() {

    private lateinit var binding: FragmentTeamDetailBinding
    private val viewModel: TeamDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamDetailBinding.inflate(layoutInflater)
        initTeamDetails()
        initEventsRecyclerView()
        return binding.root
    }

    private fun initTeamDetails() {
        viewModel.team?.apply {
            ImageViewUtils.loadImageUrl(context, binding.imageViewTeamLogo, strTeamBadge)
            ImageViewUtils.loadImageUrl(context, binding.imageViewJersey, strTeamJersey)
            if(strWebsite != null && strWebsite.isNotBlank()) {
                binding.imageViewNetwork.visibility = View.VISIBLE
            }
            if(strFacebook != null && strFacebook.isNotBlank()) {
                binding.imageViewFacebook.visibility = View.VISIBLE
            }
            if(strInstagram != null && strInstagram.isNotBlank()) {
                binding.imageViewInstagram.visibility = View.VISIBLE
            }
            if(strTwitter != null && strTwitter.isNotBlank()) {
                binding.imageViewTwitter.visibility = View.VISIBLE
            }
            binding.textViewTeamDetailName.text = strTeam
            binding.textViewTeamDescription.text = strDescriptionEN
            binding.textViewTeamFoundationYear.text = resources.getString(
                R.string.string_foundation_year,
                intFormedYear
            )
        }
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.textViewTeamDescription.setOnClickListener {
            if(binding.textViewTeamDescription.maxLines == Integer.MAX_VALUE) {
                binding.textViewTeamDescription.maxLines = 5
            } else {
                binding.textViewTeamDescription.maxLines = Integer.MAX_VALUE
            }
        }

        initBrowserListener(binding.imageViewFacebook, viewModel.team?.strFacebook)
        initBrowserListener(binding.imageViewTwitter, viewModel.team?.strTwitter)
        initBrowserListener(binding.imageViewInstagram, viewModel.team?.strInstagram)
        initBrowserListener(binding.imageViewNetwork, viewModel.team?.strWebsite)

    }

    private fun initBrowserListener(view: View, url: String?) {
        view.setOnClickListener {
            var urlToOpem = url
            if(urlToOpem != null && urlToOpem.isNotBlank()) {
                if (!urlToOpem.startsWith("http://") && !urlToOpem.startsWith("https://")) {
                    urlToOpem = "http://$url"
                }

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlToOpem))
                startActivity(browserIntent)
            }
        }
    }

    private fun initEventsRecyclerView() {
        binding.recyclerViewEvents.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        viewModel.events.observe(viewLifecycleOwner) {
            it?.let { events ->
                if(binding.recyclerViewEvents.adapter == null) {
                    binding.recyclerViewEvents.adapter = EventsAdapter(events)
                } else {
                    (binding.recyclerViewEvents.adapter as EventsAdapter).events = events
                }

                binding.recyclerViewEvents.adapter?.notifyDataSetChanged()
            }
        }
    }

}
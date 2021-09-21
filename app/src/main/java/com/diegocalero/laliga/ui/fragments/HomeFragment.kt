package com.diegocalero.laliga.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegocalero.laliga.R
import com.diegocalero.laliga.databinding.FragmentHomeBinding
import com.diegocalero.laliga.ui.adapters.TeamsAdapter
import com.diegocalero.laliga.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener, TeamsAdapter.OnTeamListener {

    companion object {
        const val TAG: String = "HomeFragment"
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        initSpinner()
        initTeamsRecyclerView()

        return binding.root
    }

    private fun initTeamsRecyclerView() {
        binding.recyclerViewTeams.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,
            false)

        viewModel.teams.observe(viewLifecycleOwner) {
            it?.let { teams ->
                if(binding.recyclerViewTeams.adapter == null) {
                    binding.recyclerViewTeams.adapter = TeamsAdapter(teams, this)
                } else {
                    (binding.recyclerViewTeams.adapter as TeamsAdapter).teams = teams
                }

                binding.recyclerViewTeams.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun initSpinner() {
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.leagues_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerLeagues.adapter = adapter
                binding.spinnerLeagues.onItemSelectedListener = this
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        parent?.let { it ->
            viewModel.listTeams(it.getItemAtPosition(pos) as String)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(TAG, "Nothing Selected")
    }

    override fun onTeamClick(position: Int) {
        viewModel.teams.value?.let { teams ->
            view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToTeamDetailFragment(teams[position]))
        }
    }
}
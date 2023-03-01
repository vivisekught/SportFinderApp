package com.example.sportfinderapp.presentation.fragments.home

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.FragmentHomeBinding
import com.example.sportfinderapp.domain.Training
import com.example.sportfinderapp.presentation.adapters.TrainingAdapter

class HomeFragment : Fragment() {

    private lateinit var trainingAdapter: TrainingAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            LayoutInflater.from(inflater.context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupActionBar()
        viewModel.trainingList.observe(viewLifecycleOwner) {
            trainingAdapter.submitList(it)
        }

    }

    private fun setupRecyclerView() {
        with(binding.trainingRv) {
            trainingAdapter = TrainingAdapter()
            adapter = trainingAdapter
        }
        setupOnClickListener()
        setupOnMoreClickListener()
    }

    private fun setupOnClickListener() {
        trainingAdapter.setOnClickListener = {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToTrainingFragment(it)
            )
        }
    }


    private fun setupOnMoreClickListener() {
        trainingAdapter.setOnMoreClickListener = { view, id ->
            val popupMenu = PopupMenu(requireContext(),view)
            popupMenu.menuInflater.inflate(R.menu.training_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.training_archive ->
                        Log.d("setupOnMoreClick", "archive $id")
                    R.id.training_mute ->
                        Log.d("setupOnMoreClick", "mute $id")
                    R.id.training_pin ->
                        Log.d("setupOnMoreClick", "pin $id")
                }

                true
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            popupMenu.show()
        }
    }

    private fun setupActionBar(){
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.home_fragment_action_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_bar_home_archive -> {
                        Log.d("setupOnMoreClick", "archive")
                        true
                    }
                    R.id.action_bar_home_notification -> {
                        Log.d("setupOnMoreClick", "notification")
                        true
                    }
                    R.id.action_bar_home_profile -> {
                        Log.d("setupOnMoreClick", "profile")
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}
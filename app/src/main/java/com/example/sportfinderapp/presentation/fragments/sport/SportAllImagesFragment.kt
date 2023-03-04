package com.example.sportfinderapp.presentation.fragments.sport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.R
import com.example.sportfinderapp.presentation.adapters.SportAllImageAdapter


class SportAllImagesFragment : Fragment() {

    private lateinit var sportAllImageAdapter: SportAllImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport_all_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.sport_page_all_images_rw)
        val training = R.drawable.box
        sportAllImageAdapter = SportAllImageAdapter(
            intArrayOf(
                training,
                R.drawable.vertical_test,
                training,
                training,
                training,
                training,
                R.drawable.vertical_test
            )
        )
        recyclerView.adapter = sportAllImageAdapter
        setupOnClickListener()
    }

    private fun setupOnClickListener() {
        sportAllImageAdapter.setOnClickListener = { images, position ->
            findNavController().navigate(
                SportAllImagesFragmentDirections.actionSportAllImagesFragmentToFullscreenSportImageFragment(
                    images,
                    position
                )
            )
        }
    }
}
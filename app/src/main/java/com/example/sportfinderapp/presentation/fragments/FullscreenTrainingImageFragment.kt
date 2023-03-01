package com.example.sportfinderapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.sportfinderapp.R
import com.example.sportfinderapp.presentation.adapters.ViewPagerImageAdapter
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class FullscreenTrainingImageFragment : Fragment() {

    private val args by navArgs<FullscreenTrainingImageFragmentArgs>()
    private lateinit var viewPager: ViewPager2

    private var currentImagePosition by Delegates.notNull<Int>()
    private lateinit var images: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentImagePosition = args.currentImagePos
        images = args.images
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_fullscreen_training_image,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerAdapter(view)
    }

    private fun setupViewPagerAdapter(view: View){
        viewPager = view.findViewById(R.id.fullscreen_image_pager)
        val viewPagerImageAdapter = ViewPagerImageAdapter(images)

        with(viewPager){
            adapter = viewPagerImageAdapter
            fakeDragBy(FAKE_DRAG_OFFSET)
            endFakeDrag()
            doOnPreDraw {
                viewPager.setCurrentItem(currentImagePosition, false)
            }
        }
    }

    companion object {
        private const val FAKE_DRAG_OFFSET = -10f
    }
}
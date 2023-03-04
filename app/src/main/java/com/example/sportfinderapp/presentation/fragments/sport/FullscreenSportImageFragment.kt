package com.example.sportfinderapp.presentation.fragments.sport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.sportfinderapp.R
import com.example.sportfinderapp.presentation.adapters.ViewPagerImageAdapter
import kotlin.properties.Delegates

class FullscreenSportImageFragment : Fragment() {

    private val args by navArgs<FullscreenSportImageFragmentArgs>()
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
            R.layout.fragment_fullscreen_sport_image,
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
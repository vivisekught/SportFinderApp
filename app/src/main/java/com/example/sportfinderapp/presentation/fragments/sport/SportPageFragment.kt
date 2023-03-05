package com.example.sportfinderapp.presentation.fragments.sport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.FragmentSportPageBinding
import com.example.sportfinderapp.domain.entity.Sport
import com.example.sportfinderapp.domain.entity.Training
import com.example.sportfinderapp.presentation.adapters.SportImagesAdapter
import com.example.sportfinderapp.presentation.adapters.TrainingAdapter
import com.example.sportfinderapp.presentation.adapters.TrainingDatesAdapter
import com.example.sportfinderapp.util.Level


class SportPageFragment : Fragment() {
    private val args by navArgs<SportPageFragmentArgs>()

    private lateinit var sportImageAdapter: SportImagesAdapter
    private lateinit var trainingDatesAdapter: TrainingDatesAdapter

    private var _binding: FragmentSportPageBinding? = null
    private val binding: FragmentSportPageBinding
        get() = _binding ?: throw RuntimeException("FragmentSportBinding == null")

    val sportImage = R.drawable.box
    val sportImages = intArrayOf(
        sportImage,
        sportImage,
        R.drawable.vertical_test,
        sportImage,
        sportImage,
        R.drawable.vertical_test
    )

    //    private var training: Training
    private lateinit var sport: Sport

    companion object {
        fun newInstance() = SportPageFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[SportViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sport = args.sport
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSportPageBinding.inflate(
            LayoutInflater.from(inflater.context), container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDescriptionEllipsize()
        setupOnClickListeners()
        setupRecyclerViews()

    }

    private fun setupOnClickListeners() {

        binding.sportPageShowAllImages.setOnClickListener {
            findNavController().navigate(
                SportPageFragmentDirections.actionSportPageFragmentToSportAllImagesFragment(sportImages)
            )
        }

        var flag = true
        binding.sportPageFullDescriptionTv.setOnClickListener {
            if (flag) {
                binding.sportPageDescriptionTv.maxLines = 50
                binding.sportPageFullDescriptionTv.text = "less"
                flag = false
            } else {
                binding.sportPageDescriptionTv.maxLines = 2
                binding.sportPageFullDescriptionTv.text = "more"
                flag = true
            }
        }
    }

    private fun checkDescriptionEllipsize(){

        with(binding) {
            sportPageDescriptionTv.waitForLayout {
                if (sportPageDescriptionTv.layout.text.toString() != sportPageDescriptionTv.text.toString()) {
                    sportPageFullDescriptionTv.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRecyclerViews() {
        setupSportImagesRecyclerView()
        setupTrainingDatesRecyclerView()
    }

    private fun setupTrainingDatesRecyclerView() {
        with(binding.sportPageTrainingDatesRw){
            trainingDatesAdapter = TrainingDatesAdapter(
                getTrainings()
            )
            trainingDatesAdapter.setOnClickListener = {
                findNavController().navigate(
                    SportPageFragmentDirections.actionSportPageFragmentToTrainingPageFragment(
                        it
                    )
                )
            }
            adapter = trainingDatesAdapter
        }
    }

    private fun getTrainings(): List<List<Training> >{
        return arrayListOf(
            arrayListOf(
                Training(1,1, "28.01", "12:20", Level.BEGINNER, 40, 25, 15),
                Training(2,1, "28.01", "14:20", Level.EXPERT, 40, 15, 15),
                Training(3,1, "28.01", "16:20", Level.INTERMEDIATE, 40, 35, 15),
                Training(4,1, "28.01", "18:20", Level.FOR_ALL, 40, 12, 15),
                Training(5,1, "28.01", "19:20", Level.DIFFERENT, 40, 10, 15),
            ),
            arrayListOf(
                Training(1,1, "29.01", "12:20", Level.BEGINNER, 40, 25, 15),
                Training(2,1, "29.01", "14:20", Level.EXPERT, 40, 15, 15),
            ),
            arrayListOf(
                Training(1,1, "30.01", "12:20", Level.BEGINNER, 40, 25, 15),
                Training(2,1, "30.01", "14:20", Level.EXPERT, 40, 15, 15),
                Training(3,1, "30.01", "16:20", Level.INTERMEDIATE, 40, 35, 15),
                Training(4,1, "30.01", "18:20", Level.FOR_ALL, 40, 12, 15),
                Training(5,1, "30.01", "19:20", Level.DIFFERENT, 40, 10, 15),
                Training(5,1, "30.01", "19:20", Level.DIFFERENT, 40, 10, 15),
                Training(5,1, "30.01", "19:20", Level.DIFFERENT, 40, 10, 15),
            ),
        )
    }

    private fun setupSportImagesRecyclerView(){
        with(binding.sportPageImageRw) {
            sportImageAdapter = SportImagesAdapter(
                sportImages
            )
            adapter = sportImageAdapter
        }
        setupOnImageClickListener()
    }



    private fun setupOnImageClickListener() {
        sportImageAdapter.setOnClickListener = { position ->
            findNavController().navigate(
                SportPageFragmentDirections.actionSportPageFragmentToFullscreenSportImageFragment(
                    sportImages,
                    position
                )
            )
        }
    }

    private inline fun View.waitForLayout(crossinline func: () -> Unit) = with(viewTreeObserver) {
        addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                func()
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
//    override fun onResume() {
//        super.onResume()
//        val actionBar: ActionBar? = (activity as AppCompatActivity?)?.supportActionBar
//        if (actionBar != null) {
//            actionBar.title = training.title
//        }
//    }
}
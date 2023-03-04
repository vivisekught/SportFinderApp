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
import com.example.sportfinderapp.databinding.FragmentSportBinding
import com.example.sportfinderapp.domain.entity.Sport
import com.example.sportfinderapp.presentation.adapters.SportImagesAdapter


class SportFragment : Fragment() {
    private val args by navArgs<SportFragmentArgs>()

    private lateinit var sportImageAdapter: SportImagesAdapter

    private var _binding: FragmentSportBinding? = null
    private val binding: FragmentSportBinding
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
        fun newInstance() = SportFragment()
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
        _binding = FragmentSportBinding.inflate(
            LayoutInflater.from(inflater.context), container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            sportPageDescriptionTv.waitForLayout {
                if (sportPageDescriptionTv.layout.text.toString() != sportPageDescriptionTv.text.toString()) {
                    sportPageFullDescriptionTv.visibility = View.VISIBLE
                }
            }
        }

        setupRecyclerView()

        binding.sportPageShowAllImages.setOnClickListener {
            findNavController().navigate(
                SportFragmentDirections.actionSportFragmentToSportAllImagesFragment(sportImages)
            )
        }

//        binding.trainingPageEnrollButton.setOnClickListener {
//            val builder: AlertDialog.Builder? = activity?.let {
//                AlertDialog.Builder(it)
//            }
//            builder?.apply {
//                setTitle(R.string.add_training)
//                setNegativeButton(R.string.view_all) { dialog, id ->
//                    // User cancelled the dialog
//                }
//                setMultiChoiceItems(
//                    arrayOf("a", "b"),
//                    null,
//                    DialogInterface.OnMultiChoiceClickListener { _, which, isChecked ->
//                        Log.d("Nikita", which.toString())
//                    })
//            } ?: throw java.lang.RuntimeException("Activity is null")
//
//            val dialog: AlertDialog? = builder.create()
//            dialog?.show()
//
//        }


//        var flag = true
//        binding.trainingPageAllDescriptionTv.setOnClickListener {
//            if (flag) {
//                binding.trainingPageDescriptionTv.maxLines = 50
//                binding.trainingPageAllDescriptionTv.text = "less"
//                flag = false
//            } else {
//                binding.trainingPageDescriptionTv.maxLines = 2
//                binding.trainingPageAllDescriptionTv.text = "more"
//                flag = true
//            }
//        }
    }

    private fun setupRecyclerView() {

        with(binding.sportPageImageRw) {
            sportImageAdapter = SportImagesAdapter(
                sportImages
            )
            adapter = sportImageAdapter
        }

        setupOnClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupOnClickListener() {
        sportImageAdapter.setOnClickListener = { position ->
            findNavController().navigate(
                SportFragmentDirections.actionSportFragmentToFullscreenSportImageFragment(
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
//    override fun onResume() {
//        super.onResume()
//        val actionBar: ActionBar? = (activity as AppCompatActivity?)?.supportActionBar
//        if (actionBar != null) {
//            actionBar.title = training.title
//        }
//    }
}
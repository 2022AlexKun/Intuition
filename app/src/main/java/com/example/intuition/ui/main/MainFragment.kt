package com.example.intuition.ui.main

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.intuition.R
import com.example.intuition.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var miliseconds: Long = 4000L
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        binding.apply {

            val countDownTimer = object : CountDownTimer(miliseconds, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tvTimeCounter.text = "${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    tvTimeCounter.text = "Looooose!!!"
                    imgRandom.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.transparent
                        )
                    )
                }
            }.start()

            btnGreenColor.setOnClickListener {
                imgRandom.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                miliseconds = 10000L
                countDownTimer.start()
            }

            btnRedColor.setOnClickListener {
                imgRandom.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                miliseconds = 10000L
                countDownTimer.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.kldaji.roulette.ui.roulette

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.kldaji.roulette.R
import com.kldaji.roulette.databinding.FragmentRouletteBinding

class RouletteFragment : Fragment() {
    private var _binding: FragmentRouletteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRouletteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addRoulettePinView()
        startButtonTouchListener()
    }

    private fun addRoulettePinView() {
        val pinView = RoulettePinView(requireContext())
        val roulettePinBitmap =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_roulette_pin, null)?.toBitmap()
        roulettePinBitmap?.let {
            pinView.roulettePinBitmap = it
        }
        binding.root.addView(pinView)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun startButtonTouchListener() {
        binding.startBtnRoulette.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.performClick()
                }
                MotionEvent.ACTION_UP -> {
                    v.performClick()
                    val rotateDegree = (3000..10000).random().toFloat()
                    val duration = (rotateDegree * 2).toLong()
                    binding.rouletteView.startRouletteViewAnimation(rotateDegree, duration)
                    binding.startBtnRoulette.isVisible = false
                }
            }
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

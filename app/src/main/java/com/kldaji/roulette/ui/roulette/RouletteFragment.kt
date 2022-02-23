package com.kldaji.roulette.ui.roulette

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

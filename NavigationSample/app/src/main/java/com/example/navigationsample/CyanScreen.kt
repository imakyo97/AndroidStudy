package com.example.navigationsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.navigationsample.databinding.FragmentCyanScreenBinding

class CyanScreen : Fragment() {
    private var binding: FragmentCyanScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCyanScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btBackModal?.setOnClickListener(OnClickListener())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private inner class OnClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            val navController = view?.findNavController()
            navController?.popBackStack()
        }
    }
}
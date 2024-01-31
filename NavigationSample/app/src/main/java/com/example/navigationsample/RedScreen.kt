package com.example.navigationsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationsample.databinding.FragmentRedScreenBinding

class RedScreen : Fragment() {
    private var binding: FragmentRedScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRedScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btBack?.setOnClickListener(OnClickListener())
        val args: RedScreenArgs by navArgs()
        val text = args.text
        binding?.etRed?.setText(text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private inner class OnClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            val navController = view?.findNavController()
            val text = binding?.etRed?.text.toString()
            setFragmentResult("result", bundleOf("text" to text))
            navController?.popBackStack()
        }
    }
}
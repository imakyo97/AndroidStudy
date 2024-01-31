package com.example.navigationsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.red_screen)
        binding?.btPopBackStack?.setOnClickListener(OnClickListener())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private inner class OnClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            parentFragmentManager.popBackStack()
        }
    }
}
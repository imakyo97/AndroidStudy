package com.example.navigationsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.navigationsample.databinding.FragmentOrangeScreenBinding

class OrangeScreen : Fragment() {
    private var binding: FragmentOrangeScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrangeScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btNext?.setOnClickListener(OnClickListener())
        setFragmentResultListener("result") { _, bundle ->
            val text = bundle.getString("text")
            binding?.etOrange?.setText(text)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private inner class OnClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val navController = view?.findNavController()
            val text = binding?.etOrange?.text.toString()
            val action = OrangeScreenDirections.actionOrangeScreen2ToRedScreen(text)
            navController?.navigate(action)
        }
    }
}
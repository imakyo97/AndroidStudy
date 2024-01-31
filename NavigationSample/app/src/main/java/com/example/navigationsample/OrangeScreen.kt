package com.example.navigationsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private inner class OnClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            val navController = view?.findNavController()
            navController?.navigate(R.id.redScreen)
        }
    }
}
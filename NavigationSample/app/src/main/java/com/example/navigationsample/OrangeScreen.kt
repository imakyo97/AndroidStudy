package com.example.navigationsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.orange_screen)
        binding?.btReplace?.setOnClickListener(OnClickListener())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private inner class OnClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.addToBackStack("OrangeScreen")
            transaction.replace(R.id.container_view, RedScreen::class.java, null)
            transaction.commit()
        }
    }
}
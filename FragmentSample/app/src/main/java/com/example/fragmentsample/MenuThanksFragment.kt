package com.example.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class MenuThanksFragment : Fragment(R.layout.fragment_menu_thanks) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuName = arguments?.getString("menuName") ?: ""
        val menuPrice = arguments?.getString("menuPrice") ?: ""
        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice

        val btBackButton = view.findViewById<Button>(R.id.btThxBack)
        btBackButton.setOnClickListener(ButtonClickListener())
    }

    private inner class ButtonClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            activity?.let {
                val fragmentMainContainer = it.findViewById<View>(R.id.fragmentMainContainer)
                if(fragmentMainContainer != null) {
                    parentFragmentManager.popBackStack()
                }
                else {
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.setReorderingAllowed(true)
                    transaction.remove(this@MenuThanksFragment)
                    transaction.commit()
                }
            }
        }
    }
}
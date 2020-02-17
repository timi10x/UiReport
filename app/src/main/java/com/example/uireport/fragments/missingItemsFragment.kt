package com.example.uireport.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.uireport.R
import kotlinx.android.synthetic.main.fragment_missing_items.*
import kotlinx.android.synthetic.main.fragment_missing_items.view.*

/**
 * A simple [Fragment] subclass.
 */
class missingItemsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_missing_items, container, false)

        view.submit_items.setOnClickListener {
            crime_edit_text.text?.clear()
            description_edit_text.text?.clear()
            lost_edit_text.text?.clear()
        }
        return view
    }
}

package com.example.uireport.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.uireport.R
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_report_crime.*
import kotlinx.android.synthetic.main.fragment_report_crime.view.*

/**
 * A simple [Fragment] subclass.
 */
class reportCrimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_report_crime, container, false)

        view.submit_crime.setOnClickListener {
            crime_edit_text.text?.clear()
            description_edit_text.text?.clear()
            location_edit_text.text?.clear()
        }
        return view
    }
}

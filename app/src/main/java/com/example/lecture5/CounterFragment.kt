package com.example.lecture5

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class CounterFragment : Fragment(R.layout.fragment_counter) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val countTextView = view.findViewById<TextView>(R.id.count_text_view)
        val addButton = view.findViewById<Button>(R.id.go_to_counter_button)

        var currentValue = arguments?.getInt(CURRENT_VALUE) ?: 0
        countTextView.text = currentValue.toString()
        addButton.setOnClickListener {
            currentValue++
            countTextView.text = currentValue.toString()
        }

    }

    companion object {
        private const val CURRENT_VALUE = "CURRENT_VALUE"

        fun newInstance(currentValue: Int): CounterFragment {
            return CounterFragment().apply {
                arguments = bundleOf(CURRENT_VALUE to currentValue)
            }
        }
    }
}
package org.d3if3001.movies.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.d3if3001.movies.MainActivity
import org.d3if3001.movies.R
import org.d3if3001.movies.databinding.FragmentFirstBinding


class FirstPage: Fragment() {
    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnStart.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_firstPage_to_hitungDiskonFragment
            )
        }
    }
}
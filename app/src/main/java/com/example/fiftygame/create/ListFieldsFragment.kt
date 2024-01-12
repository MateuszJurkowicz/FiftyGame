package com.example.fiftygame.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fiftygame.R
import com.example.fiftygame.databinding.FragmentListFieldsBinding


class ListFieldsFragment : Fragment() {
    private var _binding: FragmentListFieldsBinding? = null
    private val binding get() =_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFieldsFragment_to_addFieldFragment)
        }

        return view
    }

}
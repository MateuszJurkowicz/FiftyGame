package com.example.fiftygame.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiftygame.R
import com.example.fiftygame.data.FieldViewModel
import com.example.fiftygame.databinding.FragmentListFieldsBinding


class ListFieldsFragment : Fragment() {
    private lateinit var mFieldViewModel: FieldViewModel
    private var _binding: FragmentListFieldsBinding? = null
    private val binding get() =_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mFieldViewModel = ViewModelProvider(this).get(FieldViewModel::class.java)
        mFieldViewModel.readAllData.observe(viewLifecycleOwner, Observer { field ->
            adapter.setData(field)
        })

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFieldsFragment_to_addFieldFragment)
        }

        return view
    }

}
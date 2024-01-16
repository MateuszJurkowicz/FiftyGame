package com.example.fiftygame.create

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiftygame.R
import com.example.fiftygame.data.FieldViewModel
import com.example.fiftygame.databinding.FragmentListFieldsBinding


class ListFieldsFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var mFieldViewModel: FieldViewModel
    private val adapter: ListAdapter by lazy {ListAdapter()}
    private var _binding: FragmentListFieldsBinding? = null
    private val binding get() =_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root

        setHasOptionsMenu(true)

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_game_menu, menu)
        val search = menu?.findItem(R.id.search_item)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_item) {
            deleteAllFields()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllFields() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Potwierdź") {_, _ ->
            mFieldViewModel.deleteAllFields()
            Toast.makeText(requireContext(), "Pomyślnie usunięto!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cofnij") {_, _ -> }
        builder.setTitle("Usunąć wszystko?")
        builder.setMessage("Czy jesteś pewny, że chcesz usunąć wszystkie pola?")
        builder.create().show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        mFieldViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                adapter.setData(it)
            }
        })
    }
}
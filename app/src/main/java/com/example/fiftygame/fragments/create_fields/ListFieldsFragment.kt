package com.example.fiftygame.fragments.create_fields

import  android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.FieldStorage
import com.example.fiftygame.data.viewmodels.FieldViewModel
import com.example.fiftygame.databinding.FragmentListFieldsBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class ListFieldsFragment : Fragment(), SearchView.OnQueryTextListener, MenuProvider {
    private val args by navArgs<ListFieldsFragmentArgs>()
    private lateinit var mFieldViewModel: FieldViewModel
        private lateinit var adapter: ListFieldsAdapter
        private var _binding: FragmentListFieldsBinding? = null
        private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root

        activity?.addMenuProvider(this, viewLifecycleOwner)
        mFieldViewModel = ViewModelProvider(this)[FieldViewModel::class.java]

        val query = mFieldViewModel.readAllFields(args.currentGame.gameId)

        setupRecyclerView(query)


        binding.fieldFloatingActionButton.setOnClickListener {
            val action = ListFieldsFragmentDirections.actionListFieldsFragmentToAddFieldFragment(
                args.currentGame
            )
            findNavController().navigate(action)
        }
        binding.fieldFloatingActionButton.setOnLongClickListener {
            insertExampleDataToDatabase(args.currentGame.gameId.toString())
            true
        }


        return view
    }

    private fun setupRecyclerView(query: Query) {
        val options = FirestoreRecyclerOptions.Builder<Field>().setQuery(query, Field::class.java).build()

        adapter = ListFieldsAdapter(args.currentGame, options)
        val recyclerView = binding.fieldsRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


    private fun deleteAllFields() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Potwierdź") { _, _ ->
            mFieldViewModel.deleteFieldsInGame(args.currentGame.gameId.toString())
            Toast.makeText(requireContext(), "Pomyślnie usunięto!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cofnij") { _, _ -> }
        builder.setTitle("Usunąć wszystko?")
        builder.setMessage("Czy jesteś pewny, że chcesz usunąć wszystkie pola?")
        builder.create().show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
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
        val queryForRecyclerView = mFieldViewModel.searchDatabase(args.currentGame.gameId, searchQuery)
        setupRecyclerView(queryForRecyclerView)
        /*.observe(this) { list ->
            list.let {
                adapter.setDataForSearch(it)
            }
        }*/
    }

    private fun insertExampleDataToDatabase(gameId: String) {
        val fields = FieldStorage.getExampleFields(gameId)
        fields.forEach {
            mFieldViewModel.addField(it, gameId)
        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.create_fields_menu, menu)
        val search = menu.findItem(R.id.search_item)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.delete_item) {
            deleteAllFields()
            return true
        }
        if (menuItem.itemId == R.id.profile_item) {
            findNavController().navigate(R.id.action_listFieldsFragment_to_userProfileFragment)
            return true
        }
        return false

    }

}
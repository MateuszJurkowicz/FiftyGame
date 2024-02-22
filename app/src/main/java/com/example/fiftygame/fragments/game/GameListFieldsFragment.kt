package com.example.fiftygame.fragments.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.viewmodels.FieldViewModel
import com.example.fiftygame.data.viewmodels.PlayerViewModel
import com.example.fiftygame.databinding.FragmentGameListFieldsBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

class GameListFieldsFragment : Fragment() {
    private val args by navArgs<GameListFieldsFragmentArgs>()
    private var _binding: FragmentGameListFieldsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GameListFieldsAdapter
    private lateinit var mFieldViewModel: FieldViewModel
    private lateinit var mPlayerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root

        mFieldViewModel = ViewModelProvider(this)[FieldViewModel::class.java]
        mPlayerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]

        val query = mFieldViewModel.readAllFields(args.currentGame.gameId)

        setupRecyclerView(query)



        mPlayerViewModel.getLevel.observe(viewLifecycleOwner) { playerLevel ->
            adapter.setLevel(playerLevel - 1)
            Log.d("oncreateview", "${mPlayerViewModel.getLevel.value}")
            Log.d("oncreateview2", playerLevel.toString())


        }

        return view
    }

    private fun setupRecyclerView(query: Query) {
        val options = FirestoreRecyclerOptions.Builder<Field>().setQuery(query, Field::class.java).build()

        adapter = GameListFieldsAdapter(args.currentGame, options)
        val recyclerView = binding.gameRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}
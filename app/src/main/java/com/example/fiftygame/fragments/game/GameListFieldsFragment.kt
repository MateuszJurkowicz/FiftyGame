package com.example.fiftygame.fragments.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiftygame.R
import com.example.fiftygame.data.viewmodels.FieldViewModel
import com.example.fiftygame.databinding.FragmentGameListFieldsBinding

class GameListFieldsFragment : Fragment() {
    private val args by navArgs<GameListFieldsFragmentArgs>()
    private var _binding: FragmentGameListFieldsBinding? = null
    private val binding get() = _binding!!
    private val adapter: GameListFieldsAdapter by lazy { GameListFieldsAdapter() }
    private lateinit var mFieldViewModel: FieldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.gameRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = adapter

        mFieldViewModel = ViewModelProvider(this)[FieldViewModel::class.java]
        mFieldViewModel.readGameWithFields(args.currentGame.gameId)
            .observe(viewLifecycleOwner, Observer { gameWithFields ->
                adapter.setData(gameWithFields)
            })

        return view
    }

}
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
import com.example.fiftygame.data.viewmodels.PlayerViewModel
import com.example.fiftygame.databinding.FragmentGameListFieldsBinding

class GameListFieldsFragment : Fragment() {
    private val args by navArgs<GameListFieldsFragmentArgs>()
    private var _binding: FragmentGameListFieldsBinding? = null
    private val binding get() = _binding!!
    private val adapter: GameListFieldsAdapter by lazy { GameListFieldsAdapter(args.currentGame) }
    private lateinit var mFieldViewModel: FieldViewModel
    private lateinit var mPlayerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.gameRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = adapter
        mPlayerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]


        mPlayerViewModel.getLevel.observe(viewLifecycleOwner, Observer { playerLevel ->
            adapter.setLevel(playerLevel-1)
            Log.d("oncreateview", "${mPlayerViewModel.getLevel.value}")
            Log.d("oncreateview2", playerLevel.toString())


        })

        mFieldViewModel = ViewModelProvider(this)[FieldViewModel::class.java]
        /*args.currentGame.let {
            mFieldViewModel.readGameWithFields(it.gameId)
                .observe(viewLifecycleOwner, Observer { gameWithFields ->
                    adapter.setData(gameWithFields)
                })
        }*/

        return view
    }




}
package com.example.fiftygame.fragments.create_games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.viewmodels.GameViewModel
import com.example.fiftygame.databinding.FragmentListGamesBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ListGamesFragment : Fragment(), MenuProvider {
    private lateinit var mGameViewModel: GameViewModel
    private lateinit var adapter: ListGamesAdapter
    private var _binding: FragmentListGamesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListGamesBinding.inflate(inflater, container, false)
        val view = binding.root

        activity?.addMenuProvider(this, viewLifecycleOwner)
        mGameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        setupRecyclerView()


        binding.gameFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listGamesFragment_to_addGameFragment)
        }

        return view
    }

    private fun setupRecyclerView() {
        val query = mGameViewModel.readAllGames

        val options = FirestoreRecyclerOptions.Builder<Game>().setQuery(query, Game::class.java).build()

        adapter = ListGamesAdapter(this, options)
        val recyclerView = binding.gamesRecyclerView
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


    fun editGame(currentGame: Game) {
        val action = ListGamesFragmentDirections.actionListGamesFragmentToListFieldsFragment(currentGame)
        findNavController().navigate(action)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.create_games_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.profile_item) {
            findNavController().navigate(R.id.action_listGamesFragment_to_userProfileFragment)
            return true
        }
        return false
    }
}
package com.example.fiftygame.fragments.create_games

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiftygame.R
import com.example.fiftygame.activities.CreateFieldsActivity
import com.example.fiftygame.activities.CreateGamesActivity
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.viewmodels.FieldViewModel
import com.example.fiftygame.data.viewmodels.GameViewModel
import com.example.fiftygame.databinding.FragmentListGamesBinding
import com.example.fiftygame.fragments.create_fields.ListFieldsAdapter
import com.example.fiftygame.fragments.create_fields.ListFieldsFragmentDirections

class ListGamesFragment : Fragment() {
    private lateinit var mGameViewModel: GameViewModel
    private lateinit var mFieldViewModel: FieldViewModel
    private val adapter: ListGamesAdapter by lazy { ListGamesAdapter(this) }
    private var _binding: FragmentListGamesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListGamesBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.gamesRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mFieldViewModel = ViewModelProvider(this)[FieldViewModel::class.java]
        mGameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        mGameViewModel.readAllGames.observe(viewLifecycleOwner, Observer { game ->
            adapter.setData(game)
        })

        binding.gameFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listGamesFragment_to_addGameFragment)
        }

        return view
    }


    fun deleteGame(currentGame: Game) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Potwierdź") { _, _ ->
            mFieldViewModel.deleteFieldsInGame(currentGame.gameId)
            mGameViewModel.deleteGame(currentGame)
            Toast.makeText(requireContext(), "Pomyślnie usunięto!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cofnij") { _, _ -> }
        builder.setTitle("Usunąć grę o numerze id ${currentGame.gameId}?")
        builder.setMessage("Utracisz wszystkie pola, które są zawarte w tej grze")
        builder.create().show()
    }

    fun editGame(game: Game) {
        val action = ListGamesFragmentDirections.actionListGamesFragmentToCreateGameNav(game)
        findNavController().navigate(action)
    }
}
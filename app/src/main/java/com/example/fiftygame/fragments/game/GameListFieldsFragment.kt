package com.example.fiftygame.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.viewmodels.FieldViewModel
import com.example.fiftygame.data.viewmodels.UserViewModel
import com.example.fiftygame.databinding.FragmentGameListFieldsBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameListFieldsFragment : Fragment(), MenuProvider {
    private val args by navArgs<GameListFieldsFragmentArgs>()
    private var _binding: FragmentGameListFieldsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    private lateinit var adapter: GameListFieldsAdapter
    private lateinit var mFieldViewModel: FieldViewModel
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameListFieldsBinding.inflate(inflater, container, false)
        val view = binding.root
        mAuth = Firebase.auth

        //activity?.addMenuProvider(this, viewLifecycleOwner)
        mFieldViewModel = ViewModelProvider(this)[FieldViewModel::class.java]
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val query = mFieldViewModel.readAllFields(args.currentGame.gameId)

        setupRecyclerView(query)

        return view
    }

    override fun onPrepareMenu(menu: Menu) {/*mPlayerViewModel.getLevel.observe(viewLifecycleOwner) { playerLevel ->
            menu.findItem(R.id.game_level).setTitle(playerLevel.toString() + "/" + binding.gameRecyclerView.size)
        }*/
        super.onPrepareMenu(menu)
    }

    private fun setupRecyclerView(query: Query) {
        val options = FirestoreRecyclerOptions.Builder<Field>().setQuery(query, Field::class.java).build()

        adapter = GameListFieldsAdapter(args.currentGame, options)
        val recyclerView = binding.gameRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        lifecycleScope.launch(Dispatchers.IO) {

            val player = mUserViewModel.readPlayerInGame(args.currentGame)
            if (player != null) {
                adapter.setLevel(player.level - 1)
            }
        }


    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.game_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }


}
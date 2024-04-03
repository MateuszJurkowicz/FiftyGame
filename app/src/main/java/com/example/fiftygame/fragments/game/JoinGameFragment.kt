package com.example.fiftygame.fragments.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fiftygame.data.viewmodels.GameViewModel
import com.example.fiftygame.data.viewmodels.UserViewModel
import com.example.fiftygame.databinding.FragmentJoinGameBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JoinGameFragment : Fragment() {
    private var _binding: FragmentJoinGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGameViewModel: GameViewModel
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentJoinGameBinding.inflate(inflater, container, false)
        val view = binding.root
        mAuth = Firebase.auth
        val user = mAuth.currentUser

        mGameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.nameEditText.setText(user?.displayName)



        binding.joinGameButton.setOnClickListener {
            val pin = binding.pinEditText.text.toString()
            val playerName = binding.nameEditText.text.toString()
            lifecycleScope.launch(Dispatchers.Main) {
                if (checkAllFields(pin, playerName)) {
                    val player = mUserViewModel.readPlayer(user?.email)
                    val currentGame = mGameViewModel.readGameWithPin(pin.toInt())
                    val profileUpdates = userProfileChangeRequest {
                        displayName = playerName
                    }
                    mGameViewModel.addPlayer(currentGame, player)
                    user?.updateProfile(profileUpdates)
                    Toast.makeText(requireContext(), "Znaleziono grę", Toast.LENGTH_SHORT).show()
                    val action = JoinGameFragmentDirections.actionJoinGameFragmentToGameListFieldsFragment(
                        currentGame
                    )
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Nie znaleziono gry", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

    private suspend fun checkAllFields(pin: String, playerName: String): Boolean {
        var gameExists = false
        if (pin.length != 6 || playerName.isEmpty()) {
            return false
        }

        try {
            val game = mGameViewModel.readGameWithPin(pin.toInt())
            gameExists = (game != null)
        } catch (e: Exception) {
            // Obsłuż błąd odczytu gry
            Log.e("JoinGame", "Error reading game: ${e.message}")
        }
        return gameExists
    }

    override fun onDestroy() {
        //mPlayerViewModel.setLevel(1)
        Log.d("destroyview", "DESTROY")
        super.onDestroy()
    }

}
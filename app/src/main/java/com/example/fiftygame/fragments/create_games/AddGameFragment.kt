package com.example.fiftygame.fragments.create_games

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.models.Player
import com.example.fiftygame.data.viewmodels.GameViewModel
import com.example.fiftygame.databinding.FragmentAddGameBinding
import com.google.firebase.auth.FirebaseAuth

class AddGameFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGameViewModel: GameViewModel
    private var _binding: FragmentAddGameBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddGameBinding.inflate(inflater, container, false)
        val view = binding.root

        mAuth = FirebaseAuth.getInstance()

        mGameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        binding.addGameButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val pin: Int = (100000..999999).random()
        val description = binding.descriptionEditText.text.toString()
        if (inputCheck(pin.toString(), description)) {
            val game = Game("0", pin, description, mAuth.currentUser?.email)
            mGameViewModel.addGame(game)
            Toast.makeText(requireContext(), "Pomyślnie dodano!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addGameFragment_to_listGamesFragment)
        } else {
            Toast.makeText(requireContext(), "Wykryto błąd!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(pin: String, description: String): Boolean {
        return !(TextUtils.isEmpty(pin) && TextUtils.isEmpty(description))
    }


}
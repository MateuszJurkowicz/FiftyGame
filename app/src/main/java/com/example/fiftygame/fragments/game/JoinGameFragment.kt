package com.example.fiftygame.fragments.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fiftygame.R
import com.example.fiftygame.data.viewmodels.GameViewModel
import com.example.fiftygame.data.viewmodels.PlayerViewModel
import com.example.fiftygame.databinding.FragmentJoinGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JoinGameFragment : Fragment() {
    private var _binding: FragmentJoinGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var mGameViewModel: GameViewModel
    private lateinit var mPlayerViewModel: PlayerViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJoinGameBinding.inflate(inflater, container, false)
        val view = binding.root

        mGameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        mPlayerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]


        binding.joinGameButton.setOnClickListener {
            val pin = binding.pinEditText.text.toString()
            val playerName = binding.nameEditText.text.toString()

            if (playerName.isNotEmpty() && pin.isNotEmpty() && pin.length == 6) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val currentGame = mGameViewModel.readGameWithPin(pin.toInt())
                    mPlayerViewModel.setName(playerName)
                    mPlayerViewModel.setLevel(1)

                    if (currentGame.pin == pin.toInt()) {
                        launch(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Znaleziono grę", Toast.LENGTH_LONG)
                                .show()
                            val action =
                                JoinGameFragmentDirections.actionJoinGameFragmentToGameListFieldsFragment(
                                    currentGame
                                )
                            findNavController().navigate(action)
                        }
                    } else {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(), "Nie znaleziono gry", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Podaj nazwę gracza", Toast.LENGTH_LONG).show()
            }
        }


        return view
    }
    override fun onDestroy() {
        mPlayerViewModel.setLevel(1)
        Log.d("destroyview", "DESTROY")
        super.onDestroy()
    }

}
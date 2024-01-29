package com.example.fiftygame.fragments.game

import android.os.Bundle
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
import com.example.fiftygame.databinding.FragmentJoinGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JoinGameFragment : Fragment() {
    private var _binding: FragmentJoinGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var mGameViewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJoinGameBinding.inflate(inflater, container, false)
        val view = binding.root

        mGameViewModel = ViewModelProvider(this)[GameViewModel::class.java]


        binding.joinGameButton.setOnClickListener {
            val pin = binding.pinEditText.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                val game = mGameViewModel.readGameWithPin(pin.toInt())
                if (game.pin == pin.toInt()) {
                    launch(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Znaleziono grę", Toast.LENGTH_LONG)
                            .show()
                        val action = JoinGameFragmentDirections.actionJoinGameFragmentToGameListFieldsFragment(game)
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
        }

        return view
    }

}
package com.example.fiftygame.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fiftygame.data.viewmodels.UserViewModel
import com.example.fiftygame.databinding.FragmentGameFieldBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameFieldFragment : Fragment() {
    private val args by navArgs<GameFieldFragmentArgs>()
    private var _binding: FragmentGameFieldBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameFieldBinding.inflate(inflater, container, false)
        val view = binding.root
        mAuth = Firebase.auth

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.fieldNumber.text = args.currentItem.number.toString()
        binding.question.text = args.currentItem.question

        binding.entry.setEndIconOnClickListener {
            if (binding.entryEdit.text.toString() == args.currentItem.entry) {
                binding.question.visibility = View.VISIBLE
                binding.playerAnswer.visibility = View.VISIBLE

            }

        }
        binding.playerAnswer.setEndIconOnClickListener {
            if (binding.playerAnswerEdit.text.toString().trim() == args.currentItem.correctAnswer) {
                binding.submitAnswerButton.visibility = View.VISIBLE
            }
        }
        binding.submitAnswerButton.setOnClickListener {
            showResultDialog(getRandomNumber())
        }

        return view
    }

    private fun getRandomNumber(): Int {
        return (1..6).random()
    }

    private fun showResultDialog(result: Int) {
        MaterialAlertDialogBuilder(requireContext()).setTitle("Wynik rzutu kostką").setMessage("Wylosowano liczbę: $result")
            .setPositiveButton("OK") { dialog, _ ->
                lifecycleScope.launch(Dispatchers.Main) {
                    val currentLevel = mUserViewModel.readPlayerInGame(args.currentGame)?.level
                    if (currentLevel != null) {
                        mUserViewModel.setLevel(mAuth.currentUser?.uid, args.currentGame, currentLevel + result)
                    }
                    val action = GameFieldFragmentDirections.actionGameFieldFragmentToGameListFieldsFragment(
                        args.currentGame
                    )
                    findNavController().navigate(action)
                }

                dialog.dismiss()
            }.show()
        /*MaterialAlertDialogBuilder(requireContext()).setTitle("Wynik rzutu kostką").setMessage("Wylosowano liczbę: $result")
            .setPositiveButton("OK") { dialog, _ ->
                mPlayerViewModel.getLevel.observe(viewLifecycleOwner) { pLevel ->
                    mPlayerViewModel.setLevel(pLevel + result)
                    Log.d("GameLevel", "Nowy poziom gry: $pLevel")
                    val action = GameFieldFragmentDirections.actionGameFieldFragmentToGameListFieldsFragment(
                        args.currentGame
                    )
                    findNavController().navigate(action)
                }
                dialog.dismiss()
            }.show()*/
    }


}
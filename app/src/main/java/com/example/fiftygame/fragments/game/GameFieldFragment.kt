package com.example.fiftygame.fragments.game

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fiftygame.R
import com.example.fiftygame.data.viewmodels.PlayerViewModel
import com.example.fiftygame.databinding.FragmentGameFieldBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFieldFragment : Fragment() {
    private val args by navArgs<GameFieldFragmentArgs>()
    private var _binding: FragmentGameFieldBinding? = null
    private val binding get() = _binding!!
    private lateinit var mPlayerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameFieldBinding.inflate(inflater, container, false)
        val view = binding.root

        mPlayerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]

        binding.fieldNumber.text = args.currentItem.number.toString()
        binding.question.text = args.currentItem.question

        Log.d("Game field", args.currentItem.toString())
        Log.d("Game field", binding.entry.toString())

        binding.entry.setEndIconOnClickListener {
            if (binding.entryEdit.text.toString() == args.currentItem.entry) {
                binding.question.visibility = View.VISIBLE
                binding.playerAnswer.visibility = View.VISIBLE

            }

        }
        binding.playerAnswer.setEndIconOnClickListener {
            if (binding.playerAnswerEdit.text.toString() == args.currentItem.correctAnswer) {
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
        MaterialAlertDialogBuilder(requireContext()).setTitle("Wynik rzutu kostką")
            .setMessage("Wylosowano liczbę: $result").setPositiveButton("OK") { dialog, _ ->
                mPlayerViewModel.getLevel.observe(viewLifecycleOwner) { pLevel ->
                    mPlayerViewModel.setLevel(pLevel + result)
                    Log.d("GameLevel", "Nowy poziom gry: $pLevel")
                    val action = GameFieldFragmentDirections.actionGameFieldFragmentToGameListFieldsFragment(args.currentGame)
                    findNavController().navigate(action)

                }

                Log.d("game dialog1", result.toString())
                Log.d("game dialog3", mPlayerViewModel.getLevel.value.toString())
                dialog.dismiss()
            }.show()
    }




}
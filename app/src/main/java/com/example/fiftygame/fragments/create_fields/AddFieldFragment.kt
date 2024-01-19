package com.example.fiftygame.fragments.create_fields

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.viewmodels.FieldViewModel
import com.example.fiftygame.databinding.FragmentAddFieldBinding


class AddFieldFragment : Fragment() {
    private val args by navArgs<AddFieldFragmentArgs>()
    private lateinit var mFieldViewModel: FieldViewModel
    private var _binding: FragmentAddFieldBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddFieldBinding.inflate(inflater, container, false)
        val view = binding.root

        mFieldViewModel = ViewModelProvider(this).get(FieldViewModel::class.java)


        binding.addQuestionButton.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {

        val number = binding.numberEditText.text.toString()
        val entry = binding.entryEditText.text.toString()
        val question = binding.questionEditText.text.toString()
        val answer = binding.answerEditText.text.toString()
        val gameId = args.currentGameId
        if (inputCheck(number, entry, question, answer)) {
            val field = Field(0, number.toInt(), entry, question, answer, gameId)
            mFieldViewModel.addField(field)
            Toast.makeText(requireContext(), "Pomyślnie dodano!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFieldFragment_to_listFieldsFragment)
        } else {
            Toast.makeText(requireContext(), "Pola są puste!", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(number: String, entry: String, question: String, answer: String): Boolean {
        return !(TextUtils.isEmpty(number) && TextUtils.isEmpty(question) && TextUtils.isEmpty(answer) && TextUtils.isEmpty(entry))
    }

}
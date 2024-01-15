package com.example.fiftygame.create

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
import com.example.fiftygame.data.Field
import com.example.fiftygame.data.FieldViewModel
import com.example.fiftygame.databinding.FragmentUpdateFieldBinding

class UpdateFieldFragment : Fragment() {
    private val args by navArgs<UpdateFieldFragmentArgs>()
    private lateinit var mFieldViewModel: FieldViewModel
    private var _binding: FragmentUpdateFieldBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateFieldBinding.inflate(inflater, container, false)
        val view = binding.root

        mFieldViewModel = ViewModelProvider(this).get(FieldViewModel::class.java)

        binding.updateEntryEditText.setText(args.currentField.entry)
        binding.updateQuestionEditText.setText(args.currentField.question)
        binding.updateAnswerEditText.setText(args.currentField.correctAnswer)

        binding.updateQuestionButton.setOnClickListener {
            updateItem()
        }


        return view

    }

    private fun updateItem() {
        val entry = binding.updateEntryEditText.text.toString()
        val question = binding.updateQuestionEditText.text.toString()
        val answer = binding.updateAnswerEditText.text.toString()
        if (inputCheck(entry, question, answer)) {
            val updatedField = Field(args.currentField.id, entry, question, answer)
            mFieldViewModel.updateField(updatedField)
            Toast.makeText(requireContext(), "Pomyślnie zaktualizowano!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFieldFragment_to_listFieldsFragment)
        } else {
            Toast.makeText(requireContext(), "Nie udało się zaktualizować!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(entry: String, question: String, answer: String): Boolean {
        return !(TextUtils.isEmpty(question) && TextUtils.isEmpty(answer) && TextUtils.isEmpty(entry))
    }

}
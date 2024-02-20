package com.example.fiftygame.fragments.create_fields

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.viewmodels.FieldViewModel
import com.example.fiftygame.databinding.FragmentUpdateFieldBinding

class UpdateFieldFragment : Fragment() {
    private val args by navArgs<UpdateFieldFragmentArgs>()
    private lateinit var mFieldViewModel: FieldViewModel
    private var _binding: FragmentUpdateFieldBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateFieldBinding.inflate(inflater, container, false)
        val view = binding.root

        mFieldViewModel = ViewModelProvider(this)[FieldViewModel::class.java]

        binding.updateNumberEditText.setText(args.currentField.number.toString())
        binding.updateEntryEditText.setText(args.currentField.entry)
        binding.updateQuestionEditText.setText(args.currentField.question)
        binding.updateAnswerEditText.setText(args.currentField.correctAnswer)

        binding.updateQuestionButton.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.search_item).setVisible(false)
        menu.findItem(R.id.profile_item).setVisible(false)
        super.onPrepareOptionsMenu(menu)
    }

    private fun updateItem() {
        val number = binding.updateNumberEditText.text.toString()
        val entry = binding.updateEntryEditText.text.toString()
        val question = binding.updateQuestionEditText.text.toString()
        val answer = binding.updateAnswerEditText.text.toString()
        if (inputCheck(number, entry, question, answer)) {
            val updatedField = Field(args.currentField.fieldId, number.toInt(), entry, question, answer)
            mFieldViewModel.updateField(updatedField, args.currentGame.gameId)
            Toast.makeText(requireContext(), "Pomyślnie zaktualizowano!", Toast.LENGTH_SHORT).show()
            val action = UpdateFieldFragmentDirections.actionUpdateFieldFragmentToListFieldsFragment(args.currentGame)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Nie udało się zaktualizować!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(
        number: String, entry: String, question: String, answer: String
    ): Boolean {
        return !(TextUtils.isEmpty(number) && TextUtils.isEmpty(question) && TextUtils.isEmpty(
            answer
        ) && TextUtils.isEmpty(entry))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_fields_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_item) {
            deleteField()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteField() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Potwierdź") { _, _ ->
            mFieldViewModel.deleteField(args.currentField, args.currentGame.gameId)
            Toast.makeText(requireContext(), "Pomyślnie usunięto!", Toast.LENGTH_SHORT).show()
            val action = UpdateFieldFragmentDirections.actionUpdateFieldFragmentToListFieldsFragment(args.currentGame)
            findNavController().navigate(action)
        }
        builder.setNegativeButton("Cofnij") { _, _ -> }
        builder.setTitle("Usunąć ${args.currentField.number} pytanie?")
        builder.setMessage("Czy jesteś pewny, że chcesz usunąć ${args.currentField.number} pytanie?")
        builder.create().show()
    }
}
package com.example.fiftygame.create

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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.fiftygame.R
import com.example.fiftygame.data.Field
import com.example.fiftygame.data.FieldViewModel
import com.example.fiftygame.databinding.FragmentAddFieldBinding
import com.example.fiftygame.databinding.FragmentListFieldsBinding


class AddFieldFragment : Fragment() {
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
        val question = binding.questionEditText.text.toString()
        val answer = binding.answerEditText.text.toString()
        if (!(TextUtils.isEmpty(question) && TextUtils.isEmpty(answer))) {
            val field = Field(0, question, answer)
            mFieldViewModel.addField(field)
            Toast.makeText(requireContext(), "Pomyślnie dodano!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFieldFragment_to_listFieldsFragment)
        } else {
            Toast.makeText(requireContext(), "Pola są puste!", Toast.LENGTH_LONG).show()
        }
    }

}
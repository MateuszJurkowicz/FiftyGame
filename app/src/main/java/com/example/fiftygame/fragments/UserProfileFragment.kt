package com.example.fiftygame.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fiftygame.R
import com.example.fiftygame.activities.CreateFieldsActivity
import com.example.fiftygame.activities.MainActivity
import com.example.fiftygame.databinding.FragmentUserProfileBinding
import com.google.firebase.auth.FirebaseAuth

class UserProfileFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        binding.profileName.text = user?.displayName
        Glide.with(this).load(user?.photoUrl).into(binding.profileImage)

        binding.signOutButton.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("sign_out", true)
            startActivity(intent)


        }


        return view
    }


}
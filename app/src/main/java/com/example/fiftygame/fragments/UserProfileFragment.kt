package com.example.fiftygame.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fiftygame.R
import com.example.fiftygame.activities.accountManagment.SignInActivity
import com.example.fiftygame.data.viewmodels.UserViewModel
import com.example.fiftygame.databinding.FragmentUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class UserProfileFragment : Fragment(), AvatarsDialogFragment.AvatarSelectionListener {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser


        if (user?.displayName.isNullOrEmpty()) {
            binding.profileNameTextView.hint = "Nazwa użytkownika"
        } else {
            binding.profileNameEditText.setText(user?.displayName)
        }
        if (user?.photoUrl == null) {
            Glide.with(this).load(R.drawable.profile_avatar).into(binding.profileImage)
        } else {
            Glide.with(this).load(user.photoUrl).into(binding.profileImage)
        }

        binding.profileNameButton.setOnClickListener {
            val profileUpdates = userProfileChangeRequest {
                displayName = binding.profileNameEditText.text.toString().trim()
            }
            user?.updateProfile(profileUpdates)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Pomyślnie zaktualizowano nick", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Coś poszło nie tak", Toast.LENGTH_LONG).show()
                    Log.d("error updateProfile: ", it.exception.toString())
                }
            }
        }
        binding.profilePasswordButton.setOnClickListener {
            val newPassword = binding.profileNewPasswordEditText.text.toString().trim()
            if (checkPassword(newPassword)) {
                user?.updatePassword(newPassword)?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(requireContext(), "Pomyślnie zaktualizowano hasło", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Coś poszło nie tak", Toast.LENGTH_LONG).show()
                        Log.d("error updatePassword: ", it.exception.toString())
                    }
                }
            }
        }

        binding.editImage.setOnClickListener {
            val newFragment = AvatarsDialogFragment()
            newFragment.setAvatarSelectionListener(this)
            newFragment.show(childFragmentManager, "dialog")

        }

        binding.signOutButton.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(requireContext(), SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("sign_out", true)
            startActivity(intent)


        }


        return view
    }

    override fun onAvatarSelected(photoUrl: String) {
        val profileUpdates = userProfileChangeRequest {
            photoUri = photoUrl.toUri()
        }
        mAuth.currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener {
            if (it.isSuccessful) {
                Glide.with(this).load(mAuth.currentUser?.photoUrl).into(binding.profileImage)
                Toast.makeText(requireContext(), "Pomyślnie zaktualizowano zdjęcie", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Coś poszło nie tak", Toast.LENGTH_LONG).show()
                Log.d("error updateProfile: ", it.exception.toString())
            }
        }
    }

    private fun checkPassword(newPassword: String): Boolean {

        if (newPassword.isNotEmpty() && newPassword.length > 6) {
            binding.profileNewPasswordTextView.error = null
        } else {
            if (newPassword.isEmpty()) {
                binding.profileNewPasswordTextView.error = "This is required field"
            } else {
                binding.profileNewPasswordTextView.error = "Password should be at least 6 characters long"
            }
            return false
        }

        return true
    }

}
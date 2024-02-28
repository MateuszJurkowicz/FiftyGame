package com.example.fiftygame.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Avatar
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.databinding.FragmentAvatarDialogBinding
import com.example.fiftygame.databinding.FragmentListFieldsBinding
import com.example.fiftygame.fragments.create_fields.ListFieldsAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore


class AvatarsDialogFragment : DialogFragment() {
    private lateinit var adapter: AvatarsAdapter
    private var selectionListener: AvatarSelectionListener? = null


    interface AvatarSelectionListener {
        fun onAvatarSelected(photoUrl: String)
    }


    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_avatar_dialog, null)

        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.avatarsRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        val query = FirebaseFirestore.getInstance().collection("images")
        val options = FirestoreRecyclerOptions.Builder<Avatar>().setQuery(query, Avatar::class.java).build()
        adapter = AvatarsAdapter(options)
        recyclerView.adapter = adapter

        return AlertDialog.Builder(requireContext()).setView(dialogView).setPositiveButton("Wybierz") { _, _ ->
                val selectedAvatar = adapter.getSelectedAvatar()
                selectedAvatar.let {
                    selectionListener?.onAvatarSelected(selectedAvatar.imageUrl)
                }
            }.setNegativeButton("Anuluj") { _, _ ->
                dismiss()
            }.create()

    }

    fun setAvatarSelectionListener(listener: AvatarSelectionListener) {
        selectionListener = listener
    }


}
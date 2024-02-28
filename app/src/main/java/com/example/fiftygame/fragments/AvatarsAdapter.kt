package com.example.fiftygame.fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Avatar
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class AvatarsAdapter(options: FirestoreRecyclerOptions<Avatar>) :
    FirestoreRecyclerAdapter<Avatar, AvatarsAdapter.ViewHolder>(options) {
    private var selectedPosition = RecyclerView.NO_POSITION

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_avatar, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, currentItem: Avatar) {
        val cardLayout = holder.itemView.findViewById<LinearLayout>(R.id.avatar_simple_card)
        Glide.with(holder.itemView.context)
            .load(currentItem.imageUrl)
            .into(holder.itemView.findViewById(R.id.imageView))
        if (position == selectedPosition) {
            cardLayout.setBackgroundColor(Color.LTGRAY)
        } else {
            cardLayout.setBackgroundColor(Color.WHITE)
        }

        cardLayout.setOnClickListener {
            // Odznaczenie poprzednio zaznaczonego elementu
            notifyItemChanged(selectedPosition)

            // Zaznaczenie aktualnie klikniętego elementu
            selectedPosition = holder.bindingAdapterPosition

            // Powiadomienie adaptera o zmianie danych
            notifyItemChanged(selectedPosition)
        }
        /*val action = ListFieldsFragmentDirections.actionListFieldsFragmentToUpdateFieldFragment(
            currentItem, currentGame
        )
        holder.itemView.findNavController().navigate(action)*/
    }

    fun getSelectedAvatar(): Avatar {
        return getItem(selectedPosition)

    }


}
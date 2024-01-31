package com.example.fiftygame.fragments.game

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.relations.GameWithFields

class GameListFieldsAdapter(game: Game) : RecyclerView.Adapter<GameListFieldsAdapter.ViewHolder>() {
    private var fieldsList = emptyList<Field>()
    private var playerLevel: Int? = null
    private var clickablePosition: Int? = null
    private var currentGame = game


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_game_field, parent, false)
        )
    }

    override fun getItemCount(): Int = fieldsList.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentItem = fieldsList[position]

        holder.itemView.findViewById<TextView>(R.id.field_number).text =
            currentItem.number.toString()

        // Sprawdź i podświetl pole, jeśli playerLevel jest zdefiniowany i równy poziomowi pola
        if (playerLevel != null && playerLevel == position) {
            holder.itemView.findViewById<CardView>(R.id.field_CardView).setCardBackgroundColor(holder.itemView.context.getColor(R.color.access_color_card_view))
            clickablePosition = position // Ustaw klikalność tylko dla podświetlonego pola
        } else {
            // Jeśli nie, przywróć domyślny kolor tła
            holder.itemView.findViewById<CardView>(R.id.field_CardView).setCardBackgroundColor(holder.itemView.context.getColor(R.color.card_field_color))
        }

        holder.itemView.findViewById<TextView>(R.id.field_number).setOnClickListener {
            if (clickablePosition == position) {
                // Kliknięcie będzie obsługiwane tylko dla podświetlonego pola
                val action =
                    GameListFieldsFragmentDirections.actionGameListFieldsFragmentToGameFieldFragment(
                        currentItem, currentGame
                    )
                holder.itemView.findNavController().navigate(action)
            } else {
                // Pokaż powiadomienie "Pole niedostępne"
                Toast.makeText(holder.itemView.context, "Pole niedostępne", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun setData(gameWithFieldsList: List<GameWithFields>) {
        gameWithFieldsList.forEach { gameWithFields ->
            fieldsList = gameWithFields.fields
            // Tutaj możesz wykonać operacje na fields
        }
        notifyDataSetChanged()
    }

    fun setLevel(level: Int?) {
        playerLevel = level
        clickablePosition = null
        notifyDataSetChanged()
    }
}



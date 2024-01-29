package com.example.fiftygame.fragments.game

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.relations.GameWithFields

class GameListFieldsAdapter : RecyclerView.Adapter<GameListFieldsAdapter.ViewHolder>() {
    private var fieldsList = emptyList<Field>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_game_field, parent, false)
        )
    }

    override fun getItemCount(): Int = fieldsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = fieldsList[position]
        Log.d("gamefragment", currentItem.toString())

        holder.itemView.findViewById<TextView>(R.id.field_number).text =
            currentItem.number.toString()
        holder.itemView.findViewById<TextView>(R.id.field_number).setOnClickListener {
            val action =
                GameListFieldsFragmentDirections.actionGameListFieldsFragmentToGameFieldFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)

        }

    }

    fun setData(gameWithFieldsList: List<GameWithFields>) {
        gameWithFieldsList.forEach { gameWithFields ->
            fieldsList = gameWithFields.fields
            // Tutaj możesz wykonać operacje na fields
        }
        notifyDataSetChanged()
    }
}



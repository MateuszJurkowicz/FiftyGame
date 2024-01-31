package com.example.fiftygame.fragments.create_games

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fiftygame.R
import com.example.fiftygame.activities.CreateFieldsActivity
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.fragments.create_fields.ListFieldsFragmentDirections

class ListGamesAdapter(private val fragment: ListGamesFragment): RecyclerView.Adapter<ListGamesAdapter.ViewHolder>() {
    private var gamesList = emptyList<Game>()
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.create_game_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = gamesList[position]
        holder.itemView.findViewById<TextView>(R.id.pin_textView).text = currentItem.pin.toString()
        holder.itemView.findViewById<TextView>(R.id.description_textView).text = currentItem.description
        holder.itemView.findViewById<ConstraintLayout>(R.id.game_row).setOnClickListener{
            fragment.editGame(currentItem)
        }
        holder.itemView.findViewById<ConstraintLayout>(R.id.game_row).setOnLongClickListener{
            fragment.deleteGame(currentItem)
            true
        }
    }

    override fun getItemCount(): Int {
       return gamesList.size
    }

    fun setData(game: List<Game>) {
        this.gamesList = game
        notifyDataSetChanged()

    }
}
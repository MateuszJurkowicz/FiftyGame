package com.example.fiftygame.fragments.create_games

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Game
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ListGamesAdapter(private val fragment: ListGamesFragment, options: FirestoreRecyclerOptions<Game>) :
    FirestoreRecyclerAdapter<Game, ListGamesAdapter.ViewHolder>(options) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.create_game_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, currentItem: Game) {
        holder.itemView.findViewById<TextView>(R.id.pin_textView).text = currentItem.pin.toString()
        holder.itemView.findViewById<TextView>(R.id.description_textView).text = currentItem.description

        holder.itemView.findViewById<ConstraintLayout>(R.id.game_row).setOnClickListener {
            fragment.editGame(currentItem)
        }
        holder.itemView.findViewById<ConstraintLayout>(R.id.game_row).setOnLongClickListener {
            deleteGame(position, currentItem)
            true
        }
    }

    private fun deleteGame(position: Int, currentItem: Game) {
        val builder = AlertDialog.Builder(fragment.requireContext())
        builder.setPositiveButton("Potwierdź") { _, _ ->
            snapshots.getSnapshot(position).reference.delete()
            Toast.makeText(fragment.requireContext(), "Pomyślnie usunięto!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cofnij") { _, _ -> }
        builder.setTitle("Usunąć grę o numerze pin ${currentItem.pin}?")
        builder.setMessage("Utracisz wszystkie pola, które są zawarte w tej grze")
        builder.create().show()
    }

}
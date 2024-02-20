package com.example.fiftygame.fragments.create_fields

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.relations.GameWithFields
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ListFieldsAdapter(game: Game, options: FirestoreRecyclerOptions<Field>) :
    FirestoreRecyclerAdapter<Field, ListFieldsAdapter.ViewHolder>(options) {
    private val currentGame = game

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.create_field_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, currentItem: Field) {
        holder.itemView.findViewById<TextView>(R.id.number_textView).text = currentItem.number.toString()
        holder.itemView.findViewById<TextView>(R.id.entry_textView).text = currentItem.entry
        holder.itemView.findViewById<TextView>(R.id.question_textView).text = currentItem.question
        holder.itemView.findViewById<TextView>(R.id.correctAnswer_textView).text = currentItem.correctAnswer
        holder.itemView.findViewById<ConstraintLayout>(R.id.field_row).setOnClickListener {
            val action = ListFieldsFragmentDirections.actionListFieldsFragmentToUpdateFieldFragment(
                currentItem, currentGame
            )
            holder.itemView.findNavController().navigate(action)
        }
    }

    /*override fun getItemCount(): Int {
        return fieldsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = fieldsList[position]
        holder.itemView.findViewById<TextView>(R.id.number_textView).text = currentItem.number.toString()
        holder.itemView.findViewById<TextView>(R.id.entry_textView).text = currentItem.entry
        holder.itemView.findViewById<TextView>(R.id.question_textView).text = currentItem.question
        holder.itemView.findViewById<TextView>(R.id.correctAnswer_textView).text = currentItem.correctAnswer
        holder.itemView.findViewById<ConstraintLayout>(R.id.field_row).setOnClickListener {
            val action = ListFieldsFragmentDirections.actionListFieldsFragmentToUpdateFieldFragment(
                currentItem, currentGame
            )
            holder.itemView.findNavController().navigate(action)
        }


    }

    fun setData(fields: List<Field>) {
        fieldsList = fields
        notifyDataSetChanged()
    }

    fun setDataForSearch(fields: List<Field>) {
        fieldsList = fields
        notifyDataSetChanged()
    }*/

}
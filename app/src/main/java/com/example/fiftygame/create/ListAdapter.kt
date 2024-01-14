package com.example.fiftygame.create

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fiftygame.R
import com.example.fiftygame.data.Field
import com.example.fiftygame.databinding.FragmentAddFieldBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var fieldsList = emptyList<Field>()
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.create_custom_row, parent, false))
    }
    override fun getItemCount(): Int {
        return fieldsList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = fieldsList[position]
        holder.itemView.findViewById<TextView>(R.id.id_textView).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.entry_textView).text = currentItem.entry
        holder.itemView.findViewById<TextView>(R.id.question_textView).text = currentItem.question
        holder.itemView.findViewById<TextView>(R.id.correctAnswer_textView).text = currentItem.correctAnswer
    }
    fun setData(field: List<Field>){
        this.fieldsList = field
        notifyDataSetChanged()
    }

}
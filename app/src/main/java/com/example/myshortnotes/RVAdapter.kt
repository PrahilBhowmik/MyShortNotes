package com.example.myshortnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val listener: NotesAdapt) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    private val dataSet = ArrayList<Note>()
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val deleteBtn: ImageButton = view.findViewById(R.id.imageButton)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)
        val viewHolder = ViewHolder(view)
        viewHolder.deleteBtn.setOnClickListener{
            listener.onItemClicked(dataSet[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].text
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updateList(list: ArrayList<Note>){
        dataSet.clear()
        dataSet.addAll(list)
        notifyDataSetChanged()
    }
}

interface NotesAdapt{
    fun onItemClicked(note: Note)
}
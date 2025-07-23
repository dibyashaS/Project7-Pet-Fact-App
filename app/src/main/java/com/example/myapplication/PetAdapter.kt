package com.example.myapplication
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide


class PetAdapter(private val petList: List<Pet>) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage: ImageView = view.findViewById(R.id.pet_image)
        val petName: TextView = view.findViewById(R.id.pet_name)
        val petFact: TextView = view.findViewById(R.id.pet_fact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = petList[position]

        Glide.with(holder.itemView.context)
            .load(pet.imageUrl)
            .fitCenter()
            .into(holder.petImage)

        holder.petName.text = pet.name
        holder.petFact.text = pet.funFact

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "You clicked on ${pet.name}!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = petList.size
}

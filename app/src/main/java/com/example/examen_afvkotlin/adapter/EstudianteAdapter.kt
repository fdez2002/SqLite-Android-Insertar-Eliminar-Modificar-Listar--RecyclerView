package com.example.examen_afvkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_afvkotlin.Objetos.Estudiante
import com.example.examen_afvkotlin.R
import com.example.examen_afvkotlin.databinding.ItemEstudianteBinding

class EstudianteAdapter : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>() {

    private var list : ArrayList<Estudiante> = ArrayList()
    private var onClickItem: ((Estudiante) -> Unit)? = null

    fun addItem(items : ArrayList<Estudiante>) {
        this.list = items
        notifyDataSetChanged()
    }


    fun setOnClickItem(callback: (Estudiante) -> Unit){
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EstudianteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_estudiante, parent, false)
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {

        val item = list[position]
        holder.bindView(item)
        holder.itemView.setOnClickListener { onClickItem?.invoke(item) }
    }

    class EstudianteViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemEstudianteBinding.bind(view)

        var nombre = binding.textViewNombre
        var apellidos = binding.textViewApellidos



        fun bindView(estudiante: Estudiante){
            nombre.text = estudiante.nombre
            apellidos.text = estudiante.apellidos

            if(estudiante.media > 4)
                binding.imageView7.setImageResource(R.drawable.aprobar)
            else binding.imageView7.setImageResource(R.drawable.suspender)




        }


    }


}

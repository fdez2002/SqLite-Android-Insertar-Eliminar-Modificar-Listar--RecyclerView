package com.example.examen_afvkotlin.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_afvkotlin.Objetos.Estudiante
import com.example.examen_afvkotlin.R
import com.example.examen_afvkotlin.databinding.ItemEstudianteBinding

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
package com.example.examen_afvkotlin.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.examen_afvkotlin.miscela.BDEstudiantesOpenHelper
import com.example.examen_afvkotlin.Objetos.Estudiante
import com.example.examen_afvkotlin.R
import com.example.examen_afvkotlin.databinding.FragmentMatricularBinding

class MatricularFragment : Fragment() {
    private lateinit var bind: FragmentMatricularBinding
    private lateinit var  bdALUM : BDEstudiantesOpenHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentMatricularBinding.inflate(layoutInflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bdALUM = BDEstudiantesOpenHelper(context)
        activity?.actionBar?.title = "Matricular"



        //elementos
        bind.imaenMatricular.setImageResource(R.drawable.matricula_alumno)

        bind.editTextIDMatricular.hint = "Introduce el ID del alumno"
        bind.editTextNombreMatricular.hint = "Introduce el nombre del alumno"
        bind.editTextApellidosMatricular.hint = "Introduce los apellidos del alumno"
        bind.editTextMediaMatricular.hint = "Introduce la media del alumno"

        bind.botonAceptarMatricular.setImageResource(R.drawable.aceptar)
        bind.botonCancelarMatricular.setImageResource(R.drawable.cancelar)

        bind.botonAceptarMatricular.setOnClickListener {
            matricular()
        }
        bind.botonCancelarMatricular.setOnClickListener {
            findNavController().navigate(R.id.action_matricularFragment_to_inicioFragment, Bundle().apply {  })
        }
    }

    private fun matricular(){
        var id = bind.editTextIDMatricular.text.toString().trim()
        var nombre = bind.editTextNombreMatricular.text.toString().trim()
        var apellidos = bind.editTextApellidosMatricular.text.toString().trim()
        var media = bind.editTextMediaMatricular.text.toString().trim()

        if(id.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || media.isEmpty()) {
            Toast.makeText(context, "Rellene todos los datos", Toast.LENGTH_SHORT).show()
        }else {
            val alum = Estudiante(id = id.toInt(), nombre = nombre, apellidos = apellidos, media = media.toFloat())
            val status = bdALUM.insertAlumno(alum)

            if (status > -1) {
                Toast.makeText(context, "Alumno añadido", Toast.LENGTH_SHORT).show()
                clearEdiText()

            } else Toast.makeText(context, "El id ya esta en uso", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearEdiText() {
        bind.editTextIDMatricular.text.clear()
        bind.editTextNombreMatricular.text.clear()
        bind.editTextApellidosMatricular.text.clear()
        bind.editTextMediaMatricular.text.clear()
    }
}
package com.example.examen_afvkotlin.fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.examen_afvkotlin.miscela.BDEstudiantesOpenHelper
import com.example.examen_afvkotlin.adapter.EstudianteAdapter
import com.example.examen_afvkotlin.Objetos.Estudiante
import com.example.examen_afvkotlin.R
import com.example.examen_afvkotlin.databinding.FragmentEditarBinding

class EditarFragment : Fragment() {
    private lateinit var bind: FragmentEditarBinding
    private val alu : Estudiante? = null
    private lateinit var  bdALUM : BDEstudiantesOpenHelper
    private var  adapterAlumnos: EstudianteAdapter? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentEditarBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bdALUM = BDEstudiantesOpenHelper(context)

        //elementos
        bind.imagenEditar.setImageResource(R.drawable.editar)

        bind.editTextIDEditar.hint = "Introduce el ID del alumno"
        bind.editTextNombreEditar.hint = "Introduce el nombre del alumno"
        bind.editTextApellidosEditar.hint = "Introduce los apellidos del alumno"
        bind.editTextMediaEditar.hint = "Introduce la media del alumno"

        bind.botonAceptarEditar.setImageResource(R.drawable.aceptar)
        bind.botonCancelarEditar.setImageResource(R.drawable.cancelar)

        bind.botonAceptarEditar.setOnClickListener {
            updateAlumnos();
        }
        bind.botonCancelarEditar.setOnClickListener {
            findNavController().navigate(R.id.action_editarFragment2_to_inicioFragment, Bundle().apply {  })
        }

    }
    private fun updateAlumnos() {
        var id = bind.editTextIDEditar.text.toString().trim()
        var nombre = bind.editTextNombreEditar.text.toString().trim()
        var apellidos = bind.editTextApellidosEditar.text.toString().trim()
        var media = bind.editTextMediaEditar.text.toString().trim()

        if(id.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || media.isEmpty()){
            Toast.makeText(context, "Rellene todos los campos", Toast.LENGTH_SHORT).show()

        }else {
            val alu = Estudiante(
                id = id.toInt(),
                nombre = nombre,
                apellidos = apellidos,
                media = media.toFloat()
            )
            if (bdALUM.updateAlumno(alu) == 0){
                Toast.makeText(context, "El id no existe", Toast.LENGTH_SHORT).show()

            }else{
                val status = bdALUM.updateAlumno(alu)

                if (status > -1) {
                    getAlumnos()
                    Toast.makeText(context, "Datos modificados", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
    private fun getAlumnos(){
        val alList = bdALUM.getAllAlumnos()
        Log.e("pppp", "${alList.size}")

        adapterAlumnos?.addItem(alList)
    }
}
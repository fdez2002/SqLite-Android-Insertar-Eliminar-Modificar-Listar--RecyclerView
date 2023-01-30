package com.example.examen_afvkotlin.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.examen_afvkotlin.R
import com.example.examen_afvkotlin.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {
     private lateinit var bind: FragmentInicioBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentInicioBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.imagenInicio.setImageResource(R.drawable.escuela)

        bind.botonMatriculaInicico.setImageResource(R.drawable.matricula_alumno)
        bind.botonEditarInicio.setImageResource(R.drawable.editar)
        bind.botonBorrarInicio.setImageResource(R.drawable.borrar)
        bind.botonListarInicio.setImageResource(R.drawable.listar)

        bind.botonMatriculaInicico.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_matricularFragment2, Bundle().apply {  })
        }
        bind.botonEditarInicio.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_editarFragment2, Bundle().apply {  })
        }
        bind.botonBorrarInicio.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_borrarFragment, Bundle().apply {  })
        }
        bind.botonListarInicio.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_listarFragment, Bundle().apply {  })
        }
    }
}
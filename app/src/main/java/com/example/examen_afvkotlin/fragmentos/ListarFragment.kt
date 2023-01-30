package com.example.examen_afvkotlin.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.examen_afvkotlin.miscela.BDEstudiantesOpenHelper
import com.example.examen_afvkotlin.R
import com.example.examen_afvkotlin.databinding.FragmentListarBinding

class ListarFragment : Fragment() {
    private lateinit var bind: FragmentListarBinding
    private lateinit var  bdALUM : BDEstudiantesOpenHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentListarBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bdALUM = BDEstudiantesOpenHelper(context)
        //recursos
        bind.imagenListar.setImageResource(R.drawable.editar)
        bind.editTextListar.hint = "Introduce el id del alumno"
        bind.botonAceptarListar.setImageResource(R.drawable.aceptar)
        bind.botonCancelarListar.setImageResource(R.drawable.cancelar)

            bind.rdGroup.setOnCheckedChangeListener{ _, checkedId ->
                if (checkedId == R.id.radioButton_id) {
                    bind.botonAceptarListar.setOnClickListener {
                        if(bind.editTextListar.text.toString().isEmpty()){
                            Toast.makeText(context, "Rellene el campo", Toast.LENGTH_SHORT).show()
                        }else {
                            findNavController().navigate(
                                R.id.action_listarFragment_to_listadoFragment2,
                                Bundle().apply {
                                    putString("valor", "1")
                                    putString("id", bind.editTextListar.text.toString())
                                })
                        }
                    }
                }
                if (checkedId == R.id.radioButton_todo)
                    bind.botonAceptarListar.setOnClickListener {
                        findNavController().navigate(
                            R.id.action_listarFragment_to_listadoFragment2,
                            Bundle().apply {
                                putString("valor", "2")

                            })
                    }

            }

        bind.botonCancelarListar.setOnClickListener {
            findNavController().navigate(R.id.action_listarFragment_to_inicioFragment, Bundle().apply {  })
        }
    }



}
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
import com.example.examen_afvkotlin.databinding.FragmentBorrarBinding

class BorrarFragment : Fragment() {
    private lateinit var bind: FragmentBorrarBinding
    private lateinit var  bdALUM : BDEstudiantesOpenHelper



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentBorrarBinding.inflate(layoutInflater, container, false)


        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bdALUM = BDEstudiantesOpenHelper(context)
        //elementos
        bind.imagenBorrar.setImageResource(R.drawable.borrar)
        bind.etIdBorrar.hint = "Introduce el id del alumno"
        bind.botonAceptarBorrar.setImageResource(R.drawable.aceptar)
        bind.bontonCancelarBorrar.setImageResource(R.drawable.cancelar)

        bind.botonAceptarBorrar.setOnClickListener {
            if(bind.etIdBorrar.text.toString().isEmpty()){
                Toast.makeText(context, "Escribe un id", Toast.LENGTH_LONG).show()
            }else deleteAlumno(bind.etIdBorrar.text.toString())
        }
        bind.bontonCancelarBorrar.setOnClickListener {
            findNavController().navigate(R.id.action_borrarFragment_to_inicioFragment, Bundle().apply {  })
        }



    }
    fun deleteAlumno(id : String ){
        var id2 = id.toInt() ?: return

            if (bdALUM.deleteAlumno(id2) == 0){
                Toast.makeText(context, "Este id no existe", Toast.LENGTH_LONG).show()

            }else{
                bdALUM.deleteAlumno(id2)
                Toast.makeText(context, "Se ha eliminado correctamente", Toast.LENGTH_LONG).show()


            }


    }
    
}
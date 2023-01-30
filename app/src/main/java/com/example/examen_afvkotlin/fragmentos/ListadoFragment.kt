package com.example.examen_afvkotlin.fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen_afvkotlin.miscela.BDEstudiantesOpenHelper
import com.example.examen_afvkotlin.adapter.EstudianteAdapter
import com.example.examen_afvkotlin.databinding.FragmentListadoBinding


class ListadoFragment : Fragment() {
    private lateinit var bind: FragmentListadoBinding
    private var  adapterAlumnos: EstudianteAdapter? = null
    private  lateinit var bdAlumnos : BDEstudiantesOpenHelper



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentListadoBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        bdAlumnos = BDEstudiantesOpenHelper(context)

        if(requireArguments().getString("valor") == "1"){
            getAlumno()

        }else
            getAlumnos()


        adapterAlumnos?.setOnClickItem{
            Toast.makeText(context, "Id: " + it.id.toString() + '\n' + "Media: " +  it.media, Toast.LENGTH_LONG).show()
        }

    }

    private fun initRecyclerView(){
        bind.recyclerViewAlumnos.layoutManager = LinearLayoutManager(context)
        adapterAlumnos = EstudianteAdapter()
        bind.recyclerViewAlumnos.adapter = adapterAlumnos
    }
    private fun getAlumnos(){
        val alList = bdAlumnos.getAllAlumnos()
        Log.e("pppp", "${alList.size}")

        adapterAlumnos?.addItem(alList)
    }
    private fun getAlumno(){
        val item = bdAlumnos.getAllumno(requireArguments().getString("id")!!.toInt())
        Log.e("pppp", "${item.size}")

        adapterAlumnos?.addItem(item)
    }
}
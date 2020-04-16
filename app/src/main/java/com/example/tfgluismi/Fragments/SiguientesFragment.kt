package com.example.tfgluismi.Fragments

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tfgluismi.R
import kotlinx.android.synthetic.main.activity_mostrar_tareas.*

class SiguientesFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var textos: ArrayList<String>
    lateinit var globalContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_mostrar_tareas, container, false)
    }

    override fun onStart() {
        super.onStart()
        crearTareas()
        eliminarTarea()
    }

    //-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearTareas(){
        textos = BdAdmin.getTareas()!!
        globalContext = this.getActivity()!!
        val adaptador = ArrayAdapter(globalContext,android.R.layout.simple_list_item_1,textos!!)
        ListTareas.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListTareas.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            val item = textos!!.get(i)
            Toast.makeText(AppTFGLuismi.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }
    }

//----------------------------------------------------------DELETES-------------------------------------------------------------------

    fun eliminarTarea(){
        ListTareas.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val texto = textos.get(i)
            globalContext = this.getActivity()!!
            val dialog = AlertDialog.Builder(globalContext)
            dialog.setTitle("Confirmación")
            dialog.setMessage("¿Quieres borrar la tarea?")
            dialog.setPositiveButton("Si"){dialogInterface, i ->
                BdAdmin.removeTarea(texto)
                crearTareas()
            }
            dialog.setNegativeButton("No"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }
    }
}
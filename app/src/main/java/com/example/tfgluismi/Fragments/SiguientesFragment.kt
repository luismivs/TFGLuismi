package com.example.tfgluismi.Fragments

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tfgluismi.DetalleTarea
import com.example.tfgluismi.R
import kotlinx.android.synthetic.main.activity_mostrar_tareas.*

class SiguientesFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var globalContext: Context
    lateinit var textos: ArrayList<String>
    lateinit var ids: ArrayList<Int>
    lateinit var fechas: ArrayList<String>
    lateinit var horas: ArrayList<String>
    lateinit var usuariosDelegados: ArrayList<String>
    lateinit var tipoListas: ArrayList<String>
    lateinit var proyectos: ArrayList<String>

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
        ids = BdAdmin.getTareasId()!!
        fechas = BdAdmin.getTareasFecha()!!
        horas = BdAdmin.getTareasHora()!!
        usuariosDelegados = BdAdmin.getTareasUsuarioDelegado()!!
        tipoListas = BdAdmin.getTareasTipoLista()!!
        proyectos = BdAdmin.getTareasProyecto()!!
        globalContext = this.getActivity()!!

        val adaptador = ArrayAdapter(globalContext,android.R.layout.simple_list_item_1,textos!!)
        ListTareas.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListTareas.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->

            val intent = Intent(activity, DetalleTarea::class.java)

            //Creamos un objeto Bundle para mandar datos a la activity DetalleTarea
            val bundle = Bundle()
            //Los objetos Bundle funcionan con pares clave ("itemId") - valor (ids!!.get(i))
            bundle.putInt("itemId",ids!!.get(i))
            bundle.putString("itemTx",textos!!.get(i))
            bundle.putString("itemF",fechas!!.get(i))
            bundle.putString("itemH",horas!!.get(i))
            bundle.putString("itemUD",usuariosDelegados!!.get(i))
            bundle.putString("itemTL",tipoListas!!.get(i))
            bundle.putString("itemP",proyectos!!.get(i))
            //Se añade el contenido del Bundle al intent
            intent.putExtras(bundle)

            startActivity(intent)
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
package com.example.tfgluismi.Fragments

import Data.AdminBD
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tfgluismi.DetalleProyecto
import com.example.tfgluismi.AgregarProyecto
import com.example.tfgluismi.R
import kotlinx.android.synthetic.main.activity_mostrar_proyectos.*

class ProyectosFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var globalContext: Context
    lateinit var descripcion: ArrayList<String>
    lateinit var textos: ArrayList<String>
    lateinit var ids: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_mostrar_proyectos, container, false)

        val button: Button = view.findViewById(R.id.bt_add_proyecto)
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, AgregarProyecto::class.java)
            startActivity(intent)
        })
        return view
    }

    override fun onStart() {
        super.onStart()
        crearProyecto()
    }

    //-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearProyecto(){

        textos = BdAdmin.getProyecto()!!
        descripcion = BdAdmin.getProyectoDes()!!
        ids = BdAdmin.getProyectoId()!!
        globalContext = this.getActivity()!!

        val adaptador = ArrayAdapter(globalContext,android.R.layout.simple_list_item_1,textos!!)
        ListProyectos.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListProyectos.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->

            val intent = Intent(activity, DetalleProyecto::class.java)

            //Creamos un objeto Bundle para mandar datos ID, texto y descripcion a la activity ActualizarProyecto
            val bundle = Bundle()
            //Los objetos Bundle funcionan con pares clave ("itemId") - valor (ids!!.get(i))
            bundle.putInt("itemId",ids!!.get(i))
            bundle.putString("itemTxt",textos!!.get(i))
            bundle.putString("itemDes",descripcion!!.get(i))
            //Se añade el contenido del Bundle al intent
            intent.putExtras(bundle)

            startActivity(intent)
        }
    }
}
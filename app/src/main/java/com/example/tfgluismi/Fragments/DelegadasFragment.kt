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
import com.example.tfgluismi.DetalleTareaDelegar
import com.example.tfgluismi.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mostrar_tareas.*

class DelegadasFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var globalContext: Context
    lateinit var textos: ArrayList<String>
    lateinit var ids: ArrayList<Int>
    lateinit var fechas: ArrayList<String>
    lateinit var horas: ArrayList<String>
    lateinit var imagenes: ArrayList<String>
    lateinit var usuariosDelegados: ArrayList<String>
    lateinit var isDelegadas: ArrayList<Int>
    lateinit var tipoListas: ArrayList<String>
    lateinit var proyectos: ArrayList<String>
    lateinit var copiaTextos: ArrayList<String>
    lateinit var copiaIds: ArrayList<Int>
    lateinit var copiaFechas: ArrayList<String>
    lateinit var copiaHoras: ArrayList<String>
    lateinit var copiaImagenes: ArrayList<String>
    lateinit var copiaUsuariosDelegados: ArrayList<String>
    lateinit var copiaIsDelegadas: ArrayList<Int>
    lateinit var copiaTipoListas: ArrayList<String>
    lateinit var copiaProyectos: ArrayList<String>

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

        //Asignamos los valores de la base de datos a sus variables
        textos = BdAdmin.getTareas()!!
        ids = BdAdmin.getTareasId()!!
        fechas = BdAdmin.getTareasFecha()!!
        horas = BdAdmin.getTareasHora()!!
        imagenes = BdAdmin.getTareasImagen()!!
        usuariosDelegados = BdAdmin.getTareasUsuarioDelegado()!!
        isDelegadas = BdAdmin.getTareasIsDelegada()!!
        tipoListas = BdAdmin.getTareasTipoLista()!!
        proyectos = BdAdmin.getTareasProyecto()!!
        globalContext = this.getActivity()!!

        //Creamos copias de las variables anteriores para trabajar con ellas a la hora de selecionar un elemento de listView y asi
        //no tener fallos en los indices de las tareas para mandarlos por el bundel y filtrar correctamente en el listview
        copiaTextos = textos.clone() as ArrayList<String>
        copiaIds = ids.clone() as ArrayList<Int>
        copiaFechas = fechas.clone() as ArrayList<String>
        copiaHoras = horas.clone() as ArrayList<String>
        copiaImagenes = imagenes.clone() as ArrayList<String>
        copiaUsuariosDelegados = usuariosDelegados.clone() as ArrayList<String>
        copiaIsDelegadas = isDelegadas.clone() as ArrayList<Int>
        copiaTipoListas = tipoListas.clone() as ArrayList<String>
        copiaProyectos = proyectos.clone() as ArrayList<String>

        //Filtramos las tareas que deben mostrarse (que sean TipoLista = Delegadas)
        var size = tipoListas.size
        if(size>=1) {
            size = tipoListas.size - 1
            var aux = -1
            for(i in 0..size){
                if(tipoListas.get(i) != "Delegadas"){
                    var cont = i
                    aux = aux + 1
                    copiaTextos.removeAt(cont-aux)
                    copiaIds.removeAt(cont-aux)
                    copiaFechas.removeAt(cont-aux)
                    copiaHoras.removeAt(cont-aux)
                    copiaImagenes.removeAt(cont-aux)
                    copiaUsuariosDelegados.removeAt(cont-aux)
                    copiaIsDelegadas.removeAt(cont-aux)
                    copiaTipoListas.removeAt(cont-aux)
                    copiaProyectos.removeAt(cont-aux)
                }
            }
        }


        val adaptador = ArrayAdapter(globalContext,android.R.layout.simple_list_item_1,copiaTextos!!)
        ListTareas.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListTareas.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->

            val intent = Intent(activity, DetalleTareaDelegar::class.java)

            //Creamos un objeto Bundle para mandar datos a la activity DetalleTarea
            val bundle = Bundle()
            //Los objetos Bundle funcionan con pares clave ("itemId") - valor (ids!!.get(i))
            bundle.putInt("itemId",copiaIds!!.get(i))
            bundle.putString("itemTx",copiaTextos!!.get(i))
            bundle.putString("itemF",copiaFechas!!.get(i))
            bundle.putString("itemH",copiaHoras!!.get(i))
            bundle.putString("itemIm",copiaImagenes!!.get(i))
            bundle.putString("itemUD",copiaUsuariosDelegados!!.get(i))
            bundle.putInt("itemIsD",copiaIsDelegadas!!.get(i))
            bundle.putString("itemTL",copiaTipoListas!!.get(i))
            bundle.putString("itemP",copiaProyectos!!.get(i))
            //Se añade el contenido del Bundle al intent
            intent.putExtras(bundle)

            startActivity(intent)
        }
    }

//----------------------------------------------------------DELETES-------------------------------------------------------------------

    fun eliminarTarea(){
        ListTareas.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val texto = copiaTextos.get(i)
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
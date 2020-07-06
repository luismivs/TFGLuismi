package com.example.tfgluismi.Fragments

import Data.AdminBD
import Data.AppTFGLuismi
import Data.Tarea
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.tfgluismi.MostrarArchProy
import com.example.tfgluismi.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File
import java.net.URI

class MainFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var tareasTipoLista: ArrayList<String>
    lateinit var tareaProyecto: ArrayList<String>
    lateinit var globalContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_home, container, false)
        //Calculamos y mostramos las tareas por clasificar y por atender
        tareasTipoLista = BdAdmin.getTareasTipoLista()!!
        tareaProyecto = BdAdmin.getProyecto()!!
        var contClasificar: Int = 0
        var contAtender: Int = 0
        var contSiguientes: Int = 0
        var contCalendario: Int = 0
        var contDelegadas: Int = 0
        var contAlgunDia: Int = 0
        var contProyectos: Int = tareaProyecto.size
        var size = tareasTipoLista.size-1
        for (i in 0..size){
            if(tareasTipoLista.get(i) != "" && tareasTipoLista.get(i) != "Seleccione"){
                contAtender = contAtender+1
            }
            if(tareasTipoLista.get(i).equals("") || tareasTipoLista.get(i).equals("Seleccione")){
                contClasificar = contClasificar+1
            }
            if(tareasTipoLista.get(i).equals("Programadas")){
                contCalendario = contCalendario+1
            }
            if(tareasTipoLista.get(i).equals("Cuando Puedas")){
                contSiguientes = contSiguientes+1
            }
            if(tareasTipoLista.get(i).equals("Delegadas")){
                contDelegadas = contDelegadas+1
            }
            if(tareasTipoLista.get(i).equals("Algun dia")){
                contAlgunDia = contAlgunDia+1
            }
        }

        val textViewCla: TextView = view.findViewById(R.id.txTareasPorClasificar)
        textViewCla.setText("Tareas pendientes de clasificar: " + contClasificar)
        val textViewAten: TextView = view.findViewById(R.id.txTareasPorAtender)
        textViewAten.setText("Tareas pendientes de atender: " + contAtender)
        val textViewCalendario: TextView = view.findViewById(R.id.txContadorCalendario)
        textViewCalendario.setText(contCalendario.toString())
        val textViewSiguientes: TextView = view.findViewById(R.id.txContadorSiguientes)
        textViewSiguientes.setText(contSiguientes.toString())
        val textViewDelegadas: TextView = view.findViewById(R.id.txContadorDelegadas)
        textViewDelegadas.setText(contDelegadas.toString())
        val textViewAlgunDia: TextView = view.findViewById(R.id.txContadorAlgunDia)
        textViewAlgunDia.setText(contAlgunDia.toString())
        val textViewProy: TextView = view.findViewById(R.id.txProyectosActuales)
        textViewProy.setText("Proyectos actuales: " + contProyectos)


        //Tratamos la funcionalidad del boton de guardar la tarea
        val button: Button = view.findViewById(R.id.btGuardar)
        button.setOnClickListener(View.OnClickListener {
            val tarea = Tarea(0, txPrincipal.text.toString(), "", "", "", "", 0,"","")
            if (tarea.texto != "") {
                BdAdmin.addTarea(tarea)
                txPrincipal.setText("")
                Toast.makeText(AppTFGLuismi.CONTEXT, "Tarea guardada", Toast.LENGTH_SHORT).show()
            }else Toast.makeText(AppTFGLuismi.CONTEXT, "Introduce una tarea", Toast.LENGTH_SHORT).show()

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, MainFragment())
            transaction?.commit()
        })

        //Tratamos la funcionalidad de mostrar informacion del tipo de lista al tocar su imagen

        /*val imCalendarioIr: ImageView = view.findViewById(R.id.imageCalendario)
        imCalendarioIr.setOnClickListener({
            val transactionCalendario = fragmentManager?.beginTransaction()
            transactionCalendario?.replace(R.id.container, CalendarioFragment())
            transactionCalendario?.commit()
        })*/

        val imCalendario: ImageView = view.findViewById(R.id.imageCalendario)
        imCalendario.setOnLongClickListener {
            //val texto = copiaTextos.get(i)
            globalContext = this.getActivity()!!
            val dialog = AlertDialog.Builder(globalContext)
            dialog.setTitle("Programadas")
            dialog.setMessage("Tareas ques tienen una fecha y hora concreta")
            dialog.setNegativeButton("OK"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }

        /*val imSiguientes: ImageView = view.findViewById(R.id.imageSiguientes)
        imSiguientes.setOnClickListener({
            val transactionSiguientes = fragmentManager?.beginTransaction()
            transactionSiguientes?.replace(R.id.container, SiguientesFragment())
            transactionSiguientes?.commit()
        })*/

        val imSiguientes: ImageView = view.findViewById(R.id.imageSiguientes)
        imSiguientes.setOnLongClickListener {
            //val texto = copiaTextos.get(i)
            globalContext = this.getActivity()!!
            val dialog = AlertDialog.Builder(globalContext)
            dialog.setTitle("Cuando puedas")
            dialog.setMessage("Estas tareas no tienen un plazo de tiempo definido, pero debes realizarlas lo antes posible")
            dialog.setNegativeButton("OK"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }

        /*val imDelegadas: ImageView = view.findViewById(R.id.imageDelegada)
        imDelegadas.setOnClickListener({
            val transactionDelegadas = fragmentManager?.beginTransaction()
            transactionDelegadas?.replace(R.id.container, DelegadasFragment())
            transactionDelegadas?.commit()
        })*/

        val imDelegadas: ImageView = view.findViewById(R.id.imageDelegada)
        imDelegadas.setOnLongClickListener {
            //val texto = copiaTextos.get(i)
            globalContext = this.getActivity()!!
            val dialog = AlertDialog.Builder(globalContext)
            dialog.setTitle("Delegadas")
            dialog.setMessage("Tareas que han sido compartidas con otro usuario")
            dialog.setNegativeButton("OK"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }

        /*val imAlgunDia: ImageView = view.findViewById(R.id.imageAlgunDia)
        imAlgunDia.setOnClickListener({
            val transactionAlgunDia = fragmentManager?.beginTransaction()
            transactionAlgunDia?.replace(R.id.container, AlgunDiaFragment())
            transactionAlgunDia?.commit()
        })*/

        val imAlgunDia: ImageView = view.findViewById(R.id.imageAlgunDia)
        imAlgunDia.setOnLongClickListener {
            //val texto = copiaTextos.get(i)
            globalContext = this.getActivity()!!
            val dialog = AlertDialog.Builder(globalContext)
            dialog.setTitle("Algun día")
            dialog.setMessage("Tareas o planes ha realizar en un largo plazo de tiempo")
            dialog.setNegativeButton("OK"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }

        return view
    }
}

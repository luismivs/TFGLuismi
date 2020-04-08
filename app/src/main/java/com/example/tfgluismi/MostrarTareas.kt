package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mostrar_tareas.*


class MostrarTareas: AppCompatActivity() {

    val BdAdmin = AdminBD()
    lateinit var textos: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_tareas)
        eliminarTarea()
        //TODO detallesTarea()

        buttonMostrarAC.setOnClickListener{
            val intent = Intent(this,MostrarArchCon::class.java)
            startActivity(intent)
        }

        buttonMostrarP.setOnClickListener{
            val intent = Intent(this,MostrarProyectos::class.java)
            startActivity(intent)
        }

        buttonMostrarTareas.setOnClickListener{
            val intent = Intent(this,MostrarArchProy::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        crearTareas()
    }

    override fun onResume() {
        super.onResume()
    }

//-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearTareas(){
        textos = BdAdmin.getTareas()!!
        val adaptador = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,textos!!)
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

            val dialog = AlertDialog.Builder(this)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_tareas,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item!!.itemId){
            R.id.action_add -> {
                val intentAddTarea = Intent(applicationContext,AgregarTarea::class.java)
                startActivity(intentAddTarea)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
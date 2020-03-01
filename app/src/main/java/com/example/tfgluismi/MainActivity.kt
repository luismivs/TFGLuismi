package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val BdAdmin = AdminBD()
    lateinit var textos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            eliminarTarea()
    }

    override fun onStart() {
        super.onStart()
        crearTareas()
    }

    override fun onResume() {
        super.onResume()
    }

    fun crearTareas(){
        textos = BdAdmin.getTareas()!!
        val adaptador = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,textos!!)
        ListTareas.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListTareas.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, i, l ->
            val item = textos!!.get(i)
            Toast.makeText(AppTFGLuismi.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }
    }

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
                val intentAdd = Intent(applicationContext,AgregarTarea::class.java)
                startActivity(intentAdd)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}

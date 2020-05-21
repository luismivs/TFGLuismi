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
import kotlinx.android.synthetic.main.activity_mostrar_proyectos.*

class MostrarProyectos: AppCompatActivity() {

    val BdAdmin = AdminBD()
    lateinit var textosid: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_proyectos)
        //eliminarProyecto()
    }

    override fun onStart() {
        super.onStart()
        crearProyecto()
    }

    override fun onResume() {
        super.onResume()
    }

    //-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearProyecto(){
        textosid = BdAdmin.getProyecto()!!
        val adaptador = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,textosid!!)
        ListProyectos.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListProyectos.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            val item = textosid!!.get(i)
            Toast.makeText(AppTFGLuismi.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }
    }

//----------------------------------------------------------DELETES-------------------------------------------------------------------
/*
    fun eliminarProyecto(){
        ListProyectos.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val id = textosid.get(i)

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Confirmación")
            dialog.setMessage("¿Quieres borrar el proyecto?")
            dialog.setPositiveButton("Si"){dialogInterface, i ->
                BdAdmin.removeProyecto(id)
                crearProyecto()
            }
            dialog.setNegativeButton("No"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }
    }
*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_proyectos,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item!!.itemId){
            R.id.action_add_proy -> {
                val intentAddProyectos = Intent(applicationContext,AgregarProyecto::class.java)
                startActivity(intentAddProyectos)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
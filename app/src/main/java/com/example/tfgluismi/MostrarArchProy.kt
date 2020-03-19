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
import kotlinx.android.synthetic.main.activity_mostrar_arch_con.*
import kotlinx.android.synthetic.main.activity_mostrar_arch_proy.*

class MostrarArchProy : AppCompatActivity() {

    val BdAdmin = AdminBD()
    lateinit var textos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_arch_proy)
        eliminarArchProy()
    }

    override fun onStart() {
        super.onStart()
        crearArchProy()
    }

    override fun onResume() {
        super.onResume()
    }

    //-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearArchProy(){
        textos = BdAdmin.getArchProy()!!
        val adaptador = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,textos!!)
        ListArchProy.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListArchProy.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            val item = textos!!.get(i)
            Toast.makeText(AppTFGLuismi.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }
    }

//----------------------------------------------------------DELETES-------------------------------------------------------------------

    fun eliminarArchProy(){
        ListArchProy.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val texto = textos.get(i)

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Confirmación")
            dialog.setMessage("¿Quieres borrar el archivo de proyecto?")
            dialog.setPositiveButton("Si"){dialogInterface, i ->
                BdAdmin.removeArchProy(texto)
                crearArchProy()
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
        inflate.inflate(R.menu.menu_arch_proy,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item!!.itemId){
            R.id.action_add_AP -> {
                val intentAddArchProy = Intent(applicationContext,AgregarArchProy::class.java)
                startActivity(intentAddArchProy)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
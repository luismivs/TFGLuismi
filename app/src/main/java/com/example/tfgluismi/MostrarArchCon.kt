package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mostrar_arch_con.*

class MostrarArchCon : AppCompatActivity() {

    val BdAdmin = AdminBD()
    lateinit var textos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_arch_con)
        eliminarArchCon()
    }

    override fun onStart() {
        super.onStart()
        crearArchCon()
    }

    override fun onResume() {
        super.onResume()
    }

    //-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearArchCon(){
        textos = BdAdmin.getArchCon()!!
        val adaptador = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,textos!!)
        ListArchCon.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListArchCon.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            val item = textos!!.get(i)
            Toast.makeText(AppTFGLuismi.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }
    }

//----------------------------------------------------------DELETES-------------------------------------------------------------------

    fun eliminarArchCon(){
        ListArchCon.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val texto = textos.get(i)

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Confirmación")
            dialog.setMessage("¿Quieres borrar el archivo de consulta?")
            dialog.setPositiveButton("Si"){dialogInterface, i ->
                BdAdmin.removeArchCon(texto)
                crearArchCon()
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
        inflate.inflate(R.menu.menu_arch_con,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item!!.itemId){
            R.id.action_add_AC -> {
                val intentAddArchCon = Intent(applicationContext,AgregarArchCon::class.java)
                startActivity(intentAddArchCon)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
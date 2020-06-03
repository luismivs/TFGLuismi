package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detalle_proyecto.*
import kotlinx.android.synthetic.main.activity_mostrar_arch_con.*
import kotlinx.android.synthetic.main.activity_mostrar_arch_proy.*

class MostrarArchProy : AppCompatActivity() {

    val BdAdmin = AdminBD()
    var titulo: String = "Notas de proyecto"
    var bundle = Bundle()
    lateinit var globalContext: Context
    lateinit var textos: ArrayList<String>
    lateinit var ids: ArrayList<Int>
    lateinit var proy: ArrayList<String>
    lateinit var copiatextos: ArrayList<String>
    lateinit var copiaids: ArrayList<Int>
    lateinit var copiaproy: ArrayList<String>
    var textoProy: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_arch_proy)
        this.setTitle(titulo)
        eliminarArchProy()
        add_Arch_Proy_click()

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment ProyectosFragment
        bundle= this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment ProyectosFragment
        textoProy=bundle.getString("itemTxt2")
    }

    override fun onStart() {
        super.onStart()
        crearArchProy()
    }

    override fun onResume() {
        super.onResume()
    }

    fun add_Arch_Proy_click(){
        bt_add_ap.setOnClickListener(){
            val intent = Intent(this, AgregarArchProy::class.java)
            //Creamos un objeto Bundle para mandar datos ID, texto y descripcion a la activity ActualizarProyecto
            val bundle2 = Bundle()
            //Los objetos Bundle funcionan con pares clave ("itemId") - valor (ids!!.get(i))
            bundle2.putString("itemTxt3",textoProy!!)
            //Se añade el contenido del Bundle al intent
            intent.putExtras(bundle2)
            startActivity(intent)
        }
    }
    //-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearArchProy(){

        textos = BdAdmin.getArchProy()!!
        ids = BdAdmin.getArchProyId()!!
        proy = BdAdmin.getArchProyProyecto()!!
        globalContext = this

        //Creamos copias de las variables anteriores para trabajar con ellas a la hora de selecionar un elemento de listView y asi
        //no tener fallos en los indices de los archivos para mandarlos por el bundel y filtrar correctamente en el listview
        copiatextos = textos.clone() as ArrayList<String>
        copiaids = ids.clone() as ArrayList<Int>
        copiaproy = proy.clone() as ArrayList<String>

        //Filtramos los archivos que deben mostrarse (los que coinciden con el proyecto actual)
        var size = textos.size
        if(size>=1) {
            size = textos.size - 1
            var aux = -1
            for(i in 0..size){
                if(proy.get(i) != textoProy){
                    var cont = i
                    aux = aux + 1
                    copiatextos.removeAt(cont-aux)
                    copiaids.removeAt(cont-aux)
                    copiaproy.removeAt(cont-aux)
                }
            }
        }

        val adaptador = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,copiatextos!!)
        ListArchProy.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListArchProy.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->

            val intent = Intent(this, ActualizarArchProy::class.java)

            //Creamos un objeto Bundle para mandar datos ID y texto a la activity ActualizarArchCon
            val bundle = Bundle()
            //Los objetos Bundle funcionan con pares clave ("itemId") - valor (ids!!.get(i))
            bundle.putInt("itemId",copiaids!!.get(i))
            bundle.putString("itemTx",copiatextos!!.get(i))
            bundle.putString("itemProy",copiaproy!!.get(i))
            //Se añade el contenido del Bundle al intent
            intent.putExtras(bundle)

            startActivity(intent)
        }
    }

//----------------------------------------------------------DELETES-------------------------------------------------------------------

    fun eliminarArchProy(){
        ListArchProy.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val texto = copiatextos.get(i)

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
/*
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
    }*/
}
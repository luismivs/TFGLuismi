package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import Data.Proyecto
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detalle_proyecto.*
import kotlinx.android.synthetic.main.activity_mostrar_tareas.*

class DetalleProyecto: AppCompatActivity() {

    val BdAdmin = AdminBD()
    var titulo: String = "Resumen de proyecto"
    var bundle = Bundle()
    var ID: Int = 0
    var texto: String? = ""
    var descripcion: String? = ""
    lateinit var globalContext: Context
    lateinit var textoTareas: ArrayList<String>
    lateinit var idsTareas: ArrayList<Int>
    lateinit var fechasTareas: ArrayList<String>
    lateinit var horasTareas: ArrayList<String>
    lateinit var imagenesTareas: ArrayList<String>
    lateinit var usuariosDelegadosTareas: ArrayList<String>
    lateinit var isDelegadasTareas: ArrayList<Int>
    lateinit var tipoListasTareas: ArrayList<String>
    lateinit var proyectosTareas: ArrayList<String>
    lateinit var copiaTextosTareas: ArrayList<String>
    lateinit var copiaIdsTareas: ArrayList<Int>
    lateinit var copiaFechasTareas: ArrayList<String>
    lateinit var copiaHorasTareas: ArrayList<String>
    lateinit var copiaImagenesTareas: ArrayList<String>
    lateinit var copiaUsuariosDelegadosTareas: ArrayList<String>
    lateinit var copiaIsDelegadasTareas: ArrayList<Int>
    lateinit var copiaTipoListasTareas: ArrayList<String>
    lateinit var copiaProyectosTareas: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_proyecto)
        this.setTitle(titulo)
        actualizarProyecto_click()
        consultarArchProy_click()
        eliminarProyecto()

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment ProyectosFragment
        bundle= this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment ProyectosFragment
        ID=bundle.getInt("itemId")
        texto=bundle.getString("itemTxt")
        descripcion=bundle.getString("itemDes")
        //Modificamos el TextView del layout activity_detalle_proyecto para que muestre los datos que hemos recibido por Bundle
        val textView: TextView = findViewById(R.id.textView3)
        textView.setText(texto)
        //Modificamos el cuadro de texto "descripcion" para que conserve el valor que ya tenía
        val plainTextDes: EditText = findViewById(R.id.txtActualizarDesProy)
        plainTextDes.setText((descripcion))
    }

    override fun onStart() {
        super.onStart()
        crearTareas()
        eliminarTarea()
    }

    fun actualizarProyecto_click(){
            btActualizarProy.setOnClickListener(){
                val proy = Proyecto(ID,texto.toString(), txtActualizarDesProy.text.toString())
                BdAdmin.updateProyecto(proy)
                finish()
            }
    }

    fun consultarArchProy_click(){
        btConsultarArchProy.setOnClickListener(){
            val intent = Intent(this, MostrarArchProy::class.java)
            //Creamos un objeto Bundle para mandar datos ID, texto y descripcion a la activity ActualizarProyecto
            val bundle2 = Bundle()
            //Los objetos Bundle funcionan con pares clave ("itemId") - valor (ids!!.get(i))
            bundle2.putString("itemTxt2",texto!!)
            //Se añade el contenido del Bundle al intent
            intent.putExtras(bundle2)
            startActivity(intent)
        }
    }

    fun eliminarProyecto(){
        btEliminarProy.setOnClickListener() {
            if(copiaTextosTareas.size > 0){
                Toast.makeText(AppTFGLuismi.CONTEXT,"No se puede borrar el proyecto porque contine tareas ",Toast.LENGTH_SHORT).show()
            }else {
                val id = texto!!

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Confirmación")
                dialog.setMessage("¿Quieres borrar el proyecto?")
                dialog.setPositiveButton("Si") { dialogInterface, i ->
                    BdAdmin.removeProyecto(id)
                    finish()
                }
                dialog.setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                dialog.show()
                true
            }
        }
    }

    //-------------------------------------------------------TAREAS DE PROYECTOS-------------------------------------------------------

    fun crearTareas() {

        //Asignamos los valores de la base de datos a sus variables
        textoTareas = BdAdmin.getTareas()!!
        idsTareas = BdAdmin.getTareasId()!!
        fechasTareas = BdAdmin.getTareasFecha()!!
        horasTareas = BdAdmin.getTareasHora()!!
        imagenesTareas = BdAdmin.getTareasImagen()!!
        usuariosDelegadosTareas = BdAdmin.getTareasUsuarioDelegado()!!
        isDelegadasTareas = BdAdmin.getTareasIsDelegada()!!
        tipoListasTareas = BdAdmin.getTareasTipoLista()!!
        proyectosTareas = BdAdmin.getTareasProyecto()!!
        globalContext = this

        //Creamos copias de las variables anteriores para trabajar con ellas a la hora de selecionar un elemento de listView y asi
        //no tener fallos en los indices de las tareas para mandarlos por el bundel y filtrar correctamente en el listview
        copiaTextosTareas = textoTareas.clone() as ArrayList<String>
        copiaIdsTareas = idsTareas.clone() as ArrayList<Int>
        copiaFechasTareas = fechasTareas.clone() as ArrayList<String>
        copiaHorasTareas = horasTareas.clone() as ArrayList<String>
        copiaImagenesTareas = imagenesTareas.clone() as ArrayList<String>
        copiaUsuariosDelegadosTareas = usuariosDelegadosTareas.clone() as ArrayList<String>
        copiaIsDelegadasTareas = isDelegadasTareas.clone() as ArrayList<Int>
        copiaTipoListasTareas = tipoListasTareas.clone() as ArrayList<String>
        copiaProyectosTareas = proyectosTareas.clone() as ArrayList<String>

        //Filtramos las tareas que deben mostrarse (las cuales aun no hayan sido clasificadas)
        var size = tipoListasTareas.size
        if(size>=1) {
            size = tipoListasTareas.size - 1
            var aux = -1
            for(i in 0..size){
                if(tipoListasTareas.get(i) != "Proyectos" || proyectosTareas.get(i) != texto){
                    var cont = i
                    aux = aux + 1
                    copiaTextosTareas.removeAt(cont-aux)
                    copiaIdsTareas.removeAt(cont-aux)
                    copiaFechasTareas.removeAt(cont-aux)
                    copiaHorasTareas.removeAt(cont-aux)
                    copiaImagenesTareas.removeAt(cont - aux)
                    copiaUsuariosDelegadosTareas.removeAt(cont-aux)
                    copiaIsDelegadasTareas.removeAt(cont-aux)
                    copiaTipoListasTareas.removeAt(cont-aux)
                    copiaProyectosTareas.removeAt(cont-aux)
                }
            }
        }


        val adaptador = ArrayAdapter(globalContext, android.R.layout.simple_list_item_1, copiaTextosTareas!!)
        ListTareaProyectos.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListTareaProyectos.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, DetalleTarea::class.java)

            //Creamos un objeto Bundle para mandar datos a la activity DetalleTarea
            val bundle = Bundle()
            //Los objetos Bundle funcionan con pares clave ("itemId") - valor (ids!!.get(i))
            bundle.putInt("itemId",copiaIdsTareas!!.get(i))
            bundle.putString("itemTx",copiaTextosTareas!!.get(i))
            bundle.putString("itemF",copiaFechasTareas!!.get(i))
            bundle.putString("itemH",copiaHorasTareas!!.get(i))
            bundle.putString("itemIm",copiaImagenesTareas!!.get(i))
            bundle.putString("itemUD",copiaUsuariosDelegadosTareas!!.get(i))
            bundle.putInt("itemIsD",copiaIsDelegadasTareas!!.get(i))
            bundle.putString("itemTL",copiaTipoListasTareas!!.get(i))
            bundle.putString("itemP",copiaProyectosTareas!!.get(i))
            //Se añade el contenido del Bundle al intent
            intent.putExtras(bundle)

            startActivity(intent)
        }
    }

    fun eliminarTarea() {
        ListTareaProyectos.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

                val texto = copiaTextosTareas.get(i)
                globalContext = this
                val dialog = AlertDialog.Builder(globalContext)
                dialog.setTitle("Confirmación")
                dialog.setMessage("¿Quieres borrar la tarea?")
                dialog.setPositiveButton("Si") { dialogInterface, i ->
                    BdAdmin.removeTarea(texto)
                    crearTareas()
                }
                dialog.setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                dialog.show()
                true
            }
    }
}

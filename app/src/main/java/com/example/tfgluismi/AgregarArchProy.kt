package com.example.tfgluismi

import Data.AdminBD
import Data.ArchProy
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_agregar_arch_proy.*


class AgregarArchProy : AppCompatActivity() {

    val BdAdmin = AdminBD()
    var bundle = Bundle()
    var textoProy: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_arch_proy)
        guardarArchProy_click()

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment ProyectosFragment
        bundle= this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment ProyectosFragment
        textoProy=bundle.getString("itemTxt3")
        //Modificamos el cuadro de texto "descripcion" para que conserve el valor que ya tenía
        val plainTextProyId: EditText = findViewById(R.id.txProyectoId)
        plainTextProyId.setText((textoProy))
    }

    fun guardarArchProy_click(){
        buttonArchProy.setOnClickListener(){
            val archProy = ArchProy(0, txTextoAchProy.text.toString(), txProyectoId.text.toString())
            BdAdmin.addArchProy(archProy)
            finish()
        }
    }
}
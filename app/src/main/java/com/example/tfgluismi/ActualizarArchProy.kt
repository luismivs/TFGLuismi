package com.example.tfgluismi

import Data.AdminBD
import Data.ArchCon
import Data.ArchProy
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_actualizar_arch_con.*
import kotlinx.android.synthetic.main.activity_actualizar_arch_proy.*

class ActualizarArchProy: AppCompatActivity() {

    val BdAdmin = AdminBD()
    var bundle = Bundle()
    var ID: Int = 0
    var texto: String? = ""
    var proyecto: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_arch_proy)
        actualizarArchProy_click()

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment MotrarArchConFragment
        bundle= this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment MotrarArchConFragment
        ID=bundle.getInt("itemId")
        texto=bundle.getString("itemTx")
        proyecto=bundle.getString("itemProy")
        //Modificamos el cuadro de texto para que conserve el valor que ya tenía
        val plainTextTexto: EditText = findViewById(R.id.txActualizarTextoArchProy)
        plainTextTexto.setText(texto)
        val plainTextProy: EditText = findViewById(R.id.txActualizarProyectoArchProy)
        plainTextProy.setText(proyecto)
    }

    fun actualizarArchProy_click(){
        btActualizarArchProy.setOnClickListener(){

            val archProy = ArchProy(ID, txActualizarTextoArchProy.text.toString(), txActualizarProyectoArchProy.text.toString())
            BdAdmin.updateArchProy(archProy)
            finish()
        }
    }
}
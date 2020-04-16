package com.example.tfgluismi

import Data.AdminBD
import Data.Tarea
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_agregar_tarea.*

class AgregarTarea : AppCompatActivity() {

    val BdAdmin = AdminBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tarea)
        guardarTarea_click()
    }

    fun guardarTarea_click(){
        button.setOnClickListener(){
            val tarea = Tarea(0, txTexto.text.toString(), txFecha.text.toString(), txHora.text.toString(), txUsuarioDelegado.text.toString(),txLista.text.toString(), txProyectoDeTarea.text.toString())
            BdAdmin.addTarea(tarea)
            finish()
        }
    }
}

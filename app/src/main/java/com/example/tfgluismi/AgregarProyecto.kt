package com.example.tfgluismi

import Data.AdminBD
import Data.Proyecto
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_agregar_proyectos.*

class AgregarProyecto : AppCompatActivity() {

    val BdAdmin = AdminBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_proyectos)
            guardarProyecto_click()
    }

    fun guardarProyecto_click(){
        buttonProyectos.setOnClickListener(){
            //val proyecto = Proyecto(txTextoIdProyecto.id.toString(), txDescripcionProyecto.text.toString())
            val proyecto = Proyecto(0,txTextoProyecto.text.toString(), txDescripcionProyecto.text.toString())
            BdAdmin.addProyecto(proyecto)
            finish()
        }
    }
}
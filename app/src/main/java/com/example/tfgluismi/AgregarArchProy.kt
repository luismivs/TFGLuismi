package com.example.tfgluismi

import Data.AdminBD
import Data.ArchProy
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_agregar_arch_proy.*


class AgregarArchProy : AppCompatActivity() {

    val BdAdmin = AdminBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_arch_proy)
        guardarArchProy_click()
    }

    fun guardarArchProy_click(){
        buttonArchProy.setOnClickListener(){
            val archProy = ArchProy(0, txTextoAchProy.text.toString(), txProyectoId.text.toString())
            BdAdmin.addArchProy(archProy)
            finish()
        }
    }
}
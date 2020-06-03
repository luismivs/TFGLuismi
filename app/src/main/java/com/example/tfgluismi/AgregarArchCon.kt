package com.example.tfgluismi

import Data.AdminBD
import Data.ArchCon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_agregar_arch_con.*

class AgregarArchCon : AppCompatActivity() {

    val BdAdmin = AdminBD()
    var titulo: String = "Agregar nota"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_arch_con)
        this.setTitle(titulo)
        guardarArchCon_click()
    }

    fun guardarArchCon_click(){
        buttonArchCon.setOnClickListener(){
            val archCon = ArchCon(0, txTextoArchCon.text.toString())
            BdAdmin.addArchCon(archCon)
            finish()
        }
    }
}
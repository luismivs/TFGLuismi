package com.example.tfgluismi

import Data.AdminBD
import Data.ArchCon
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_actualizar_arch_con.*


class ActualizarArchCon: AppCompatActivity() {

    val BdAdmin = AdminBD()
    var bundle = Bundle()
    var ID: Int = 0
    var descripcion: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_arch_con)
        actualizarArchCon_click()

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment MotrarArchConFragment
        bundle= this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment MotrarArchConFragment
        ID=bundle.getInt("itemId")
        descripcion=bundle.getString("itemTx")
        //Modificamos el TextView del layout activity_actualizar_arch_con para que muestre los datos que hemos recibido por Bundle
        val textView: TextView = findViewById(R.id.textViewIdArchCon)
        textView.setText("ID: " + ID + "\r\nActual: " + descripcion)
        //Modificamos el cuadro de texto para que conserve el valor que ya tenía
        val plainText: EditText = findViewById(R.id.txActualizarArchCon)
        plainText.setText(descripcion)
    }

    fun actualizarArchCon_click(){
        btActualizarArchCon.setOnClickListener(){

            val archCon = ArchCon(ID, txActualizarArchCon.text.toString())
            BdAdmin.updateArchCon(archCon)
            finish()
        }
    }
}
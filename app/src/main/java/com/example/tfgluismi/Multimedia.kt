package com.example.tfgluismi

import Data.AdminBD

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Multimedia : AppCompatActivity() {

    val BdAdmin = AdminBD()
    var bundle = Bundle()
    lateinit var imagenIV: ImageView
    lateinit var path: Uri
    lateinit var imagenUri: Uri
    var Imagen: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia)

        imagenIV = findViewById(R.id.ivImage)

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment ClasificarTareasFragment
        bundle = this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment MotrarArchConFragment
        Imagen = bundle.getString("itemIm2")

        //Ponemos la imagen que trae la tarea, si tiene contenido
        if(Imagen != "") {
            imagenUri = Uri.parse(Imagen)
            imagenIV.setImageURI(imagenUri)
        }
    }
}
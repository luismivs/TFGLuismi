package com.example.tfgluismi.Fragments

import Data.AdminBD
import Data.AppTFGLuismi
import Data.Tarea
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tfgluismi.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File
import java.net.URI

class MainFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var tareasTipoLista: ArrayList<String>
    /*
    lateinit var imagen: ImageView
    val CARPETA_RAIZ: String = "imagenesOrganIce/"
    val RUTA_IMAGEN: String = CARPETA_RAIZ+"misFotos"
    var path: String = ""
    */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_home, container, false)
        //Calculamos y mostramos las tareas por clasificar y por atender
        tareasTipoLista = BdAdmin.getTareasTipoLista()!!
        var contClasificar: Int = 0
        var contAtender: Int = 0
        var size = tareasTipoLista.size-1
        for (i in 0..size){
            if(tareasTipoLista.get(i) != "" && tareasTipoLista.get(i) != "Seleccione"){
                contAtender = contAtender+1
            }
            if(tareasTipoLista.get(i).equals("") || tareasTipoLista.get(i).equals("Seleccione")){
                contClasificar = contClasificar+1
            }
        }
        val textViewCla: TextView = view.findViewById(R.id.txTareasPorClasificar)
        textViewCla.setText("Tareas pendientes de clasificar: " + contClasificar)
        val textViewAten: TextView = view.findViewById(R.id.txTareasPorAtender)
        textViewAten.setText("Tareas pendientes de atender: " + contAtender)


        //Tratamos la funcionalidad del boton de guardar la tarea
        val button: Button = view.findViewById(R.id.btGuardar)
        button.setOnClickListener(View.OnClickListener {
            val tarea = Tarea(0, txPrincipal.text.toString(), "", "", "", "", "","")
            if (tarea.texto != "") {
                BdAdmin.addTarea(tarea)
                txPrincipal.setText("")
                Toast.makeText(AppTFGLuismi.CONTEXT, "Tarea guardada", Toast.LENGTH_SHORT).show()
            }else Toast.makeText(AppTFGLuismi.CONTEXT, "Introduce una tarea", Toast.LENGTH_SHORT).show()
        })

        /*
        //Tratamos la funcionalidad del boton tomar foto
        val buttonFoto: Button = view.findViewById(R.id.btImagen)
        buttonFoto.setOnClickListener(View.OnClickListener {

            var fileImage = context?.getExternalFilesDir(RUTA_IMAGEN)
            var isCreada: Boolean = fileImage!!.exists()
            var nombreImagen: String = ""
            var bitmap: Bitmap

            if (isCreada == false){
                isCreada = fileImage.mkdirs()
            }
            if (isCreada == true){
                nombreImagen = (System.currentTimeMillis()/1000).toString() + ".jpg"
            }

            path = context?.getExternalFilesDir(RUTA_IMAGEN).toString() + File.separator + nombreImagen

            var img = File(path)

            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(img))
            startActivity(intent)

            bitmap = BitmapFactory.decodeFile(path)
            imagen.setImageBitmap(bitmap)

        })
         */

        return view
    }
}

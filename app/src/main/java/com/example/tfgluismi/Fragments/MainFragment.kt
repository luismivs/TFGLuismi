package com.example.tfgluismi.Fragments

import Data.AdminBD
import Data.AppTFGLuismi
import Data.Tarea
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tfgluismi.R
import kotlinx.android.synthetic.main.activity_home.*

class MainFragment : Fragment() {

    val BdAdmin = AdminBD()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
           // Inflate the layout for this fragment
           val view: View = inflater.inflate(R.layout.activity_home, container, false)
           val button: Button = view.findViewById(R.id.btGuardar)
           button.setOnClickListener(View.OnClickListener {
               val tarea = Tarea(0, txPrincipal.text.toString(), "", "", "", "", "")
               if (tarea.texto != "") {
                   BdAdmin.addTarea(tarea)
                   txPrincipal.setText("")
                   Toast.makeText(AppTFGLuismi.CONTEXT, "Tarea guardada", Toast.LENGTH_SHORT).show()
               }else Toast.makeText(AppTFGLuismi.CONTEXT, "Introduce una tarea", Toast.LENGTH_SHORT).show()
           })

           return view
    }
}

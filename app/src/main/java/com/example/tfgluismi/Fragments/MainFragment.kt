package com.example.tfgluismi.Fragments

import Data.AdminBD
import Data.Tarea
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tfgluismi.AgregarArchCon
import com.example.tfgluismi.AgregarTarea
import com.example.tfgluismi.R
import kotlinx.android.synthetic.main.activity_agregar_tarea.*

class MainFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var globalContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_principal, container, false)
        val button: Button = view.findViewById(R.id.btGuardar)
        button.setOnClickListener(View.OnClickListener {
            val tarea = Tarea(0, txTexto.text.toString(), txFecha.text.toString(), txHora.text.toString(), txUsuarioDelegado.text.toString(),txLista.text.toString(), txProyectoDeTarea.text.toString())
            BdAdmin.addTarea(tarea)
        })

        return view
    }
}

package com.example.tfgluismi.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tfgluismi.AgregarProyecto
import com.example.tfgluismi.R

class ProyectosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_mostrar_proyectos, container, false)

        val button: Button = view.findViewById(R.id.bt_add_proyecto)
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, AgregarProyecto::class.java)
            startActivity(intent)
        })
        return view
    }
}
package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val BdAdmin = AdminBD()
    lateinit var textos: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonMostrarTareas.setOnClickListener{
            val intent = Intent(this,MostrarTareas::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }
}

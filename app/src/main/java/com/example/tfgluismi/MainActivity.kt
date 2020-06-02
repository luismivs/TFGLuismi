package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.tfgluismi.Fragments.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView

    //Variables para cargar el fragment principal
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.navigationView)
        //establecer el evento onClick al navigationView
        navigationView.setNavigationItemSelectedListener(this)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled()
        actionBarDrawerToggle.syncState()

        //cargar fragment principal
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, MainFragment())
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId){
            R.id.homeMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, MainFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Principal")
            }
            R.id.clasificarTareasMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, ClasificarTareasFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Clasificar")
            }
            R.id.calendarioMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, CalendarioFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Programadas")
            }
            R.id.siguienteMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, SiguientesFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Cuando puedas")
            }
            R.id.delegadasMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, DelegadasFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Delegadas")
            }
            R.id.algunDiaMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, AlgunDiaFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Algun dia")
            }
            R.id.proyectosMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, ProyectosFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Proyectos")
            }
            R.id.archConMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, MostrarArchConFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Notas de consulta")
            }
            R.id.acercaDeMenuDrawer -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, AcercaDeFragment())
                fragmentTransaction.commit()
                toolbar.setTitle("OrganIce - Acerca de")
            }
        }
        return false
    }

}

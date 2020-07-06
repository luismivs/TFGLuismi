package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import Data.Tarea
import android.content.Context
import org.junit.After
import org.junit.Before
import java.io.IOException


class BaseDeDatosTest {
    private lateinit var tareas: ArrayList<String>
    private lateinit var db: AdminBD

   /* @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TestDatabase::class.java).build()
        tareas = db.getTareas()!!
    }*/

}
package com.example.tfgluismi

import Data.ArchCon
import Data.ArchProy
import Data.Proyecto
import Data.Tarea
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ExampleUnitTest {
    private lateinit var tarea: Tarea
    private lateinit var archCon: ArchCon
    private lateinit var proy: Proyecto
    private lateinit var archProy: ArchProy

    @Before
    fun createTarea() {
       tarea = Tarea (0,"Tarea 1","","","","",0,"","")
    }

    @Before
    fun createArchCon(){
        archCon = ArchCon(0,"Archivo de consulta 1")
    }

    @Before
    fun createProy(){
        proy = Proyecto(0,"Proyecto 1", "")
    }

    @Before
    fun createArchProy(){
        archProy = ArchProy(0, "Archivo de proyecto 1", "Proyecto 1")
    }

    @Test
    fun compare_tarea(){
        assertEquals(tarea,Tarea (0,"Tarea 1","","","","",0,"",""))
    }

    @Test
    fun compare_archCon(){
        assertEquals(archCon,ArchCon(0,"Archivo de consulta 1"))
    }

    @Test
    fun compare_proy(){
        assertEquals(proy,Proyecto(0,"Proyecto 1", ""))
    }

    @Test
    fun compare_ArchProy(){
        assertEquals(archProy,ArchProy(0, "Archivo de proyecto 1", "Proyecto 1"))
    }
}

package Data

import android.app.Application
import android.content.Context

class AppTFGLuismi: Application() {

    companion object{
        lateinit var CONTEXT: Context
        lateinit var DB: InitDb
        val DB_NAME = "TFGLuismiDB.db"
        val VERSION = 4
        val TB_TAREAS = "tbTareas"
        val TB_PROYECTOS = "tbProyectos"
        val TB_ARCHIVOSDEPROYECTOS = "tbAchProyectos"
        val TB_ARCHIVOSDECONSULTA = "tbArchConsulta"
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        DB = InitDb()
    }
}
package Data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class InitDb: SQLiteOpenHelper(AppTFGLuismi.CONTEXT, AppTFGLuismi.DB_NAME, null, AppTFGLuismi.VERSION) {

    val qryCreateTablaTareas = "CREATE TABLE ${AppTFGLuismi.TB_TAREAS}(" +
            "${Contract.Tarea.tareaID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Contract.Tarea.TEXTO} TEXT NOT NULL," +
            "${Contract.Tarea.FECHA} TEXT," +
            "${Contract.Tarea.HORA} TEXT," +
            "${Contract.Tarea.IMAGEN} TEXT," +
            "${Contract.Tarea.AUDIO} TEXT," +
            "${Contract.Tarea.USUARIO_DELEGADO} TEXT," +
            "${Contract.Tarea.PROYECTO} TEXT," +
            "${Contract.Tarea.TIPO_LISTA} TEXT," +
            "FOREIGN KEY(${Contract.Tarea.PROYECTO}) REFERENCES ${AppTFGLuismi.TB_PROYECTOS}(${Contract.Proyectos.ProyectoID}));"

    val qryCreateTablaProyectos = "CREATE TABLE ${AppTFGLuismi.TB_PROYECTOS}(" +
            "${Contract.Proyectos.ProyectoID} TEXT PRIMARY KEY," +
            "${Contract.Proyectos.DESCRIPCION} TEXT);"

    val qryCreateTablaArchProyecto = "CREATE TABLE ${AppTFGLuismi.TB_ARCHIVOSDEPROYECTOS}(" +
            "${Contract.ArchProyectos.ArchProyID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Contract.ArchProyectos.texto} TEXT NOT NULL," +
            "${Contract.ArchProyectos.PROYECTO} TEXT NOT NULL," +
            "FOREIGN KEY(${Contract.ArchProyectos.PROYECTO}) REFERENCES ${AppTFGLuismi.TB_PROYECTOS}(${Contract.Proyectos.ProyectoID}));"

    val qryCreateTablaArchConsulta = "CREATE TABLE ${AppTFGLuismi.TB_ARCHIVOSDECONSULTA}(" +
            "${Contract.ArchConsulta.ArchConID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Contract.ArchConsulta.texto} TEXT);"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(qryCreateTablaTareas)
        db!!.execSQL(qryCreateTablaProyectos)
        db!!.execSQL(qryCreateTablaArchProyecto)
        db!!.execSQL(qryCreateTablaArchConsulta)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}
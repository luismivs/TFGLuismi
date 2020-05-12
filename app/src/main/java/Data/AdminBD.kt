package Data

import android.database.DatabaseUtils
import android.widget.Toast
import android.widget.Toast.makeText
import java.lang.Exception

data class Tarea(var tareaid:Int, var texto:String, var fecha:String, var hora:String, var usuarioDelegado:String, var tipoLista: String, var proyecto: String)
data class ArchCon(var id: Int, var texto:String)
data class Proyecto(var id: String, var descripcion: String)
data class ArchProy(var id: Int, var texto:String, var proyecto: String)

class AdminBD {

//--------------------------------------------GETERS----------------------------------------------------------------------------------

    fun getTareas():ArrayList<String>?{
        try {
            val textos = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay tareas guardadas
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT ${Contract.Tarea.TEXTO} FROM ${AppTFGLuismi.TB_TAREAS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    textos.add(c.getString(c.getColumnIndex(Contract.Tarea.TEXTO)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay tareas guardadas", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return textos
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar las tareas", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getTareasId():ArrayList<Int>?{
        try {
            val ids = arrayListOf<Int>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay tareas guardadas
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT ${Contract.Tarea.tareaID} FROM ${AppTFGLuismi.TB_TAREAS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    ids.add(c.getInt(c.getColumnIndex(Contract.Tarea.tareaID)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay tareas guardadas", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return ids
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar las tareas", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getTareasFecha():ArrayList<String>?{
        try {
            val fechas = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay tareas guardadas
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT ${Contract.Tarea.FECHA} FROM ${AppTFGLuismi.TB_TAREAS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    fechas.add(c.getString(c.getColumnIndex(Contract.Tarea.FECHA)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay tareas guardadas", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return fechas
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar las tareas", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getTareasHora():ArrayList<String>?{
        try {
            val horas = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay tareas guardadas
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT ${Contract.Tarea.HORA} FROM ${AppTFGLuismi.TB_TAREAS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    horas.add(c.getString(c.getColumnIndex(Contract.Tarea.HORA)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay tareas guardadas", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return horas
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar las tareas", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getTareasUsuarioDelegado():ArrayList<String>?{
        try {
            val usuarios = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay tareas guardadas
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT ${Contract.Tarea.USUARIO_DELEGADO} FROM ${AppTFGLuismi.TB_TAREAS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    usuarios.add(c.getString(c.getColumnIndex(Contract.Tarea.USUARIO_DELEGADO)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay tareas guardadas", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return usuarios
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar las tareas", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getTareasTipoLista():ArrayList<String>?{
        try {
            val listas = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay tareas guardadas
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT ${Contract.Tarea.TIPO_LISTA} FROM ${AppTFGLuismi.TB_TAREAS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    listas.add(c.getString(c.getColumnIndex(Contract.Tarea.TIPO_LISTA)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay tareas guardadas", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return listas
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar las tareas", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getTareasProyecto():ArrayList<String>?{
        try {
            val proyectos = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay tareas guardadas
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT ${Contract.Tarea.PROYECTO} FROM ${AppTFGLuismi.TB_TAREAS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    proyectos.add(c.getString(c.getColumnIndex(Contract.Tarea.PROYECTO)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay tareas guardadas", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return proyectos
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar las tareas", Toast.LENGTH_SHORT).show()
            return null
        }
    }
    fun getTareasFull():ArrayList<Tarea>?{
        try {
            val tareas = arrayListOf<Tarea>()
            val ids = arrayListOf<Int>()
            val textos = arrayListOf<String>()
            val fechas = arrayListOf<String>()
            val horas = arrayListOf<String>()
            val usuariosDelegados = arrayListOf<String>()
            val tipoListas = arrayListOf<String>()
            val pro = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay Archivos de consulta guardados
            val numTareas = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_TAREAS).toInt()
            if (numTareas > 0){
                val qry = "SELECT * FROM ${AppTFGLuismi.TB_TAREAS}"
                val cursor = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                cursor.moveToFirst()
                do{
                    ids.add(cursor.getInt(cursor.getColumnIndex(Contract.Tarea.tareaID)))
                    textos.add(cursor.getString(cursor.getColumnIndex(Contract.Tarea.TEXTO)))
                    fechas.add(cursor.getString(cursor.getColumnIndex(Contract.Tarea.FECHA)))
                    horas.add(cursor.getString(cursor.getColumnIndex(Contract.Tarea.HORA)))
                    usuariosDelegados.add(cursor.getString(cursor.getColumnIndex(Contract.Tarea.USUARIO_DELEGADO)))
                    tipoListas.add(cursor.getString(cursor.getColumnIndex(Contract.Tarea.TIPO_LISTA)))
                    pro.add(cursor.getString(cursor.getColumnIndex(Contract.Tarea.PROYECTO)))
                }while (cursor.moveToNext())
                for(i in 0 .. ids.size){
                    var tarea = Tarea(ids.get(i),textos.get(i),fechas.get(i),horas.get(i), usuariosDelegados.get(i), tipoListas.get(i), pro.get(i))
                    tareas.add(tarea)
                }
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay archivos de consulta guardados", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return tareas
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar los archivos de consulta", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getArchCon():ArrayList<String>?{
        try {
            val textos = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay Archivos de consulta guardados
            val numArchCon = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_ARCHIVOSDECONSULTA).toInt()
            if (numArchCon > 0){
                val qry = "SELECT ${Contract.ArchConsulta.texto} FROM ${AppTFGLuismi.TB_ARCHIVOSDECONSULTA}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    textos.add(c.getString(c.getColumnIndex(Contract.ArchConsulta.texto)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay archivos de consulta guardados", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return textos
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar los archivos de consulta", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getArchConId():ArrayList<Int>?{
        try {
            val ids = arrayListOf<Int>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay Archivos de consulta guardados
            val numArchCon = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_ARCHIVOSDECONSULTA).toInt()
            if (numArchCon > 0){
                val qry = "SELECT ${Contract.ArchConsulta.ArchConID} FROM ${AppTFGLuismi.TB_ARCHIVOSDECONSULTA}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    ids.add(c.getInt(c.getColumnIndex(Contract.ArchConsulta.ArchConID)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay archivos de consulta guardados", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return ids
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar los archivos de consulta", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    //Lo dejo como ejemplo para ver como se hace
   /* fun getArchConFull():ArrayList<ArchCon>?{
        try {
            val archivos = arrayListOf<ArchCon>()
            val ids = arrayListOf<Int>()
            val textos = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay Archivos de consulta guardados
            val numArchCon = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_ARCHIVOSDECONSULTA).toInt()
            if (numArchCon > 0){
                val qry = "SELECT * FROM ${AppTFGLuismi.TB_ARCHIVOSDECONSULTA}"
                val cursor = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                cursor.moveToFirst()
                do{
                    ids.add(cursor.getInt(cursor.getColumnIndex(Contract.ArchConsulta.ArchConID)))
                    textos.add(cursor.getString(cursor.getColumnIndex(Contract.ArchConsulta.texto)))
                }while (cursor.moveToNext())
                for(i in 0 .. ids.size){
                    var archcon = ArchCon(ids.get(i),textos.get(i))
                    archivos.add(archcon)
                }
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay archivos de consulta guardados", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return archivos
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar los archivos de consulta", Toast.LENGTH_SHORT).show()
            return null
        }
    }*/

    fun getProyecto():ArrayList<String>?{
        try {
            val textosid = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay proyectos guardados
            val numProyectos = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_PROYECTOS).toInt()
            if (numProyectos > 0){
                val qry = "SELECT ${Contract.Proyectos.ProyectoID} FROM ${AppTFGLuismi.TB_PROYECTOS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    textosid.add(c.getString(c.getColumnIndex(Contract.Proyectos.ProyectoID)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay proyectos guardados", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return textosid
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar los proyectos", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getArchProy():ArrayList<String>?{
        try {
            val textos = arrayListOf<String>()
            val db = AppTFGLuismi.DB.readableDatabase
            //Comprobamos si hay Archivos de proyecto guardados
            val numArchProy = DatabaseUtils.queryNumEntries(db,AppTFGLuismi.TB_ARCHIVOSDEPROYECTOS).toInt()
            if (numArchProy > 0){
                val qry = "SELECT ${Contract.ArchProyectos.texto} FROM ${AppTFGLuismi.TB_ARCHIVOSDEPROYECTOS}"
                val c = db.rawQuery(qry,null)
                //vamos al inicio de la tabla
                c.moveToFirst()
                do{
                    textos.add(c.getString(c.getColumnIndex(Contract.ArchProyectos.texto)))
                }while (c.moveToNext())
            }else{
                makeText(AppTFGLuismi.CONTEXT,"No hay archivos de proyectos guardados", Toast.LENGTH_SHORT).show()
            }
            db.close()
            return textos
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudieron mostrar los archivos de proyectos", Toast.LENGTH_SHORT).show()
            return null
        }
    }
//---------------------------------------------------------------ADDS-------------------------------------------------------------------

    fun addTarea(tarea: Tarea){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "INSERT INTO ${AppTFGLuismi.TB_TAREAS} (" +
                    "${Contract.Tarea.TEXTO}, ${Contract.Tarea.FECHA}, ${Contract.Tarea.HORA}, ${Contract.Tarea.USUARIO_DELEGADO}, ${Contract.Tarea.TIPO_LISTA}, ${Contract.Tarea.PROYECTO})" +
                    "VALUES('${tarea.texto}','${tarea.fecha}','${tarea.hora}','${tarea.usuarioDelegado}','${tarea.tipoLista}','${tarea.proyecto}');"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se guardó la tarea", Toast.LENGTH_SHORT).show()
        }
    }

    fun addArchCon(archCon: ArchCon){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "INSERT INTO ${AppTFGLuismi.TB_ARCHIVOSDECONSULTA} (" +
                    "${Contract.ArchConsulta.texto})" +
                    "VALUES('${archCon.texto}');"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se guardó el archivo de consulta", Toast.LENGTH_SHORT).show()
        }
    }

    fun addProyecto(proyecto: Proyecto){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "INSERT INTO ${AppTFGLuismi.TB_PROYECTOS} (" +
                    "${Contract.Proyectos.ProyectoID},${Contract.Proyectos.DESCRIPCION})" +
                    "VALUES('${proyecto.id}','${proyecto.descripcion}');"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se guardó el proyecto", Toast.LENGTH_SHORT).show()
        }
    }

    fun addArchProy(archProy: ArchProy){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "INSERT INTO ${AppTFGLuismi.TB_ARCHIVOSDEPROYECTOS} (" +
                    "${Contract.ArchProyectos.texto},${Contract.ArchProyectos.PROYECTO})" +
                    "VALUES('${archProy.texto}','${archProy.proyecto}');"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se guardó el archivo de proyecto", Toast.LENGTH_SHORT).show()
        }
    }

//---------------------------------------------------REMOVES-----------------------------------------------------------------------------

    fun removeTarea(texto: String){
        try{
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "DELETE FROM ${AppTFGLuismi.TB_TAREAS} WHERE ${Contract.Tarea.TEXTO} = '$texto';"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se eliminó la tarea", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeArchCon(texto: String){
        try{
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "DELETE FROM ${AppTFGLuismi.TB_ARCHIVOSDECONSULTA} WHERE ${Contract.ArchConsulta.texto} = '$texto';"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se eliminó el archivo de consulta", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeProyecto(id: String){
        try{
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "DELETE FROM ${AppTFGLuismi.TB_PROYECTOS} WHERE ${Contract.Proyectos.ProyectoID} = '$id';"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se eliminó el proyecto", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeArchProy(texto: String){
        try{
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "DELETE FROM ${AppTFGLuismi.TB_ARCHIVOSDEPROYECTOS} WHERE ${Contract.ArchProyectos.texto} = '$texto';"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se eliminó el archivo de proyecto", Toast.LENGTH_SHORT).show()
        }
    }

//---------------------------------------------------UPDATES-----------------------------------------------------------------------------

    fun updateTarea(tarea: Tarea){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "UPDATE ${AppTFGLuismi.TB_TAREAS} " +
                      "SET ${Contract.Tarea.TEXTO} = '${tarea.texto}', " +
                        "${Contract.Tarea.FECHA} = '${tarea.fecha}', " +
                        "${Contract.Tarea.HORA} = '${tarea.hora}', " +
                        "${Contract.Tarea.USUARIO_DELEGADO} = '${tarea.usuarioDelegado}', " +
                        "${Contract.Tarea.TIPO_LISTA} = '${tarea.tipoLista}', " +
                        "${Contract.Tarea.PROYECTO} = '${tarea.proyecto}' " +
                      "WHERE ${Contract.Tarea.tareaID} = '${tarea.tareaid}';"
            db.execSQL(qry)
            db.close()
            makeText(AppTFGLuismi.CONTEXT,"La tarea se actualizo correctamente", Toast.LENGTH_SHORT).show()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudo actualizar la tarea", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateArchCon(archCon: ArchCon){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "UPDATE ${AppTFGLuismi.TB_ARCHIVOSDECONSULTA} " +
                    "SET ${Contract.ArchConsulta.texto} = '${archCon.texto}' " +
                    "WHERE ${Contract.ArchConsulta.ArchConID} = '${archCon.id}';"
            db.execSQL(qry)
            db.close()
            makeText(AppTFGLuismi.CONTEXT,"El archivo de consulta se actualizo correctamente", Toast.LENGTH_SHORT).show()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudo actualizar el archivo de consulta", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateProyecto(proyecto: Proyecto){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "UPDATE ${AppTFGLuismi.TB_PROYECTOS} " +
                    "SET ${Contract.Proyectos.DESCRIPCION} = '${proyecto.descripcion}' " +
                    "WHERE ${Contract.Proyectos.ProyectoID} = '${proyecto.id}';"
            db.execSQL(qry)
            db.close()
            makeText(AppTFGLuismi.CONTEXT,"El proyecto se actualizo correctamente", Toast.LENGTH_SHORT).show()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudo actualizar el proyecto", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateArchProy(archProy: ArchProy){
        try {
            val db = AppTFGLuismi.DB.writableDatabase
            var qry = "UPDATE ${AppTFGLuismi.TB_ARCHIVOSDEPROYECTOS} " +
                    "SET ${Contract.ArchProyectos.texto} = '${archProy.texto}', " +
                        "${Contract.ArchProyectos.PROYECTO} = '${archProy.proyecto}' " +
                    "WHERE ${Contract.ArchProyectos.ArchProyID} = '${archProy.id}';"
            db.execSQL(qry)
            db.close()
            makeText(AppTFGLuismi.CONTEXT,"El archivo de proyecto se actualizo correctamente", Toast.LENGTH_SHORT).show()
        }catch (ex:Exception){
            makeText(AppTFGLuismi.CONTEXT,"No se pudo actualizar el archivo de proyecto", Toast.LENGTH_SHORT).show()
        }
    }

}

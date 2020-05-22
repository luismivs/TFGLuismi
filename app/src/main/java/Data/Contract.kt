package Data

import android.provider.BaseColumns

class Contract {

    //Definimos los campos de las tablas

    class Tarea: BaseColumns{
        companion object {
            var tareaID = "tareaid"
            var TEXTO = "texto"
            var IMAGEN = "imagen"
            var AUDIO = "audio"
            var FECHA = "fecha"
            var HORA = "hora"
            var USUARIO_DELEGADO = "usuarioDelegado"
            var IS_DELEGADA = "isDelegada"
            var PROYECTO = "proyecto"
            var TIPO_LISTA = "tipoLista"
        }
    }

    class Proyectos: BaseColumns{
        companion object{
            var ProyectoID = "id"
            var TEXTO = "texto"
            var DESCRIPCION = "descripcion"
        }
    }

    class ArchProyectos: BaseColumns{
        companion object{
            var ArchProyID = "id"
            var texto = "texto"
            var PROYECTO = "proyecto"
        }
    }

    class ArchConsulta: BaseColumns{
        companion object{
            var ArchConID = "id"
            var texto = "texto"
        }
    }
}
package com.example.tfgluismi

import Data.AdminBD
import Data.Tarea
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detalles_tarea.*
import kotlinx.android.synthetic.main.activity_detalles_tarea_delegar.*
import java.util.*
import kotlin.collections.ArrayList

class DetalleTareaDelegar : AppCompatActivity(), View.OnClickListener {

    val BdAdmin = AdminBD()
    var titulo: String = "Resumen de tarea"
    var bundle = Bundle()
    var ID: Int = 0
    var Texto: String? = ""
    var Fecha: String? = ""
    var Hora: String? = ""
    var Imagen: String? = ""
    var UsuarioDelegado: String? = ""
    var IsDelegada: Int = 0
    var TipoLista: String? = ""
    var Proyecto: String? = ""
    var Seleccion: String? = ""
    var SeleccionProy: String? = ""
    lateinit var plainTextFecha: EditText
    lateinit var plainTextHora: EditText
    var dias: Int = 0;
    var meses: Int = 0;
    var anos: Int = 0;
    var horas: Int = 0;
    var minutos: Int = 0
    val COD_GALERIA: Int = 10
    lateinit var path: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_tarea_delegar)
        this.setTitle(titulo)
        guardarTarea_click()
        irAMultimedia_click()
        añadirImagen_click()

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment ClasificarTareasFragment
        bundle = this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment MotrarArchConFragment
        ID = bundle.getInt("itemId")
        Texto = bundle.getString("itemTx")
        Fecha = bundle.getString("itemF")
        Hora = bundle.getString("itemH")
        Imagen = bundle.getString("itemIm")
        Imagen = bundle.getString("itemIm")
        UsuarioDelegado = bundle.getString("itemUD")
        IsDelegada = bundle.getInt("itemIsD")
        TipoLista = bundle.getString("itemTL")
        Proyecto = bundle.getString("itemP")

        //Modificamos el TextView del layout activity_detalles_tarea para que muestre los datos que hemos recibido por Bundle
        //val textView: TextView = findViewById(R.id.textViewIdTareasDel)
        //textView.setText("ID: " + ID)
        //Modificamos los cuadros de texto para que conserven el valor que ya tenían
        val plainTextTexto: EditText = findViewById(R.id.txTextoDel)
        plainTextTexto.setText(Texto)
        val textViewTL: TextView = findViewById(R.id.txtTipoListaDel)
        if (TipoLista != "Seleccione" && TipoLista != "") {
            textViewTL.setText(TipoLista)
        }
        if (TipoLista.equals("Seleccione") || TipoLista.equals("")){
            textViewTL.setText("Lista: -")
        }

        //Tratamiento de los campos fecha y hora
        plainTextFecha = findViewById(R.id.txFechaDel)
        val buttonFecha: Button = findViewById(R.id.btFechaDel)
        buttonFecha.setOnClickListener(this)
        plainTextFecha.setText((Fecha))

        plainTextHora = findViewById(R.id.txHoraDel)
        val buttonHora: Button = findViewById(R.id.btHoraDel)
        buttonHora.setOnClickListener(this)
        plainTextHora.setText(Hora)

        //Tratamiento de funcion delegacion de tareas
        val plainTextUD: EditText = findViewById(R.id.txUsuarioDelegadoDel)
        plainTextUD.setText(UsuarioDelegado)

        val confirmarDelegacion: RadioButton = findViewById(R.id.rbIsDelegado)
        if(IsDelegada == 1){
            confirmarDelegacion.isChecked = true
        }
        val btDelegar: Button = findViewById(R.id.btDelegarDel)
        btDelegar.setOnClickListener {
            if(IsDelegada == 0) {
                confirmarDelegacion.isChecked = true
                IsDelegada = 1
            }
            val intent = Intent()
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Tarea: " + Texto + "\r\nFecha y hora: " + Fecha + " | " + Hora)
            intent.putExtra(Intent.EXTRA_SUBJECT,"Tarea enviada desde Organ-Ice")
            intent.action = Intent.ACTION_SEND


            val chooseIntent = Intent.createChooser(intent, "Elige una opcion")
            startActivity(chooseIntent)
        }

        //Tratamiento del spinner para insertar el tipo de lista
        val spinnerTL: Spinner = findViewById(R.id.SpinnerListaDel)
        ArrayAdapter.createFromResource(
            this,
            R.array.nombreListas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTL.adapter = adapter
        }
        spinnerTL.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Seleccion = selectedItem
            }

        }

        //Tratamiento del spinner para insertar nombre de proyecto
        var nomProyBD: ArrayList<String>
        var nomProy: ArrayList<String>
        nomProyBD = BdAdmin.getProyecto()!!
        nomProy = nomProyBD.clone() as ArrayList<String>
        nomProy.clear()
        nomProy.add("Seleccione")
        var size = nomProyBD.size-1
        for (i in 0..size){
            nomProy.add(nomProyBD.get(i))
        }

        val spinnerProy: Spinner = findViewById(R.id.spinnerProyectoDel)
        ArrayAdapter(this,android.R.layout.simple_spinner_item, nomProy).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerProy.adapter = adapter
        }

        spinnerProy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                SeleccionProy = selectedItem
            }

        }

        val textViewP: TextView = findViewById(R.id.twProyectoDeTareaDel)
        if (Proyecto != "Seleccione") {
            textViewP.setText("Proyecto: " + Proyecto)
        } else textViewP.setText("Proyecto: ")
    }

    fun guardarTarea_click() {
        btActualizarTareaDel.setOnClickListener() {
            if (Seleccion.equals("Seleccione") && SeleccionProy.equals("Seleccione")) {
                val tarea = Tarea(
                    ID,
                    txTextoDel.text.toString(),
                    txFechaDel.text.toString(),
                    txHoraDel.text.toString(),
                    Imagen.toString(),
                    txUsuarioDelegadoDel.text.toString(),
                    IsDelegada,
                    TipoLista.toString(),
                    Proyecto.toString()
                )
                BdAdmin.updateTarea(tarea)

            } else if (Seleccion != "Seleccione" && SeleccionProy.equals("Seleccione")) {
                val tarea = Tarea(
                    ID,
                    txTextoDel.text.toString(),
                    txFechaDel.text.toString(),
                    txHoraDel.text.toString(),
                    Imagen.toString(),
                    txUsuarioDelegadoDel.text.toString(),
                    IsDelegada,
                    Seleccion.toString(),
                    Proyecto.toString()
                )
                BdAdmin.updateTarea(tarea)

            } else if(Seleccion.equals("Seleccione") && SeleccionProy != "Seleccione") {
                val tarea = Tarea(
                    ID,
                    txTextoDel.text.toString(),
                    txFechaDel.text.toString(),
                    txHoraDel.text.toString(),
                    Imagen.toString(),
                    txUsuarioDelegadoDel.text.toString(),
                    IsDelegada,
                    TipoLista.toString(),
                    SeleccionProy.toString()
                )
                BdAdmin.updateTarea(tarea)

            } else {
                val tarea = Tarea(
                    ID,
                    txTextoDel.text.toString(),
                    txFechaDel.text.toString(),
                    txHoraDel.text.toString(),
                    Imagen.toString(),
                    txUsuarioDelegadoDel.text.toString(),
                    IsDelegada,
                    Seleccion.toString(),
                    SeleccionProy.toString()
                )
                BdAdmin.updateTarea(tarea)
            }
            finish()
        }
    }

    //Metodo para ir a multimedia
    fun irAMultimedia_click(){
        btMultimediaDel.setOnClickListener(){
            val intent = Intent(this, Multimedia::class.java)

            //Creamos un objeto Bundle para mandar datos a la activity Multimedia
            val bundle = Bundle()
            bundle.putString("itemIm2",Imagen)

            intent.putExtras(bundle)

            startActivity(intent)
        }
    }

    //Metodo para añadir una imagen de la galeria a una tarea
    fun añadirImagen_click(){
        btAddImageDel.setOnClickListener(){
            cargarImagen()
        }
    }

    private fun cargarImagen() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, COD_GALERIA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==RESULT_OK){
            path = data!!.data!!
            Imagen = path.toString()
        }
    }

    //Metodo onClick de los botones de insercion de fecha y hora
    override fun onClick(v: View?) {
        if (v == btFechaDel) {
            var c: Calendar = Calendar.getInstance()
            dias = c.get(Calendar.DAY_OF_MONTH)
            meses = c.get(Calendar.MONTH)
            anos = c.get(Calendar.YEAR)

            var datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    plainTextFecha.setText("" + dayOfMonth + "/" + (monthOfYear+1) + "/" + year)
                },
                anos,
                meses,
                dias
            )
            datePickerDialog.show()
        }

        if (v == btHoraDel) {
            var c: Calendar = Calendar.getInstance()
            horas = c.get(Calendar.HOUR_OF_DAY)
            minutos = c.get(Calendar.MINUTE)

            var timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hour, minute ->

                    // Display Selected date in textbox
                    plainTextHora.setText("" + hour + ":" + minute)

                },
                horas,
                minutos,
                true
            )
            timePickerDialog.show()
        }
    }
}
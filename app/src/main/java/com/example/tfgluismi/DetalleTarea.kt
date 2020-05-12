package com.example.tfgluismi

import Data.AdminBD
import Data.AppTFGLuismi
import Data.Tarea
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_detalles_tarea.*
import java.util.*

class DetalleTarea : AppCompatActivity(), View.OnClickListener {

    val BdAdmin = AdminBD()
    var bundle = Bundle()
    var ID: Int = 0
    var Texto: String? = ""
    var Fecha: String? = ""
    var Hora: String? = ""
    var UsuarioDelegado: String? = ""
    var TipoLista: String? = ""
    var Proyecto: String? = ""
    var Seleccion: String? = ""
    lateinit var plainTextFecha: EditText
    lateinit var plainTextHora: EditText
    var dias: Int = 0;
    var meses: Int = 0;
    var anos: Int = 0;
    var horas: Int = 0;
    var minutos: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_tarea)
        guardarTarea_click()

        //Almacenamos los datos que hemos enviado mediante un Bundle atraves del intent del fragment ClasificarTareasFragment
        bundle = this.intent.extras!!
        //Utilizamos la Clave ("ItemId") ya que es la que hemos especificado en el fragment MotrarArchConFragment
        ID = bundle.getInt("itemId")
        Texto = bundle.getString("itemTx")
        Fecha = bundle.getString("itemF")
        Hora = bundle.getString("itemH")
        UsuarioDelegado = bundle.getString("itemUD")
        TipoLista = bundle.getString("itemTL")
        Proyecto = bundle.getString("itemP")

        //Modificamos el TextView del layout activity_detalles_tarea para que muestre los datos que hemos recibido por Bundle
        val textView: TextView = findViewById(R.id.textViewIdTareas)
        textView.setText("ID: " + ID)
        //Modificamos los cuadros de texto para que conserven el valor que ya tenían
        val plainTextTexto: EditText = findViewById(R.id.txTexto)
        plainTextTexto.setText(Texto)
        val textViewTL: TextView = findViewById(R.id.txtTipoLista)
        if (TipoLista != "Seleccione") {
            textViewTL.setText("Tipo de lista: " + TipoLista)
        } else textViewTL.setText("Tipo de lista: ")

        //Tratamiento de los campos fecha y hora
        plainTextFecha = findViewById(R.id.txFecha)
        val buttonFecha: Button = findViewById(R.id.btFecha)
        buttonFecha.setOnClickListener(this)
        plainTextFecha.setText((Fecha))

        plainTextHora = findViewById(R.id.txHora)
        val buttonHora: Button = findViewById(R.id.btHora)
        buttonHora.setOnClickListener(this)
        plainTextHora.setText(Hora)


        val plainTextUD: EditText = findViewById(R.id.txUsuarioDelegado)
        plainTextUD.setText(UsuarioDelegado)

        //Tratamiento del spinner para insertar el tipo de lista
        val spinnerTL: Spinner = findViewById(R.id.SpinnerLista)
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

        val plainTextProyecto: EditText = findViewById(R.id.txProyectoDeTarea)
        plainTextProyecto.setText(Proyecto)
    }

    fun guardarTarea_click() {
        btActualizarTarea.setOnClickListener() {
            if (Seleccion.equals("Seleccione")) {
                val tarea = Tarea(
                    ID,
                    txTexto.text.toString(),
                    txFecha.text.toString(),
                    txHora.text.toString(),
                    txUsuarioDelegado.text.toString(),
                    TipoLista.toString(),
                    txProyectoDeTarea.text.toString()
                )
                BdAdmin.updateTarea(tarea)
            } else {
                val tarea = Tarea(
                    ID,
                    txTexto.text.toString(),
                    txFecha.text.toString(),
                    txHora.text.toString(),
                    txUsuarioDelegado.text.toString(),
                    Seleccion.toString(),
                    txProyectoDeTarea.text.toString()
                )
                BdAdmin.updateTarea(tarea)
            }
            finish()
        }
    }

    //Metodo onClick de los botones de insercion de fecha y hora
    override fun onClick(v: View?) {
        if (v == btFecha) {
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

        if (v == btHora) {
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

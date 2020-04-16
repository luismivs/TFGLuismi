package com.example.tfgluismi.Fragments

import Data.AdminBD
import Data.AppTFGLuismi
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tfgluismi.AgregarArchCon
import com.example.tfgluismi.MostrarArchCon
import com.example.tfgluismi.MostrarProyectos
import com.example.tfgluismi.R
import kotlinx.android.synthetic.main.activity_mostrar_arch_con.*
import kotlinx.android.synthetic.main.activity_mostrar_arch_con.view.*




class MostrarArchConFragment : Fragment() {

    val BdAdmin = AdminBD()
    lateinit var textos: ArrayList<String>
    lateinit var globalContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_mostrar_arch_con, container, false)

        val button: Button = view.findViewById(R.id.bt_add_ac)
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, AgregarArchCon::class.java)
            startActivity(intent)
        })
        return view
    }

    override fun onStart() {
        super.onStart()
        crearArchCon()
        eliminarArchCon()
    }

    //-------------------------------------------------------CREATES-----------------------------------------------------------------------------

    fun crearArchCon(){
        textos = BdAdmin.getArchCon()!!
        globalContext = this.getActivity()!!
        val adaptador = ArrayAdapter(globalContext,android.R.layout.simple_list_item_1,textos!!)
        ListArchCon.adapter = adaptador

        //Al seleccionar un elemento de la lista
        ListArchCon.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            val item = textos!!.get(i)
            Toast.makeText(AppTFGLuismi.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }
    }

    //----------------------------------------------------------DELETES-------------------------------------------------------------------

    fun eliminarArchCon(){
        ListArchCon.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val texto = textos.get(i)
            globalContext = this.getActivity()!!
            val dialog = AlertDialog.Builder(globalContext)
            dialog.setTitle("Confirmación")
            dialog.setMessage("¿Quieres borrar el archivo de consulta?")
            dialog.setPositiveButton("Si"){dialogInterface, i ->
                BdAdmin.removeArchCon(texto)
                crearArchCon()
            }
            dialog.setNegativeButton("No"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }
    }
}
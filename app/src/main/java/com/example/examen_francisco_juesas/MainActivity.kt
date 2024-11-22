package com.example.examen_francisco_juesas

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    val categorias: Array<String> = arrayOf("Todo", "Cultura", "Economía", "Política", "Deporte")
    val noticias: Array<String> = arrayOf("Noticia Cultura", "Noticia Economía", "Noticia Política", "Noticia Deporte")

    private val imagenes = intArrayOf(
        R.drawable.cyltv,
        R.drawable.ic_culture,
        R.drawable.ic_economy,
        R.drawable.ic_politics,
        R.drawable.ic_sports
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("ASD", "ASD")


        val spinner: Spinner = findViewById(R.id.planets_spinner)
        val adaptadorPersonalizado = AdaptadorPersonalizado(this, R.layout.linearspiner, categorias)
        spinner.adapter = adaptadorPersonalizado
        spinner.onItemSelectedListener = this


        val listAdapter = AdaptadorPersonalizado(this, android.R.layout.simple_list_item_1, noticias)
        val listView: ListView = findViewById(R.id.list_view)
        listView.adapter = listAdapter
        listView.onItemClickListener


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("HOLA", parent.toString())
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("HOLA", "ASLÑDKAÑSLD")
        val c = view?.findViewById<TextView>(R.id.descripcion)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private inner class AdaptadorPersonalizado(
        context: Context,
        resource: Int,
        objects: Array<String>
    ) : ArrayAdapter<String>(context, resource, objects) {

        override fun getDropDownView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            return crearFilaPersonalizada(position, convertView, parent)
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            return crearFilaPersonalizada(position, convertView, parent)
        }
        private fun crearFilaPersonalizada(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {

            val layoutInflater = LayoutInflater.from(context)

            //Declaro una vista de mi fila, y la preparo para inflarla con datos
            // Los parametros son: XML descriptivo
            // Vista padre
            // Booleano que indica si se debe ceñir a las características del padre
            val rowView = convertView ?: layoutInflater.inflate(R.layout.linearspiner, parent, false)


            rowView.findViewById<TextView>(R.id.descripcion).text = categorias[position]

            rowView.findViewById<ImageView>(R.id.imagenCiudad).setImageResource(imagenes[position])
            return rowView
        }
    }
}

package com.example.app_de_cursos.Vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_de_cursos.Adaptadores.EstadoAdaptador
import com.example.app_de_cursos.Modelo.dataCarreras
import com.example.app_de_cursos.Modelo.dataEstadoCurso
import com.example.app_de_cursos.Presentador.clsEstadoPresentador
import com.example.app_de_cursos.R

class EstadoInscripcionesActivity : AppCompatActivity() {

    private lateinit var btnAtras: TextView
    private lateinit var spnCarrerasEstado: Spinner
    private lateinit var txtTotal: TextView
    private lateinit var rvEstado: RecyclerView
    private lateinit var btnVolverCatalogo: Button

    private lateinit var presentador: clsEstadoPresentador
    private lateinit var adaptador: EstadoAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estado_inscripciones)

        // Enlazar IDs
        btnAtras = findViewById(R.id.btnAtras)
        spnCarrerasEstado = findViewById(R.id.spnCarrerasEstado)
        txtTotal = findViewById(R.id.txtTotal)
        rvEstado = findViewById(R.id.rvEstado)
        btnVolverCatalogo = findViewById(R.id.btnVolverCatalogo)

        // Configurar RecyclerView
        rvEstado.layoutManager = LinearLayoutManager(this)
        adaptador = EstadoAdaptador(emptyList()) // Inicia vacío
        rvEstado.adapter = adaptador

        // Inicializar Presentador y cargar datos
        presentador = clsEstadoPresentador(this)
        presentador.iniciar()

        // Eventos de clic
        btnAtras.setOnClickListener { finish() }
        btnVolverCatalogo.setOnClickListener { finish() }

        // Evento cuando el usuario cambia de carrera en el Spinner
        spnCarrerasEstado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presentador.seleccionarCarrera(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    // Funciones que el Presentador manda a llamar:
    fun mostrarCarreras(lista: List<dataCarreras>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista)
        spnCarrerasEstado.adapter = adapter
    }

    fun mostrarDatosEstado(total: Int, listaCursos: List<dataEstadoCurso>) {
        txtTotal.text = "Total: $total"
        adaptador.actualizarLista(listaCursos)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
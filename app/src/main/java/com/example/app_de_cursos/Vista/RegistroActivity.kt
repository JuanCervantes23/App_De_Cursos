package com.example.app_de_cursos.Vista

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.app_de_cursos.Modelo.RetrofitCliente
import com.example.app_de_cursos.Presentador.clsRegistroPresentador
import com.example.app_de_cursos.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegistroActivity : AppCompatActivity() {
    private lateinit var presenter: clsRegistroPresentador
    private lateinit var txtCurso: TextView
    private lateinit var imgCurso: ImageView
    private lateinit var etMatricula: EditText
    private lateinit var etFecha: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnAtras: TextView

    private var cursoId = 0
    private var fechaApi = "" // Formato yyyy-MM-dd, el que espera la API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        // 1. Componentes de XML
        txtCurso = findViewById(R.id.txtCursoRegistro)
        imgCurso = findViewById(R.id.imgCursoRegistro)
        etMatricula = findViewById(R.id.etMatricula)
        etFecha = findViewById(R.id.etFecha)
        etCorreo = findViewById(R.id.etCorreo)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnAtras = findViewById(R.id.btnAtras)

        // 2. Datos enviados desde MainActivity
        cursoId = intent.getIntExtra("CURSO_ID", 0)
        txtCurso.text = (intent.getStringExtra("NOMBRE_CURSO") ?: "").uppercase()
        val urlImagen = intent.getStringExtra("URL_IMAGEN") ?: ""
        Glide.with(this)
            .load(RetrofitCliente.BASE_URL + urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(imgCurso)

        // 3. Presentador
        presenter = clsRegistroPresentador(this)

        // 4. Fecha: por defecto hoy, y al tocar el campo se abre el calendario
        establecerFecha(Calendar.getInstance())
        etFecha.setOnClickListener { mostrarCalendario() }

        // 5. Botones
        btnConfirmar.setOnClickListener {
            presenter.registrar(
                cursoId,
                etMatricula.text.toString().trim(),
                fechaApi,
                etCorreo.text.toString().trim()
            )
        }
        btnCancelar.setOnClickListener { finish() }
        btnAtras.setOnClickListener { finish() }
    }

    private fun establecerFecha(cal: Calendar) {
        fechaApi = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(cal.time)
        etFecha.setText(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(cal.time))
    }

    private fun mostrarCalendario() {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, anio, mes, dia ->
                cal.set(anio, mes, dia)
                establecerFecha(cal)
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // Evita dobles toques mientras se envía el registro
    fun mostrarCargando(cargando: Boolean) {
        btnConfirmar.isEnabled = !cargando
        btnConfirmar.text = if (cargando) "ENVIANDO" else "CONFIRMAR INSCRIPCIÓN"
    }

    fun registroExitoso(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
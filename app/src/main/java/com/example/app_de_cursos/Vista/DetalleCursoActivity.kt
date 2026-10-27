package com.example.app_de_cursos.Vista

import android.os.Bundle
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
import com.example.app_de_cursos.Modelo.dataDetalleCurso
import com.example.app_de_cursos.Presentador.clsDetalleCursoPresentador
import com.example.app_de_cursos.R

class DetalleCursoActivity : AppCompatActivity() {
    private lateinit var presenter: clsDetalleCursoPresentador
    private lateinit var imgDetalle: ImageView
    private lateinit var txtNombre: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtEspacio: TextView
    private lateinit var txtHoras: TextView
    private lateinit var txtDias: TextView
    private lateinit var btnAtras: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_curso)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        // 1. Enlazar componentes XML
        imgDetalle = findViewById(R.id.imgDetalle)
        txtNombre = findViewById(R.id.txtNombreDetalle)
        txtCodigo = findViewById(R.id.txtCodigoDetalle)
        txtDescripcion = findViewById(R.id.txtDescripcionDetalle)
        txtEspacio = findViewById(R.id.txtEspacioDetalle)
        txtHoras = findViewById(R.id.txtHorasDetalle)
        txtDias = findViewById(R.id.txtDiasDetalle)
        btnAtras = findViewById(R.id.btnAtras)

        btnAtras.setOnClickListener { finish() }

        // 2. Obtener el id del curso enviado desde MainActivity
        val cursoId = intent.getIntExtra("CURSO_ID", 0)

        // 3. Inicializar presentador y cargar el detalle
        presenter = clsDetalleCursoPresentador(this)
        if (cursoId != 0) {
            presenter.cargarDetalle(cursoId)
        } else {
            mostrarError("No se recibió el curso")
        }
    }

    fun mostrarDetalle(curso: dataDetalleCurso) {
        txtNombre.text = curso.nombre.uppercase()
        txtCodigo.text = "Código: " + curso.codigo
        txtDescripcion.text = curso.descripcion
        txtEspacio.text = curso.edificio + ", Salón " + curso.salon
        txtHoras.text = curso.total_horas.toString() + " Horas"
        txtDias.text = curso.dias_atencion + "\n(" + curso.horario + ")"

        Glide.with(this)
            .load(RetrofitCliente.BASE_URL + curso.url_imagen_detalle)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(imgDetalle)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}

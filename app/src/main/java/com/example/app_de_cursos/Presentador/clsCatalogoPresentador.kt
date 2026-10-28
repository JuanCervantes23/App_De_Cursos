package com.example.app_de_cursos.Presentador

import com.example.app_de_cursos.Modelo.clsCarrerasModelo
import com.example.app_de_cursos.Modelo.clsCursosModelo
import com.example.app_de_cursos.Modelo.dataCarreras
import com.example.app_de_cursos.Vista.MainActivity
import com.example.prueba_cursos.Modelo.clsCarrerasModelo
import com.example.prueba_cursos.Modelo.clsCursosModelo
import com.example.prueba_cursos.Modelo.dataCarreras
import com.example.prueba_cursos.Modelo.dataCursos
import com.example.prueba_cursos.Vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCatalogoPresentador(val vista: MainActivity) {
    private val modeloCarreras = clsCarrerasModelo()
    private val modeloCursos = clsCursosModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {
        // Se usa un Scope simple para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // 1. Cargar la lista de carreras en el Spinner
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarr1(listaCarreras)

                // 2. Cargar TODOS los cursos por defecto (carrera_id = 0)
                cargarCursos(0)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {
        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]
            // Si carrera.id es 0, la API traerá todos; si es > 0, filtrará por esa carrera
            cargarCursos(carrera.id)
        }
    }

    private fun cargarCursos(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cursos = modeloCursos.obtenerCursosPorCarrera(carreraId)
                vista.mostrarCursos(cursos)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar cursos: " + e.localizedMessage)
            }
        }
    }

    // Botón "Ver detalle" de la tarjeta
    fun seleccionarCurso(curso: dataCursos) {
        vista.abrirDetalleCurso(curso.id)
    }

    // Botón "Registrarse" de la tarjeta
    fun registrarseCurso(curso: dataCursos) {
        vista.abrirRegistro(curso.id, curso.nombre, curso.urlImagenPrincipal)
    }
}
